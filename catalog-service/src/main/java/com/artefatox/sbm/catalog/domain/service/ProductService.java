package com.artefatox.sbm.catalog.domain.service;

import com.artefatox.sbm.catalog.domain.core.Product;
import com.artefatox.sbm.catalog.domain.repository.ProductRepository;
import com.artefatox.sbm.catalog.mapper.ProductMapper;
import com.artefatox.sbm.catalog.mapper.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Product create(ProductRequest request) {
        Product product = mapper.toEntity(request);
        return repository.save(product);
    }
}
