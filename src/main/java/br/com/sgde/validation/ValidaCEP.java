
package br.com.sgde.validation;


public class ValidaCEP implements IValidator<String>{

    @Override
    public boolean valida(String valor) {
        return valor.matches("[0-9]{5}-[0-9]{3}");
    }
    
    
}
