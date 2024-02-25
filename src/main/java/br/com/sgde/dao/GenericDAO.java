
package br.com.sgde.dao;

import br.com.sgde.Exceptions.ExceptionDAO;
import java.util.List;


public abstract interface GenericDAO<T> {
    
    public abstract int save(T entity) throws ExceptionDAO;
    
    public abstract int update(T entity) throws ExceptionDAO;
    
    public abstract int delete(T entity) throws ExceptionDAO;
    
    public abstract List<T> list() throws ExceptionDAO;
    
    public abstract T findById(Long id) throws ExceptionDAO;
    
    public abstract List<T> listByValue(String value) throws ExceptionDAO;
}
