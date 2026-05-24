package com.artefatox.sbm.catalog.domain.service;

import com.artefatox.sbm.catalog.domain.core.Product;
import com.artefatox.sbm.catalog.domain.exception.EntityNotFoundException;
import com.artefatox.sbm.catalog.domain.repository.ProductRepository;
import com.artefatox.sbm.catalog.domain.valueobject.ProductFilter;
import com.artefatox.sbm.catalog.mapper.ProductMapper;
import com.artefatox.sbm.catalog.mapper.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Product create(ProductRequest request) {
        Product product = mapper.toEntity(request);
        return repository.save(product);
    }

    public Product findById(Long id) {
        Optional<Product> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Product", id);
        }
        return optional.get();
    }

    public Page<Product> list(ProductFilter filter, Pageable pageable) {
        Product probe = mapper.toFilter(filter);
        Example<Product> example = Example.of(probe, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues());
        return repository.findAll(example, pageable);
    }

    public Product update(Long id, ProductRequest request) {
        Product product = this.findById(id);
        mapper.update(request, product);
        return repository.save(product);
    }
}
