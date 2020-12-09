package com.essence.api.repositories;

import com.essence.api.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandsRepository extends JpaRepository<Brand, Integer> {
}
