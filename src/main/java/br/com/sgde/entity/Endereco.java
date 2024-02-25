package br.com.sgde.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Endereco {

  
    private Long idaddress;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;
    private String cidade;
    private String cep;
    private Integer employee_id;
    private Integer student_id;

    public Endereco() {
    }

    public Endereco(Long idaddress, String logradouro, String numero, String complemento, String bairro, String estado, String cidade, String cep) {
        this.idaddress = idaddress;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
    }

    public Endereco(String logradouro, String numero, String complemento, String bairro, String estado, String cidade, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
    }

 

}
