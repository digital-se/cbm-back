package com.digitalse.cbm.back.repository.specifications;

import java.time.LocalDate;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.digitalse.cbm.back.entities.Documento;

import org.springframework.data.jpa.domain.Specification;

/**
 * Gera um specification para realizar uma busca com multiplos campos.
 */
public class DocumentoSpecification implements Specification<Documento> {

    private Map<String, Object> filter;

    /**
     * Instancia um specification para realizar uma busca com multiplos campos.
     * @param jsonNode Um jsonNode gerado a partir dos multiplos campos enviados pelo endpoint.
     */
    public DocumentoSpecification(Map<String, Object> filter) {
        super();
        this.filter = filter;
    }

    // TODO: matricula do militar, nome do militar e palavras-chave
    @Override
    public Predicate toPredicate(Root<Documento> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Predicate p = criteriaBuilder.and();

        if (filter.containsKey("nome")) {
            p.getExpressions().add(criteriaBuilder.like(root.get("nome"), "%" + ((String)filter.get("nome")) + "%" ));
        }

        if (filter.containsKey("numeracao")) {
            p.getExpressions().add(criteriaBuilder.equal(root.get("numeracao"), (String)filter.get("numeracao")));
        }

        if (filter.containsKey("tipo")) {
            p.getExpressions().add(criteriaBuilder.equal(root.get("tipo"), (String)filter.get("tipo")));
        }

        if (filter.containsKey("datainicial") || filter.containsKey("datafinal")) {
            // DateTimeFormatter formatter =
            // DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSXXX");
            LocalDate dataInicial;
            LocalDate dataFinal;

            if (filter.containsKey("datainicial")) {
                dataInicial = LocalDate.parse(((LocalDate)filter.get("datainicial")).toString());
            } else {
                dataInicial = null;
            }

            if (filter.containsKey("datafinal")) {
                dataFinal = LocalDate.parse(((LocalDate)filter.get("dataFinal")).toString());
            } else {
                dataFinal = null;
            }

            if (filter.containsKey("datainicial") && filter.containsKey("datafinal")) {
                p.getExpressions().add(criteriaBuilder.between(root.<LocalDate>get("data"), dataInicial, dataFinal));
            }
            if (filter.containsKey("datainicial") && !filter.containsKey("datafinal")) {
                p.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get("data"), dataInicial));
            }
            if (!filter.containsKey("datainicial") && filter.containsKey("datafinal")) {
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
