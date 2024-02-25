package br.com.sgde.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ListBancos {
    

    private Long cod;
    private String banco;

    public ListBancos() {
    }

    public ListBancos(Long cod, String banco) {
        this.cod = cod;
        this.banco = banco;
    }


}
