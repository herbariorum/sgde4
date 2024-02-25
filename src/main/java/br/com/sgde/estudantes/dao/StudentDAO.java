package br.com.sgde.estudantes.dao;

import Database.DB;
import br.com.sgde.Exceptions.ExceptionDAO;
import br.com.sgde.dao.GenericDAO;
import br.com.sgde.entity.Endereco;
import br.com.sgde.estudantes.entity.Student;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO implements GenericDAO<Student> {

    /**
     *
     * @param entity
     * @return
     * @throws ExceptionDAO
     */
    @Override
    public int save(Student entity) throws ExceptionDAO {
        var insertStudent = 0;
        var insertEndereco = 0;
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlInsert,
                Statement.RETURN_GENERATED_KEYS);) {
            List<Student> lista = listByCpf(entity.getCpf());
            if (lista.isEmpty()) {
                stmt.setString(1, entity.getNome());
                stmt.setString(2, entity.getCpf());
                stmt.setDate(3, Date.valueOf(entity.getDta_nasc()));
                stmt.setString(4, entity.getSexo());
                stmt.setBoolean(5, entity.isStatus());
                stmt.setString(6, entity.getNomeMae());
                stmt.setString(7, entity.getNomePai());
                stmt.setString(8, entity.getTelefoneMae());
                stmt.setString(9, entity.getTelefonePai());
                stmt.setString(10, entity.getNacionalidade());
                stmt.setString(11, entity.getUfNascimento());
                stmt.setString(12, entity.getCidadeNascimento());
                insertStudent = stmt.executeUpdate();
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long idStudent = generatedKeys.getLong(1);
                        try (var stmtend = connection.prepareStatement(sqlInsertEndereco, Statement.RETURN_GENERATED_KEYS);) {
                            Endereco endereco = entity.getEndereco();
                            stmtend.setString(1, endereco.getLogradouro());
                            stmtend.setString(2, endereco.getNumero());
                            stmtend.setString(3, endereco.getComplemento());
                            stmtend.setString(4, endereco.getBairro());
                            stmtend.setString(5, endereco.getEstado());
                            stmtend.setString(6, endereco.getCidade());
                            stmtend.setString(7, endereco.getCep());
                            stmtend.setLong(8, idStudent);
                            insertEndereco = stmtend.executeUpdate();
                        }
                    }
                    if ((insertStudent > 0) && (insertEndereco > 0)) {
                        return 1;
                    }
                }
            } else {
                throw new ExceptionDAO("Este cpf já está cadastrado na base de dados.");
            }

        } catch (SQLException ex) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao salvar estudantes\n" + ex.getMessage());
        }

        return -1;
    }

    @Override
    public int update(Student entity) throws ExceptionDAO {
        var updateStudent = 0;
        var updateEndereco = 0;
        Long idStudent = entity.getId();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlUpdate);) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getCpf());
            stmt.setDate(3, Date.valueOf(entity.getDta_nasc()));
            stmt.setString(4, entity.getSexo());
            stmt.setBoolean(5, entity.isStatus());
            stmt.setString(6, entity.getNomeMae());
            stmt.setString(7, entity.getNomePai());
            stmt.setString(8, entity.getTelefoneMae());
            stmt.setString(9, entity.getTelefonePai());
            stmt.setString(10, entity.getNacionalidade());
            stmt.setString(11, entity.getUfNascimento());
            stmt.setString(12, entity.getCidadeNascimento());
            stmt.setLong(13, idStudent);
            updateStudent = stmt.executeUpdate();

            try (var stmtend = connection.prepareStatement(sqlUpdateEndereco);) {
                Endereco endereco = entity.getEndereco();
                stmtend.setString(1, endereco.getLogradouro());
                stmtend.setString(2, endereco.getNumero());
                stmtend.setString(3, endereco.getComplemento());
                stmtend.setString(4, endereco.getBairro());
                stmtend.setString(5, endereco.getEstado());
                stmtend.setString(6, endereco.getCidade());
                stmtend.setString(7, endereco.getCep());
                stmtend.setLong(8, idStudent);
                stmtend.setLong(9, endereco.getIdaddress());
                updateEndereco = stmtend.executeUpdate();

            }
            if ((updateStudent > 0) && (updateEndereco > 0)) {

                return updateStudent;
            }

        } catch (SQLException ex) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao atualizar o estudante\n" + ex.getSQLState() + ex.getMessage());
        }

        return -1;
    }

    @Override
    public int delete(Student entity) throws ExceptionDAO {
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlDelete);) {
            stmt.setLong(1, entity.getId());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao tentar excluir o estudante\n" + ex.getMessage());
        }
    }

    @Override
    public List<Student> list() throws ExceptionDAO {
        List<Student> students = new ArrayList<>();
        Map<Integer, Endereco> map = new HashMap<>();
        try (var connection = DB.getConexao(); var stmt = connection.createStatement();) {
            try (var rs = stmt.executeQuery(sqlFindAll)) {
                while (rs.next()) {
                    Endereco end = map.get(rs.getInt("student_id"));
                    if (end == null) {
                        end = instancializaEndereco(rs);
                        map.put(rs.getInt("student_id"), end);
                    }
                    Student student = instancializaStudent(rs, end);

                    students.add(student);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao listar estudantes\n" + e.getMessage());
        }
        return students;
    }

    @Override
    public Student findById(final Long id) throws ExceptionDAO {
        Student student = null;
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlFindByID);) {
            stmt.setLong(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var endereco = new Endereco();
                    endereco = instancializaEndereco(rs);
                    student = instancializaStudent(rs, endereco);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar pelo ID.\n" + e.getMessage());
        }
        return student;
    }

    @Override
    public List<Student> listByValue(final String value) throws ExceptionDAO {
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlFindByName);) {
            stmt.setString(1, "%" + value.toLowerCase() + "%");
            try (var rs = stmt.executeQuery()) {
                List<Student> students = new ArrayList<>();
                Map<Integer, Endereco> map = new HashMap<>();
                while (rs.next()) {
                    Endereco end = map.get(rs.getInt("student_id"));
                    if (end == null) {
                        end = instancializaEndereco(rs);
                        map.put(rs.getInt("student_id"), end);
                    }
                    Student student = instancializaStudent(rs, end);
                    students.add(student);
                }
                return students;
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar pelo nome.\n" + e.getMessage());
        }

    }

    public List<Student> listByCpf(final String cpf) throws SQLException, ExceptionDAO {
        List<Student> students = new ArrayList<>();
        Map<Integer, Endereco> map = new HashMap<>();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlFindToCpf);) {
            stmt.setString(1, cpf);
            try (var rs = stmt.executeQuery()) {
//                students = convertToList(rs); 
                while (rs.next()) {
                    Endereco end = map.get(rs.getInt("student_id"));
                    if (end == null) {
                        end = instancializaEndereco(rs);
                        map.put(rs.getInt("student_id"), end);
                    }
                    Student student = instancializaStudent(rs, end);

                    students.add(student);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar pelo nome.\n" + e.getMessage());
        }
        return students;
    }

    private Student instancializaStudent(ResultSet rs, Endereco endereco) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setNome(rs.getString("nome"));
        student.setCpf(rs.getString("cpf"));
        student.setDta_nasc(rs.getDate("dta_nasc").toLocalDate());
        student.setSexo(rs.getString("sexo"));
        student.setStatus(rs.getBoolean("status"));
        student.setNomeMae(rs.getString("nomeMae"));
        student.setTelefoneMae(rs.getString("telefoneMae"));
        student.setNomePai(rs.getString("nomePai"));
        student.setTelefonePai(rs.getString("telefonePai"));
        student.setNacionalidade(rs.getString("nacionalidade"));
        student.setUfNascimento(rs.getString("ufNascimento"));
        student.setCidadeNascimento(rs.getString("cidadeNascimento"));
        student.setEndereco(endereco);
        return student;
    }

    private Endereco instancializaEndereco(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setIdaddress(rs.getLong("idaddress"));
        endereco.setLogradouro(rs.getString("logradouro"));
        endereco.setNumero(rs.getString("numero"));
        endereco.setComplemento(rs.getString("complemento"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setEstado(rs.getString("estado"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setCep(rs.getString("cep"));
        endereco.setStudent_id(rs.getInt("student_id"));
        return endereco;
    }

    private final String sqlFindAll = "SELECT * FROM student";
    private final String sqlFindToCpf = "select est.*, address.* FROM student est INNER JOIN endereco address ON est.id = address.student_id WHERE est.cpf = ?";
    private final String sqlFindByID = "select est.*, address.* FROM student est INNER JOIN endereco address ON est.id = address.student_id WHERE est.id = ?;";
    private final String sqlDelete = "DELETE FROM student WHERE id = ?";
    private final String sqlFindByName = "select est.*, address.* FROM student est INNER JOIN endereco address ON est.id = address.student_id WHERE LOWER(est.nome) Like ? ORDER BY est.nome";
    private final String sqlInsert = "INSERT INTO student(nome, cpf, dta_nasc, "
            + "sexo, status, nomeMae, nomePai, telefoneMae, telefonePai, nacionalidade,"
            + "ufNascimento, cidadeNascimento) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String sqlUpdate = "UPDATE student SET nome = ?, cpf = ?, dta_nasc = ?, "
            + "sexo = ?, status = ?, nomeMae = ?, nomePai = ?, telefoneMae = ?, telefonePai = ? ,"
            + "nacionalidade = ?, ufNascimento = ?, cidadeNascimento = ? WHERE id = ?";

    private final String sqlInsertEndereco = "INSERT INTO endereco(logradouro, numero, complemento,"
            + "bairro, estado, cidade, cep, student_id) VALUES (?,?,?,?,?,?,?,?)";

    private final String sqlUpdateEndereco = "UPDATE endereco SET logradouro = ?, "
            + "numero = ?, complemento = ?,"
            + "bairro = ?, estado = ?, cidade = ?, cep = ?, student_id = ? WHERE idaddress = ?";
}
