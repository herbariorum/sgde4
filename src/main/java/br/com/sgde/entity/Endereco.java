package br.com.sgde.entity;

import br.com.sgde.estudantes.entity.Student;
import br.com.sgde.servidores.entity.Employees;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Endereco {

  
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;
    private String cidade;
    private String cep;

    private Employees employee;

    private Student student;
    
    public Endereco() {
    }

    public Endereco(Long id, String logradouro, String numero, String complemento, String bairro, String estado, String cidade, String cep) {
        this.id = id;
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
