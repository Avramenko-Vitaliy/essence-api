package com.essence.api.repositories;

import com.essence.api.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
}
