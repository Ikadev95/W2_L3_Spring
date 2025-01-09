package com.epicode.W2_L2_Spring.autore;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autori")
public class AutoreController {

    @Autowired
    private AutoreSrv autoreSrv;

    @GetMapping
    public ResponseEntity<List<Autore>> listAllAutori(){
        List<Autore> autori = autoreSrv.findAll();
        return ResponseEntity.ok(autori);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(autoreSrv.findById(id));
        } catch (EntityExistsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveAutore(@RequestBody AutoreCreaRequest request ) {
        try{
            return new ResponseEntity<>(autoreSrv.saveAutore(request), HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyAutore(@PathVariable Long id, @RequestBody Autore modAutore) {
        try{
            return ResponseEntity.ok(autoreSrv.modifyAutore(id, modAutore));
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteAutore(@PathVariable Long id){
        try{
            return new ResponseEntity<>("blog cancellato", HttpStatus.NO_CONTENT);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
