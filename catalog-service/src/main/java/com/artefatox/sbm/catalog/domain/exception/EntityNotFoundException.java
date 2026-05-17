package com.artefatox.sbm.catalog.domain.exception;

public class EntityNotFoundException extends DomainException {
    public static final String CODE = "NOT_FOUND";
    public static final int STATUS = 404;
    public EntityNotFoundException(String entityName, Long id) {
        String message = entityName + " " + id + " não encontrado(a).";
        super(CODE, message, STATUS);
    }
}
