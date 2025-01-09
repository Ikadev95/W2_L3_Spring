package com.epicode.W2_L2_Spring.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<Blog>> findAll(Pageable pageable) {
        return ResponseEntity.ok(blogSrv.findAll(pageable));
    }


    public ResponseEntity<List<Blog>> listAllBlogs(){
        List<Blog> blogs = blogSrv.findAll();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
            return ResponseEntity.ok(blogSrv.findById(id));

    }

    @PostMapping
    public ResponseEntity<?> saveBlog
            (@RequestBody BlogCreaRequest request ) {
            return new ResponseEntity<>(blogSrv.saveBlog(request), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyBlog
            (@PathVariable Long id, @RequestBody Blog modBlog) {

            return ResponseEntity.ok(blogSrv.modifyBlog(id, modBlog));
        }


    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteBlog (@PathVariable Long id){
            return new ResponseEntity<>("blog cancellato", HttpStatus.NO_CONTENT);

    }
}
