package com.example.shop.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>,
                                           JpaSpecificationExecutor<Product> {

//    @Query("select p from Product as p where p.name like CONCAT(:name, '%') and p.price >= :minPrice and p.price <= :maxPrice and p.brand.name like CONCAT(:brand, '%') and p.category.name like CONCAT(:category, '%')")
//    List<Product> getBy(String name, float minPrice, float maxPrice, String brand, String category);

    @Query("select p from Product as p where p.price >= :minPrice and p.price <= :maxPrice ")
    Page<Product> getByPrice(float minPrice, float maxPrice, Pageable pageable);

    @Query("select p from Product as p where p.name like CONCAT(:name, '%')")
    Page<Product> getByName(String name, Pageable pageable);

    Page<Product> getByBrand_Id(int id, Pageable pageable);
}
