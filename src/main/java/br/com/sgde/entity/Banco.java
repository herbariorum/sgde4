package br.com.sgde.entity;

import br.com.sgde.servidores.entity.Employees;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class Banco {
    
    private Long id;
    private String nome;
    private String agencia;
    private String conta;
    private double salario;

   
    private Employees employee;
    
    public Banco() {
    }

    public Banco(Long id, String nome, String agencia, String conta, double salario) {
        this.id = id;
        this.nome = nome;
        this.agencia = agencia;
        this.conta = conta;
        this.salario = salario;
    }


}
