package com.store.controller;

import com.store.entity.Brand;
import com.store.entity.Type;
import com.store.excaptions.DeviceAlreadyExistException;
import com.store.excaptions.DeviceNotFoundException;
import com.store.model.DeviceDTO;
import com.store.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getDevices(
            @RequestParam (value = "typeId", required = false) Type type,
            @RequestParam (value = "brandId", required = false) Brand brand,
            @RequestParam (value = "limit", required = false) Integer limit,
            @RequestParam (value = "page", required = false) Integer page
    ) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("brand", brand);
        params.put("type", type);
        params.put("limit", limit);
        params.put("page", page);

        try {
            Map<String, Object> devices = deviceService.getDevices(params);
            return ResponseEntity.ok().body(devices);
        } catch (DeviceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable Long id) throws DeviceNotFoundException {
        try {
            return ResponseEntity.ok().body(deviceService.getDevice(id));
        } catch (DeviceNotFoundException e) {
            throw new DeviceNotFoundException(e.getMessage());
        }
    }

    @PostMapping(path = "/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> create(
            @RequestParam(required = false, name = "info") String info,
            @RequestParam(name = "img") MultipartFile img,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") Integer price,
            @RequestParam(name = "brandId") Brand brand ,
            @RequestParam(name = "typeId") Type type
    ) {
        try {
            deviceService.create(name, brand, type, price, img, info);
            return ResponseEntity.ok("Device successfully added");
        } catch (DeviceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
