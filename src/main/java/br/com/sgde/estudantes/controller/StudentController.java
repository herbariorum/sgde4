package br.com.sgde.estudantes.controller;

import br.com.sgde.Exceptions.ExceptionDAO;
import br.com.sgde.estudantes.dao.StudentDAO;
import br.com.sgde.estudantes.entity.Student;
import java.util.List;

public class StudentController {

    private final StudentDAO studentDAO;

    public StudentController() {
        this.studentDAO = new StudentDAO();

    }

    public List<Student> getAll() {
        List<Student> lista = null;
        try {
            lista = this.studentDAO.list();
        } catch (Exception e) {
        }
        return lista;
    }

    
    public Student getPeloID(Long id) throws ExceptionDAO {       
        Student student = (Student) this.studentDAO.findById(id);
        return student;
    }

    public List<Student> buscaPeloValor(String valor) throws ExceptionDAO {
        List<Student> lista = null;
        lista = this.studentDAO.listByValue(valor);
        return lista;
    }

    public void salvaNoBD(Student student) throws ExceptionDAO {
        if (student.getId() == null) {
            this.studentDAO.save(student);
        }else{
            this.studentDAO.update(student);
        }
    }
    
    public void removeItem(Student student) throws ExceptionDAO{
        this.studentDAO.delete(student);
    }
}

