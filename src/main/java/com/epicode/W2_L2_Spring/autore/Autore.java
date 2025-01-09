package com.epicode.W2_L2_Spring.autore;

import com.epicode.W2_L2_Spring.blog.Blog;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "autori")
public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;

    private String avatar;

    @OneToMany (mappedBy = "autore", cascade = CascadeType.ALL)
    private List<Blog> blogs;
}