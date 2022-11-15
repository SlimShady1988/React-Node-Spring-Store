package com.store.services;

import com.store.entity.Brand;
import com.store.excaptions.BrandAlreadyExistException;
import com.store.excaptions.BrandsNotFoundException;
import com.store.model.BrandDTO;
import com.store.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<BrandDTO> getBrands() throws BrandsNotFoundException {

        List<BrandDTO> brands = brandRepository.findAll().stream().map(BrandDTO::toDTO).toList();
        if (brands.isEmpty()) {
            throw new BrandsNotFoundException("Brands has not been found");
        }
        return brands;
    }

    public BrandDTO getBrand(Brand brand) throws BrandsNotFoundException {
        if (brandRepository.findById(brand.getId()).isPresent()) {
            return BrandDTO.toDTO(brand);
        } else {
            throw new BrandsNotFoundException("Brand has not been found");
        }

    }

    public void create(Brand brand) throws BrandAlreadyExistException {
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new BrandAlreadyExistException("Brand with this name already exist");
        } else {
            brandRepository.save(brand);
        }
    }
}
