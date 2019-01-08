package ir.asta.training.exercise2.utils.exceptions;

import javax.persistence.EntityNotFoundException;

public class EComEntityNotFoundException extends EntityNotFoundException {
    private Long id;
    private Class<?> entityClass;

    public EComEntityNotFoundException(Long id, Class<?> entityClass) {
        this.id = id;
        this.entityClass = entityClass;
    }

    public EComEntityNotFoundException(String message, Long id, Class<?> entityClass) {
        super(message);
        this.id = id;
        this.entityClass = entityClass;
    }

    public Long getId() {
        return id;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }
}
