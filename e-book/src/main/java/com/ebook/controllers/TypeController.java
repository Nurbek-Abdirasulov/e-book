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
        try{
            return new ResponseEntity<>(typeService.getAllTypes(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{typeId}")
    public String deleteType(@PathVariable ("typeId") Long typeId){
        try {
            typeService.deleteType(typeId);
            return "Type with ID = " + typeId + " was successfully deleted.";
        }catch (Exception e){
            return String.valueOf(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Type> addType(@RequestBody Type type){
        try{
            return new ResponseEntity<>(typeService.saveType(type), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @CrossOrigin
    public ResponseEntity<Type> updateType(@RequestBody Type type){
        try{
            return new ResponseEntity<>(typeService.updateType(type), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{typeId}")
    public ResponseEntity<Type> getTypeById(@PathVariable("typeId") Long typeId){
        try {
            return new ResponseEntity<>(typeService.getTypeById(typeId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
