
package br.com.sgde.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class Cidades {
    

    private long id;
    private String nome;
    private int estado_id;
    

    public Cidades() {
    }

    public Cidades(long id, String nome, int estado_id) {
        this.id = id;
        this.nome = nome;
        this.estado_id = estado_id;
    }
    
    
}
