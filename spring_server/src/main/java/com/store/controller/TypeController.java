package com.store.controller;

import com.store.entity.Type;
import com.store.excaptions.TypeAlreadyExistException;
import com.store.excaptions.TypesNotFoundException;
import com.store.model.TypeDTO;
import com.store.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
@CrossOrigin
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping()
    public ResponseEntity<List<TypeDTO>> getTypes() throws Exception {
        try {
            List<TypeDTO> types = typeService.getTypes();
            return ResponseEntity.ok().body(types);
        } catch (TypesNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Type type) {
        try {
            typeService.create(type);
            return ResponseEntity.ok("Type successfully added");
        } catch (TypeAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<TypeDTO> getType(@RequestParam Type type) throws TypesNotFoundException {
//        try {
//            return ResponseEntity.ok().body(typeService.getType(type));
//        } catch (TypesNotFoundException e) {
//            throw new TypesNotFoundException(e.getMessage());
//        }
//    }



}
