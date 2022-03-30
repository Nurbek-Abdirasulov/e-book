package com.ebook.services.Impl;

import com.ebook.entities.Type;
import com.ebook.repositories.TypeRepository;
import com.ebook.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository repository;

    @Autowired
    public TypeServiceImpl(TypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Type> getAllTypes() {
        return repository.findAll();
    }

    @Override
    public List<String> getNamesOfTypesToList() {
        List<Type> types = repository.findAll();
        List<String> stringList= types.stream()
                .map(type -> type.getName()).collect(Collectors.toList());
        return stringList;
    }

    @Override
    public Type getTypeByName(String name) {
        return repository.getTypeByName(name);
    }

    @Override
    public Type getTypeById(Long id) {
        return repository.findById(id).get();
    }
}
