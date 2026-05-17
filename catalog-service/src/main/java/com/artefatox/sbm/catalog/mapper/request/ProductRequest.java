package com.artefatox.sbm.catalog.mapper.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Esse campo é obrigatório e não deve ser nulo.")
        @Size(min = 3, max = 80)
        String name,

        @NotBlank
        @Size(max = 200)
        String description,

        @NotBlank
        @Size(max = 255)
        String imageUrl,

        @NotNull
        @Positive
        @Digits(integer = 12, fraction = 2)
        BigDecimal price,

        Boolean available
) {
}
