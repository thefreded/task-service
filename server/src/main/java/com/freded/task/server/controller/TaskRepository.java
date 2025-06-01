package com.freded.task.server.controller;

import com.freded.dtos.TaskPaginationAndSortingDTO;
import com.freded.task.server.entity.TaskEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Repository class for managing task data persistence operations. Provides CRUD operations for TaskEntity with
 * user-specific access control using JPA Criteria API for dynamic query building.
 */
@ApplicationScoped
public class TaskRepository {

    private static final String CREATEDBY = "createdBy";

    @Inject
    EntityManager em;

    @Inject
    PaginationAndSortingService paginationAndSortingService;

    /**
     * Adds a new task to the entity manager/database.
     *
     * @param task the {@link TaskEntity} object containing task details to be added.
     * @return the created {@link TaskEntity} .
     */
    @Transactional
    public TaskEntity create(final TaskEntity task) {
        em.persist(task);
        return task;
    }

    /**
     * Gets all tasks created by the currently authenticated user. Can also be sorted and paginated based on the
     * provided parameters.
     *
     * @param createdBy                   who created the task. Ideally the loggedIn user.
     * @param taskPaginationAndSortingDTO the {@link TaskPaginationAndSortingDTO } containing pagination and sorting
     *                                    options.
     * @return a list of {@link TaskEntity} objects representing the tasks created by the user in regard to the options
     * provided in {@code taskPaginationAndSortingDTO}.
     */
    public List<TaskEntity> readAll(final String createdBy,
            final TaskPaginationAndSortingDTO taskPaginationAndSortingDTO) {
        // 1. Get the CriteriaBuilder.
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // Create the CriteriaQuery object for Task (more like blueprint)
        CriteriaQuery<TaskEntity> cbQuery = cb.createQuery(TaskEntity.class);

        //Define the FROM clause (Task table. Where the query should start from)
        Root<TaskEntity> root = cbQuery.from(TaskEntity.class);

        // Create a ParameterExpression for the parameter
        ParameterExpression<String> createdByParam = cb.parameter(String.class, CREATEDBY);

        // Select the root entity (TaskEntity) and apply the filter condition to ensure the task is created by the
        // authenticated user.
        cbQuery.select(root).where(cb.equal(root.get(CREATEDBY), createdByParam));

        // Apply sorting based on the parameters provided in taskPaginationAndSortingDTO.
        paginationAndSortingService.sort(cb, cbQuery, root, taskPaginationAndSortingDTO);

        // Create a TypedQuery to execute the CriteriaQuery and get the result as TaskEntity objects.
        TypedQuery<TaskEntity> typedQuery = em.createQuery(cbQuery);

        // Set the parameter for the createdBy field to the username of the currently authenticated user.
        typedQuery.setParameter(CREATEDBY, createdBy);

        // Apply pagination settings to the query based on the provided taskPaginationAndSortingDTO.
        paginationAndSortingService.paginate(typedQuery, taskPaginationAndSortingDTO);

        return typedQuery.getResultList();
    }


    /**
     * Gets the task with the specified ID that was created by the authenticated user, with optional file loading based
     * on query parameters.
     *
     * @param createdBy who created the task. Ideally the logged-in user.
     * @param taskId    unique identifier of the task.
     * @return the found task {@link TaskEntity}, or {@code null} if no task is found.
     */
    public TaskEntity read(final String createdBy, final String taskId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TaskEntity> cq = cb.createQuery(TaskEntity.class);
        Root<TaskEntity> task = cq.from(TaskEntity.class);

        // Add conditions
        Predicate idPredicate = cb.equal(task.get("id"), taskId);
        Predicate createdByPredicate = cb.equal(task.get("createdBy"), createdBy);
        cq.where(cb.and(idPredicate, createdByPredicate));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Updates the task with the taskId with the data provided in newTask.
     *
     * @param newTask newly updated task {@link TaskEntity}.
     * @return newly updated task {@link TaskEntity} or {@code null} if no task is found.
     */
    @Transactional
    public TaskEntity update(final TaskEntity newTask) {
        return em.merge(newTask);
    }

    /**
     * Deletes the task provided.
     *
     * @param task task to be deleted {@link TaskEntity}
     * @return the id of the deleted task or {@code null} if no task is found with the id.
     */
    @Transactional
    public String delete(final TaskEntity task) {
        em.remove(task);
        return task.getId();
    }
}