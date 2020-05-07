package com.example.shop.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>,
                                           JpaSpecificationExecutor<Product> {
    @Query("select p from Product as p where p.name like CONCAT(:name, '%') and p.price >= :minPrice and p.price <= :maxPrice and p.brand.name like CONCAT(:brand, '%') and p.category.name like CONCAT(:category, '%')")
    List<Product> getBy(String name, float minPrice, float maxPrice, String brand, String category);
//    @Query("select p from Product as p where p.name like CONCAT(:name, '%') and p.price >= :minPrice and p.price <= :maxPrice ")
//    List<Product> getBy(String name, float minPrice, float maxPrice);
//@Query("select p from Product as p where p.name like CONCAT(:name, '%')")
//List<Product> getBy(String name);
}
