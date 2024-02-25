
package br.com.sgde.validation;

/**
 *
 * @author elias
 */
public class ValidaTelefone implements IValidator<String>{
    
            
    @Override
    public boolean valida(String valor) {
        return valor.matches("^\\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$");
    }
}
