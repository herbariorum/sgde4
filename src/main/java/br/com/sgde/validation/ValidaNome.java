package br.com.sgde.validation;


public class ValidaNome implements IValidator<String>{

    @Override
    public boolean valida(String valor) {
         String ePattern = "^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(valor);
        return m.matches();
    }

    
    
}
