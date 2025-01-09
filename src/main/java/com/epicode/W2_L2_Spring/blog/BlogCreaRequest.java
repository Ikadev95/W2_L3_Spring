package com.epicode.W2_L2_Spring.blog;

import com.epicode.W2_L2_Spring.autore.Autore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Data
public class BlogCreaRequest {
    @NotEmpty(message = "il campo categoria non può essere vuoto")
    private String categoria;
    @NotEmpty(message = "il campo titolo non può essere vuoto")
    private String titolo;
    @NotEmpty(message = "il campo cover non può essere vuoto")
    private String cover;
    @NotEmpty(message = "il campo contenuto non può essere vuoto")
    @Length(min = 10, max = 300, message = "il contenuto deve essere lungo almeno 10 caratteri")
    private String contenuto;
    @NotNull(message = "il campo tempo di lettura non può essere vuoto")
    private int tempoDiLettura;
    @NotNull(message = "il campo autore id non può essere vuoto")
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
