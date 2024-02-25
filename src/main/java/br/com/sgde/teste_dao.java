
package br.com.sgde;

import br.com.sgde.Exceptions.ExceptionDAO;
import br.com.sgde.estudantes.dao.StudentDAO;
import br.com.sgde.util.Util;
import java.sql.SQLException;


public class teste_dao {
    
    public static void lista_by_cpf() throws SQLException, ExceptionDAO{
//       var cpf = "797.983.654-53";
//        var nome = "Silva";
       
       StudentDAO dao = new StudentDAO();
        System.out.println("Lista "+ dao.findById(1L));
    }
    
    public static void main(String[] args) throws SQLException, ExceptionDAO {
//        lista_by_cpf();
        System.out.println("Número "+ new Util().apenasNumero("797.983.654-53"));
    }
}
