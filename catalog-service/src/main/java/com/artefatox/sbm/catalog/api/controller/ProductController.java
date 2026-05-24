package com.artefatox.sbm.catalog.api.controller;

import com.artefatox.sbm.catalog.domain.core.Product;
import com.artefatox.sbm.catalog.domain.service.ProductService;
import com.artefatox.sbm.catalog.domain.valueobject.ProductFilter;
import com.artefatox.sbm.catalog.mapper.ProductMapper;
import com.artefatox.sbm.catalog.mapper.request.ProductRequest;
import com.artefatox.sbm.catalog.mapper.response.ProductResponse;
import com.artefatox.sbm.catalog.mapper.response.ProductSummary;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request) {
        Product product = service.create(request);
        ProductResponse response = mapper.toResponse(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> show(@PathVariable("id") Long id) {
        Product product = service.findById(id);
        ProductResponse response = mapper.toResponse(product);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> list(ProductFilter filter, Pageable pageable) {
        Page<Product> page = service.list(filter, pageable);
        Page<ProductSummary> response = page.map(mapper::toSummary);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable("id") Long id,
                                                  @RequestBody @Valid ProductRequest request) {
        Product product = service.update(id, request);
        return ResponseEntity.ok(mapper.toResponse(product));
    }
}
