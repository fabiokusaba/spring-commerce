package com.artefatox.sbm.catalog.mapper.response;

import java.math.BigDecimal;

public record ProductSummary(
        Long id,
        String name,
        String description,
        BigDecimal price,
        boolean available
) {
}
