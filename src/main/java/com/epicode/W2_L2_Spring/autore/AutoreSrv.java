package com.epicode.W2_L2_Spring.autore;

import jakarta.persistence.EntityExistsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreSrv {

    @Autowired
    private AutoreRepo autoreRepo;

    public List<Autore> findAll(){return autoreRepo.findAll();}

    public Autore findById(Long id){
        if(!autoreRepo.existsById(id)){
            throw new EntityExistsException("l'autore non è stato trovato");
        }

        return autoreRepo.findById(id).get();
    };

    public Autore findByEmail(String mail){
        if(!autoreRepo.existsByEmail(mail)){
            throw new EntityExistsException("l'autore non è stato trovato");
        }

        return autoreRepo.findByEmail(mail);
    };

    public Autore saveAutore(AutoreCreaRequest a){
        if(autoreRepo.existsByEmail(a.getEmail())){
            throw new EntityExistsException("un autore con questa mail esiste già");
        }

        Autore autore = new Autore();
        BeanUtils.copyProperties(a,autore);

        return autoreRepo.save(autore);
    }

    public Autore modifyAutore(Long id, Autore modAutore){
        Autore a = findById(id);

        BeanUtils.copyProperties(modAutore, a);

        return autoreRepo.save(a);

    }

    public Autore deleteAutore (Long id){
        Autore a = findById(id);
        autoreRepo.delete(a);
        return a;
    }


}
