
package br.com.sgde.dao;

import Database.DB;
import br.com.sgde.Exceptions.ExceptionDAO;
import br.com.sgde.entity.Cidades;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CidadeDAO implements GenericDAO<Cidades>{



    @Override
    public Cidades findById(Long id)  throws ExceptionDAO{
        Cidades cidade = null;  
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlFindById);) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()){
                    cidade = new Cidades();
                    cidade.setId(rs.getLong("id"));
                    cidade.setNome(rs.getString("nome"));
                    cidade.setEstado_id(rs.getInt("estado_id"));
                }
            }
        }catch(SQLException e){
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar a UF.\n" + e.getMessage());
        }
        return cidade;
    }

    @Override
    public List<Cidades> listByValue(String value) {
       return null;
    }
    
    public List<Cidades> getByCodigoUf(int value) throws ExceptionDAO{
        List<Cidades> cidadeList = new ArrayList<>();       
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlBuscaPorValor);) {
            stmt.setInt(1, value);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    var municipio = new Cidades(rs.getLong("id"), rs.getString("nome"), rs.getInt("estado_id"));
                    cidadeList.add(municipio);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar a UF.\n" + e.getMessage());
        }
        return cidadeList;
    }
    
    private final String sqlFindAll = "SELECT * FROM Cidades";
    private final String sqlFindById = "SELECT * FROM Cidades WHERE id = ?";
    private final String sqlBuscaPorValor = "SELECT id, nome, estado_id FROM cidades WHERE estado_id = ? ORDER BY nome";

    @Override
    public int save(Cidades entity) throws ExceptionDAO {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Cidades entity) throws ExceptionDAO {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Cidades entity) throws ExceptionDAO {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cidades> list() throws ExceptionDAO {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
