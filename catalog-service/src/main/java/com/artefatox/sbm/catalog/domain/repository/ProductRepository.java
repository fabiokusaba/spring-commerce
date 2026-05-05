package com.artefatox.sbm.catalog.domain.repository;

import com.artefatox.sbm.catalog.domain.core.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
