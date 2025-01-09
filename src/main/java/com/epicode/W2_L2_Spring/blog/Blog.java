package com.epicode.W2_L2_Spring.blog;

import com.epicode.W2_L2_Spring.autore.Autore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String categoria;

    private String titolo;

    private String cover;

    private String contenuto;

    private int tempoDiLettura;
    //minuti

    @ManyToOne
    private Autore autore;

    public void setAutore(Autore autore) {
        this.autore = autore;
    }
}