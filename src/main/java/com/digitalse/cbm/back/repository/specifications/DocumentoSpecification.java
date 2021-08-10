package com.digitalse.cbm.back.repository.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.digitalse.cbm.back.entities.Documento;

import org.springframework.data.jpa.domain.Specification;

public class DocumentoSpecification implements Specification<Documento> {

	private Documento filter;

    public DocumentoSpecification(Documento filter) {
        super();
        this.filter = filter;
    }

	@Override
	public Predicate toPredicate(Root<Documento> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		query.distinct(true);
		Predicate p = criteriaBuilder.and();

		if (filter.getNumeracao() != null) {
            p.getExpressions()
                    .add(criteriaBuilder.equal(root.get("numeracao"), filter.getNumeracao()));
        }

		if (filter.getTipo() != null) {
            p.getExpressions()
                    .add(criteriaBuilder.equal(root.get("tipo"), filter.getTipo()));
        }

		if (filter.getPublico() != null) {
            p.getExpressions()
                    .add(criteriaBuilder.equal(root.get("publico"), filter.getPublico()));
        }

		if (filter.getData() != null) {
            p.getExpressions()
                    .add(criteriaBuilder.equal(root.get("data"), filter.getData()));
        }

		if (filter.getDescricao() != null) {
            p.getExpressions()
                    .add(criteriaBuilder.like(root.get("descricao"), filter.getDescricao()));
        }

		if (filter.getCriado() != null) {
            p.getExpressions()
                    .add(criteriaBuilder.equal(root.get("criado"), filter.getCriado()));
        }

		return p;
	}

	/* private Specification<Documento> getSpecification(DocumentoDTO documentoRequestDTO) {
		
		// Build Specification with Employee Id and Filter Text
		return (root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			// Predicate for Employee Id
			Predicate predicateForDocumento = criteriaBuilder.equal(root.get("id"), documentoRequestDTO.getId());

			 if (isNotNullOrEmpty(documentoRequestDTO.getFilterText())) { 
			// Predicate for Employee Projects data
			Predicate predicateForData = criteriaBuilder.or(
					criteriaBuilder.like(root.get("firstName"), "%" + documentoRequestDTO.getFilterText() + "%"),
					criteriaBuilder.like(root.get("lastName"), "%" + documentoRequestDTO.getFilterText() + "%"),
					criteriaBuilder.like(root.get("projectLocation"), "%" + documentoRequestDTO.getFilterText() + "%"));

			// Combine both predicates
			return criteriaBuilder.and(predicateForDocumento, predicateForData);
			 }
			return criteriaBuilder.and(predicateForDocumento);
		};
	} */


    
    /* 

    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {
        Predicate p = cb.disjunction();

        if (filter.getName() != null) {
            p.getExpressions()
                    .add(cb.equal(root.get("name"), filter.getName()));
        }

        if (filter.getSurname() != null && filter.getAge() != null) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("surname"), filter.getSurname()),
                            cb.equal(root.get("age"), filter.getAge())));
        }

        return p;
    } */
}
