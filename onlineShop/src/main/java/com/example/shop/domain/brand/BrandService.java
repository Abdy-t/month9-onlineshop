package com.example.shop.domain.brand;

import com.example.shop.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public Page<BrandDTO> getBrands(Pageable pageable) {
        return brandRepository.findAll(pageable)
                .map(BrandDTO::from);
    }

    public BrandDTO getBrand(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("brand", id));
        return BrandDTO.from(brand);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public List<Brand> getByName(String name) {
        return brandRepository.getByName(name);
    }
}
