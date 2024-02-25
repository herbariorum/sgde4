package br.com.sgde.estudantes.entity;

import br.com.sgde.entity.Endereco;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Student {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dta_nasc;
    private String sexo;
    private boolean status;
    private String nomeMae;
    private String nomePai;
    private String telefoneMae;
    private String telefonePai;
    private String nacionalidade;
    private String ufNascimento;
    private String cidadeNascimento;


    private Endereco endereco;

    public Student() {
    }

    public Student(String nome, String cpf, LocalDate dta_nasc, String sexo, boolean status, String nomeMae, String nomePai, String telefoneMae, String telefonePai, String nacionalidade, String ufNascimento, String cidadeNascimento, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dta_nasc = dta_nasc;
        this.sexo = sexo;
        this.status = status;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.telefoneMae = telefoneMae;
        this.telefonePai = telefonePai;
        this.nacionalidade = nacionalidade;
        this.ufNascimento = ufNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.endereco = endereco;
    }

    public Student(Long id, String nome, String cpf, LocalDate dta_nasc, String sexo, boolean status, String nomeMae, String nomePai, String telefoneMae, String telefonePai, String nacionalidade, String ufNascimento, String cidadeNascimento, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dta_nasc = dta_nasc;
        this.sexo = sexo;
        this.status = status;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.telefoneMae = telefoneMae;
        this.telefonePai = telefonePai;
        this.nacionalidade = nacionalidade;
        this.ufNascimento = ufNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.endereco = endereco;
    }

    


}
