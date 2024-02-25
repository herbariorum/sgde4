package br.com.sgde.validation;


public interface IValidator<T> {
    
    boolean valida(T t);
}
