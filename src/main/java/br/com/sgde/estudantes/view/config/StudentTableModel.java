package br.com.sgde.estudantes.view.config;




import br.com.sgde.estudantes.entity.Student;
import br.com.sgde.util.Util;
import br.com.sgde.util.ViewAbstractTableModel;
import java.util.List;

public class StudentTableModel extends ViewAbstractTableModel<Student> {

    public StudentTableModel(List<Student> rows) {
        super(rows);
        columns = new String[]{
                "ID",
                "NOME",
                "CPF",
                "NASCIMENTO",
                "MÃE"
                
        };

    }

    @Override
    public Object getValueAt(int row, int col) {
        Student std = this.rows.get(row);
        switch (col) {
            case 0:
                return std.getId();
            case 1:
                return std.getNome();
            case 2:
                return std.getCpf();         
            case 3:
                return new Util().formatDate(std.getDta_nasc());
            case 4:
                return std.getNomeMae();
            default:
                return null;
        }
    }
}
