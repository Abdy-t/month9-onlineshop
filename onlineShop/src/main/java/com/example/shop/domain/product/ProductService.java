package com.example.shop.domain.product;

import com.example.shop.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

//    public List<Product> getSearchResult(String name, float min, float max, String brand, String category) {
//        return productRepository.getBy(name, min, max, brand, category);
//
//    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<ProductDTO> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductDTO::from);
    }

    public Page<ProductDTO> searchByName(String name, Pageable pageable) {
        return productRepository.getByName(name, pageable).map(ProductDTO::from);
    }

    public Page<ProductDTO> searchByPrice(float min, float max, Pageable pageable) {
        return productRepository.getByPrice(min, max, pageable).map(ProductDTO::from);
    }

    public Page<ProductDTO> getProductsByBrand(int id, Pageable pageable) {
        return productRepository.getByBrand_Id(id, pageable).map(ProductDTO::from);
    }

    public ProductDTO getProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", id));
        return ProductDTO.from(product);
    }
}
