package com.example.shop.domain.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("select p from Brand as p where p.name like CONCAT(:name, '%')")
    List<Brand> getByName(String name);

}
