package com.store.services;

import com.store.entity.Type;
import com.store.excaptions.TypeAlreadyExistException;
import com.store.excaptions.TypesNotFoundException;
import com.store.model.TypeDTO;
import com.store.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<TypeDTO> getTypes() throws TypesNotFoundException {
        List<TypeDTO> types = typeRepository.findAll().stream().map(TypeDTO::toDTO).toList();
        if (types.isEmpty()) {
            throw new TypesNotFoundException("Types has not been found type");
        }
        return types;
    }

    public TypeDTO getType(Type type) throws TypesNotFoundException {
        if (typeRepository.findById(type.getId()).isPresent()) {
            return TypeDTO.toDTO(type);
        } else {
            throw new TypesNotFoundException("Type has not been found type");
        }

    }

    public void create(Type type) throws TypeAlreadyExistException {
        if (typeRepository.findByName(type.getName()).isPresent()) {
            throw new TypeAlreadyExistException("Type with this name already exist");
        } else {
            typeRepository.save(type);
        }
    }
}
