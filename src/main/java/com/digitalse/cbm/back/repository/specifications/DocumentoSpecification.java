package com.digitalse.cbm.back.repository.specifications;

import java.time.OffsetDateTime;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.digitalse.cbm.back.entities.Documento;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.data.jpa.domain.Specification;

public class DocumentoSpecification implements Specification<Documento> {

	private JsonNode filter;

    public DocumentoSpecification(JsonNode filter) {
        super();
        this.filter = filter;
    }

     //TODO: matricula do militar, nome do militar e palavras-chave
	@Override
	public Predicate toPredicate(Root<Documento> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		query.distinct(true);
		Predicate p = criteriaBuilder.and();
       
		if (filter.has("numeracao") && !filter.get("numeracao").isNull()) {
            p.getExpressions()
                    .add(criteriaBuilder.equal(root.get("numeracao"), filter.get("numeracao").asText()));
        }

		if (filter.has("tipo") && !filter.get("tipo").isNull()) {
            p.getExpressions()
                    .add(criteriaBuilder.equal(root.get("tipo"), filter.get("tipo").asText()));
        }
        
		if ((filter.has("data inicial") || filter.has("data final")) && (!filter.get("data inicial").isNull() || !filter.get("data final").isNull())) {
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSXXX");
            OffsetDateTime dataInicial;
            OffsetDateTime dataFinal;

            if(filter.has("data inicial")){
                dataInicial = OffsetDateTime.parse(filter.get("data inicial").asText());
            } else {
                dataInicial = null;
            }

            if(filter.has("data final")){
                dataFinal = OffsetDateTime.parse(filter.get("data final").asText());
            } else {
                dataFinal = null;
            }

            if(filter.has("data inicial") && filter.has("data final")){
                p.getExpressions()
                    .add(criteriaBuilder.between(root.<OffsetDateTime>get("criado"), dataInicial, dataFinal));
            }
            if(filter.has("data inicial") && !filter.has("data final")){
                p.getExpressions()
                    .add(criteriaBuilder.greaterThanOrEqualTo(root.<OffsetDateTime>get("criado"), dataInicial));
            }
            if(!filter.has("data inicial") && filter.has("data final")){
                p.getExpressions()
                    .add(criteriaBuilder.lessThanOrEqualTo(root.<OffsetDateTime>get("criado"), dataFinal));
            }
            System.out.println(dataInicial+"\n"+dataFinal);
        }

		return p;
	}

}
