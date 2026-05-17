package com.artefatox.sbm.catalog.mapper;

import com.artefatox.sbm.catalog.domain.core.Product;
import com.artefatox.sbm.catalog.mapper.request.ProductRequest;
import com.artefatox.sbm.catalog.mapper.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {
    Product toEntity(ProductRequest request);
    ProductResponse toResponse(Product entity);
}
