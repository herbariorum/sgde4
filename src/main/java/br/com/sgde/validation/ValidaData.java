
package br.com.sgde.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class ValidaData implements IValidator<String>{

    private final String dateFormat; 
    
    public ValidaData(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    

    @Override
    public boolean valida(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try{
            sdf.parse(dateStr);
        }catch (ParseException e){
            return false;
        }
        return true;
    }
    
}
