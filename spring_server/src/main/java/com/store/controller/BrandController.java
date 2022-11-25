package com.store.controller;

import com.store.entity.Brand;
import com.store.excaptions.BrandAlreadyExistException;
import com.store.excaptions.BrandsNotFoundException;
import com.store.model.BrandDTO;
import com.store.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping()
    public ResponseEntity<List<BrandDTO>> getBrands() throws Exception {
        try {
            List<BrandDTO> brands = brandService.getBrands();
            return ResponseEntity.ok().body(brands);
        } catch (BrandsNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Brand brand) {
        try {
            brandService.create(brand);
            return ResponseEntity.ok("Brand successfully added");
        } catch (BrandAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
