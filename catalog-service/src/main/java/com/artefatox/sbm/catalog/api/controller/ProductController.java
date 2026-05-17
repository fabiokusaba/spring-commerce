package com.artefatox.sbm.catalog.api.controller;

import com.artefatox.sbm.catalog.domain.core.Product;
import com.artefatox.sbm.catalog.domain.service.ProductService;
import com.artefatox.sbm.catalog.mapper.ProductMapper;
import com.artefatox.sbm.catalog.mapper.request.ProductRequest;
import com.artefatox.sbm.catalog.mapper.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
