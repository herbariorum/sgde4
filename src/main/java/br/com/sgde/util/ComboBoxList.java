package br.com.sgde.util;


import java.util.ArrayList;

public class ComboBoxList {
    private Long id;
    private String name;

    public ComboBoxList(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelectedId(ArrayList<ComboBoxList> comboBoxList, String id, javax.swing.JComboBox cb){
        for (ComboBoxList nome :  comboBoxList){
            if (nome.getId().toString().equals(id)){
                cb.setSelectedItem(nome);
            }
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
}
