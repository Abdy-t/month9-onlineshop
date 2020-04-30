package com.example.shop.repository;



import com.example.shop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("select p from Brand as p where p.name like CONCAT(:name, '%')")
    List<Brand> getByName(String name);
}
