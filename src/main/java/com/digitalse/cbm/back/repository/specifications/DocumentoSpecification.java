package com.digitalse.cbm.back.repository.specifications;

import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.digitalse.cbm.back.entities.Documento;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.data.jpa.domain.Specification;

/**
 * Gera um specification para realizar uma busca com multiplos campos.
 */
public class DocumentoSpecification implements Specification<Documento> {

    private JsonNode filter;

    /**
     * Instancia um specification para realizar uma busca com multiplos campos.
     * @param jsonNode Um jsonNode gerado a partir dos multiplos campos enviados pelo endpoint.
     */
    public DocumentoSpecification(JsonNode filter) {
        super();
        this.filter = filter;
    }

    // TODO: matricula do militar, nome do militar e palavras-chave
    @Override
    public Predicate toPredicate(Root<Documento> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Predicate p = criteriaBuilder.and();

        if (filter.has("numeracao") && !filter.get("numeracao").isNull()) {
            p.getExpressions().add(criteriaBuilder.equal(root.get("numeracao"), filter.get("numeracao").asText()));
        }

        if (filter.has("tipo") && !filter.get("tipo").isNull()) {
            p.getExpressions().add(criteriaBuilder.equal(root.get("tipo"), filter.get("tipo").asText()));
        }

        if ((filter.has("datainicial") || filter.has("datafinal"))
                && (!filter.get("datainicial").isNull() || !filter.get("datafinal").isNull())) {
            // DateTimeFormatter formatter =
            // DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSXXX");
            LocalDate dataInicial;
            LocalDate dataFinal;

            if (filter.has("datainicial")) {
                dataInicial = LocalDate.parse(filter.get("datainicial").asText());
            } else {
                dataInicial = null;
            }

            if (filter.has("datafinal")) {
                dataFinal = LocalDate.parse(filter.get("datafinal").asText());
            } else {
                dataFinal = null;
            }

            if (filter.has("datainicial") && filter.has("datafinal")) {
                p.getExpressions().add(criteriaBuilder.between(root.<LocalDate>get("data"), dataInicial, dataFinal));
            }
            if (filter.has("datainicial") && !filter.has("datafinal")) {
                p.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get("data"), dataInicial));
            }
            if (!filter.has("datainicial") && filter.has("datafinal")) {
                p.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.<LocalDate>get("data"), dataFinal));
            }

        }

        /*
         * if (filter.has("matriculamilitar") &&
         * !filter.get("matriculamilitar").isNull()) { root.join("militares",
         * JoinType.INNER); p.getExpressions()
         * .add(criteriaBuilder.equal(root.get("militares").get("matricula"),
         * filter.get("matriculamilitar").asText())); }
         */

        return p;
    }

}
