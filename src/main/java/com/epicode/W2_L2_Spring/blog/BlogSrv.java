package com.epicode.W2_L2_Spring.blog;

import com.epicode.W2_L2_Spring.autore.Autore;
import com.epicode.W2_L2_Spring.autore.AutoreCreaRequest;
import com.epicode.W2_L2_Spring.autore.AutoreRepo;
import com.epicode.W2_L2_Spring.autore.AutoreSrv;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class BlogSrv {
    @Autowired
    private BlogRepo blogRepo;
    @Autowired
    private AutoreSrv autoreSrv;
    @Autowired
    private AutoreRepo autoreRepo;

    public List<Blog> findAll(){return blogRepo.findAll();}
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepo.findAll(pageable);
    }

    public Blog findById(Long id){
        if(!blogRepo.existsById(id)){
            throw new EntityNotFoundException("il blog non è stato trovato");
        }

        return blogRepo.findById(id).get();
    };

    public Blog saveBlog(@Valid BlogCreaRequest a){
        if(blogRepo.existsByTitolo(a.getTitolo())){
            throw new EntityExistsException("un blog con questo titolo esiste già");
        }

        Blog blog = new Blog();
        if(!autoreRepo.existsById(a.getAutoreId())){
            throw new EntityNotFoundException("non esiste un autore con questo id");
        }
        Autore autore = autoreSrv.findById(a.getAutoreId());
        BeanUtils.copyProperties(a,blog);
        blog.setAutore(autore);

        return blogRepo.save(blog);
    }

    public Blog modifyBlog(Long id, Blog modBlog){
        Blog a = findById(id);

        BeanUtils.copyProperties(modBlog, a);

        return blogRepo.save(a);

    }

    public Blog deleteBlog (Long id){
        Blog a = findById(id);
        blogRepo.delete(a);
        return a;
    }

}
