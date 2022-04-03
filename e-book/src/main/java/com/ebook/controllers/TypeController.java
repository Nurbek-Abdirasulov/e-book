package com.ebook.controllers;

import com.ebook.entities.Type;
import com.ebook.services.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type/")
@CrossOrigin
public class TypeController {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Type>> getAllTypes(){
        return new ResponseEntity<>(typeService.getAllTypes(), HttpStatus.OK);
    }

    @DeleteMapping("/{typeId}")
    public String deleteType(@PathVariable ("typeId") Long typeId){
        typeService.deleteType(typeId);
        return "Type with ID = " + typeId + " was successfully deleted.";
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Type> addType(@RequestBody Type type){
        return new ResponseEntity<>(typeService.saveType(type), HttpStatus.OK);
    }

    @PutMapping
    @CrossOrigin
    public ResponseEntity<Type> updateType(@RequestBody Type type){
        return new ResponseEntity<>(typeService.updateType(type), HttpStatus.OK);
    }

    @GetMapping("/{typeId}")
    public ResponseEntity<Type> getTypeById(@PathVariable("typeId") Long typeId){
        return new ResponseEntity<>(typeService.getTypeById(typeId), HttpStatus.OK);
    }
}
