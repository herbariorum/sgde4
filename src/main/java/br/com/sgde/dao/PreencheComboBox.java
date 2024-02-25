
package br.com.sgde.dao;

import br.com.sgde.entity.Estados;
import br.com.sgde.util.ComboBoxList;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreencheComboBox {
    
    private ArrayList<ComboBoxList> list;
    
    private EstadoDAO dao;

    public PreencheComboBox() {
        this.dao = new EstadoDAO();
    }
        
    
    public void comboBoxEstado() {        
        this.setList(new ArrayList<>());
        List<Estados> estadosList = this.dao.list();
        for (Estados e : estadosList){
            this.getList().add(new ComboBoxList(e.getId(), e.getNome()));
        }
    }
}
