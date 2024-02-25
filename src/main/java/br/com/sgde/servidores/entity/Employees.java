package br.com.sgde.servidores.entity;


import br.com.sgde.entity.Banco;
import br.com.sgde.entity.Endereco;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Employees {
    

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dta_nasc;
    private String telefone;
    private boolean status;
    private String email;
    private String cargo;
    
    

    private Endereco endereco;

    private Banco banco;
    
//    private ArrayList<Banco> conta_bancaria = new ArrayList<>();


    public Employees() {
    }
  

//    public void addEndereco(Endereco endereco) {
//        enderecos.add(endereco);
//        endereco.setEmployee(this);
//    }
//
//    public void removeEndereco(Endereco endereco) {
//        enderecos.remove(endereco);
//        endereco.setEmployee(null);
//    }
}
