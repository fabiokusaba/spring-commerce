package com.artefatox.sbm.catalog.mapper;

import com.artefatox.sbm.catalog.domain.core.Product;
import com.artefatox.sbm.catalog.domain.valueobject.ProductFilter;
import com.artefatox.sbm.catalog.mapper.request.ProductRequest;
import com.artefatox.sbm.catalog.mapper.response.ProductResponse;
import com.artefatox.sbm.catalog.mapper.response.ProductSummary;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {
    Product toEntity(ProductRequest request);
    ProductResponse toResponse(Product entity);
    ProductSummary toSummary(Product entity);
    Product toFilter(ProductFilter filter);
    @Mapping(target = "id", ignore = true)
    Product update(ProductRequest request, @MappingTarget Product entity);
}
