package br.com.sgde.dao;

import Database.DB;
import br.com.sgde.Exceptions.ExceptionDAO;
import br.com.sgde.entity.Estados;
import br.com.sgde.util.ComboBoxList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO implements GenericDAO<Estados> {

    private ArrayList<ComboBoxList> list;

    public ArrayList<ComboBoxList> getList() {
        return list;
    }

    public void setList(ArrayList<ComboBoxList> list) {
        this.list = list;
    }



    @Override
    public List<Estados> list() {
        var estados = new ArrayList<Estados>();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement("SELECT id, nome FROM estados ORDER BY nome");) {
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    var estado = new Estados();
                    estado.setId(rs.getLong("id"));
                    estado.setNome(rs.getString("nome"));
                    estados.add(estado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estados;
    }

    @Override
    public Estados findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Estados> listByValue(String value) {
        return null;
    }
    
    public Estados listByName(String value) {
        var estado = new Estados();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement("SELECT * FROM estados WHERE nome = ?");) {
            stmt.setString(1, value);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    estado = new Estados(rs.getLong("id"), rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }

    public void comboBoxEstado() {
        this.setList(new ArrayList<>());
        List<Estados> estadosList = list();
        for (Estados e : estadosList){
            this.getList().add(new ComboBoxList(e.getId(), e.getNome()));
        }
    }

    @Override
    public int save(Estados entity) throws ExceptionDAO {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Estados entity) throws ExceptionDAO {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Estados entity) throws ExceptionDAO {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
