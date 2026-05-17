package com.artefatox.sbm.catalog.mapper.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        String imageUrl,
        BigDecimal price,
        Boolean available
) {
}
