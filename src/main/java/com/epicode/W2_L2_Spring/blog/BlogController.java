package com.epicode.W2_L2_Spring.blog;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogSrv blogSrv;

    @GetMapping
    public ResponseEntity<List<Blog>> listAllBlogs(){
        List<Blog> blogs = blogSrv.findAll();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(blogSrv.findById(id));
        } catch (EntityExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveBlog
            (@RequestBody BlogCreaRequest request ) {
        try{
            return new ResponseEntity<>(blogSrv.saveBlog(request), HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyBlog
            (@PathVariable Long id, @RequestBody Blog modBlog) {
        try{
            return ResponseEntity.ok(blogSrv.modifyBlog(id, modBlog));
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteBlog (@PathVariable Long id){
        try{
            return new ResponseEntity<>("blog cancellato", HttpStatus.NO_CONTENT);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
