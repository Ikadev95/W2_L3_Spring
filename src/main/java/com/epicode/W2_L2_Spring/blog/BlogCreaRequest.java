package com.epicode.W2_L2_Spring.blog;

import com.epicode.W2_L2_Spring.autore.Autore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class BlogCreaRequest {
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    private Long autoreId;

    public int getTempoDiLettura() {
        return tempoDiLettura;
    }

    public String getContenuto() {
        return contenuto;
    }

    public String getCover() {
        return cover;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public Long getAutoreId() {
        return autoreId;
    }
}
