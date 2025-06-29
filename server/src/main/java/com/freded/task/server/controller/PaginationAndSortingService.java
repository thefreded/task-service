package com.freded.task.server.controller;

import com.freded.common.dto.PaginationAndSortingDTO;
import com.freded.task.client.dto.TaskPaginationAndSortingDTO;
import com.freded.task.server.entity.TaskEntity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

@RequestScoped
public class PaginationAndSortingService {
    private static final String DESC = "DESC";

    /**
     * Applies sorting to a CriteriaQuery based on sorting parameters.
     *
     * @param cb                          The CriteriaBuilder for constructing sort expressions
     * @param cbQuery                     The CriteriaQuery to apply sorting to
     * @param root                        The root dto being queried
     * @param taskPaginationAndSortingDTO Sorting parameters containing sortBy field and sortOrder direction
     */
    public void sort(final CriteriaBuilder cb, final CriteriaQuery<TaskEntity> cbQuery, final Root<TaskEntity> root,
            TaskPaginationAndSortingDTO taskPaginationAndSortingDTO) {

        Order order = DESC.equalsIgnoreCase(taskPaginationAndSortingDTO.getSortOrder()) ?
                cb.desc(root.get(taskPaginationAndSortingDTO.getSortBy())) :
                cb.asc(root.get(taskPaginationAndSortingDTO.getSortBy()));

        cbQuery.orderBy(order);

    }

    /**
     * Using Generics here just for practise. Paginate the dto based on the provided {@link PaginationAndSortingDTO}
     * parameters.
     *
     * @param typedQuery the {@link TypedQuery} to apply pagination to.
     * @param qParams    the {@link PaginationAndSortingDTO} containing pagination options.
     * @param <T>        The type of the dto being queried, which will be sorted.
     * @param <Q>        The type of the pagination parameters object, which must extend
     *                   {@link PaginationAndSortingDTO}.
     */
    public <T, Q extends PaginationAndSortingDTO> void paginate(final TypedQuery<T> typedQuery, final Q qParams) {
        typedQuery.setFirstResult(qParams.getOffset());
        typedQuery.setMaxResults(qParams.getLimit());
    }

}
