package br.com.sgde.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Estados {
 
   
    private Long id;
    private String nome;
    private String uf;
    
    public Estados() {
    }

    public Estados(String nome, String uf) {
        this.nome = nome;
        this.uf = uf;
    }

    public Estados(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    
    
    public Estados(Long id, String nome, String uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }
    
    
    
}
