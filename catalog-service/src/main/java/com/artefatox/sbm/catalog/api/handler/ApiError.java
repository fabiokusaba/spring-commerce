package com.artefatox.sbm.catalog.api.handler;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"status", "code", "message", "fields"})
public class ApiError {
    private Integer status;
    private String code;
    private String message;
    private List<Field> fields;

    public ApiError(RuntimeException ex, Integer status, String code) {
        this.status = status;
        this.code = code;
        this.message = ex.getMessage();
        this.fields = new ArrayList<>();
    }

    public ApiError(List<Field> fields) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.code = "INVALID_PROPERTIES";
        this.message = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        this.fields = fields;
    }

    @Getter
    @Setter
    @Builder
    @JsonPropertyOrder({"name", "message"})
    public static class Field {
        private String name;
        private String message;
    }
}
