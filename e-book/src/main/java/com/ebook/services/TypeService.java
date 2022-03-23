package com.ebook.services;

import com.ebook.entities.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {
    List<Type> getAllTypes();
    List<String> getNamesOfTypesToList();
    Type getTypeByName(String name);
    Type getTypeById(Long id);
}
