package com.ebook.services;

import com.ebook.entities.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {
    List<Type> getAllTypes();

    Type getTypeById(Long id);

    Type saveType(Type type);

    void deleteType(Long id);

    Type updateType(Type type);
}
