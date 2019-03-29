package me.annanjin.shop.util.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

public class SearchCriteriaConsumer implements Consumer<SearchCriteria> {

    protected Predicate predicate;
    protected CriteriaBuilder criteriaBuilder;
    protected Root root;

    public SearchCriteriaConsumer() {
    }

    public SearchCriteriaConsumer(Predicate predicate, CriteriaBuilder criteriaBuilder, Root root) {
        this.predicate = predicate;
        this.criteriaBuilder = criteriaBuilder;
        this.root = root;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    @Override
    public void accept(SearchCriteria searchCriteria) {
        switch (searchCriteria.getOperator()) {
            case EQUALITY:
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
                break;
            case NEGATION:
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
                break;
            case GREATER_THAN:
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
                break;
            case LESS_THAN:
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
                break;
            case LIKE:
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
                break;
            case CONTAINS:
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%"));
                break;
            case STARTS_WITH:
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(searchCriteria.getKey()), searchCriteria.getValue() + "%"));
                break;
            case ENDS_WITH:
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue()));
                break;
            default:
                break;

        }
    }
}
