package com.ebook.services.Impl;

import com.ebook.entities.Type;
import com.ebook.repositories.TypeRepository;
import com.ebook.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Type getTypeById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Type saveType(Type type) {
        return repository.save(type);
    }

    @Override
    public void deleteType(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Type updateType(Type type) {
        return repository.save(type);
    }
}
