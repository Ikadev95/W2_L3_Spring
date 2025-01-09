package com.epicode.W2_L2_Spring.blog;

import com.epicode.W2_L2_Spring.autore.Autore;
import com.epicode.W2_L2_Spring.autore.AutoreCreaRequest;
import com.epicode.W2_L2_Spring.autore.AutoreRepo;
import com.epicode.W2_L2_Spring.autore.AutoreSrv;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogSrv {
    @Autowired
    private BlogRepo blogRepo;
    @Autowired
    private AutoreSrv autoreSrv;

    public List<Blog> findAll(){return blogRepo.findAll();}

    public Blog findById(Long id){
        if(!blogRepo.existsById(id)){
            throw new EntityExistsException("il blog non è stato trovato");
        }

        return blogRepo.findById(id).get();
    };

    public Blog saveBlog(BlogCreaRequest a){
        if(blogRepo.existsByTitolo(a.getTitolo())){
            throw new EntityExistsException("un blog con questa mail esiste già");
        }

        Blog blog = new Blog();
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
