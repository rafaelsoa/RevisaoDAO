
package br.senac.rn.view;

import br.senac.rn.dao.SexoDAO;
import br.senac.rn.model.Sexo;


public class Principal {
    
    
    public static void main(String[] args) {
        
        Sexo s = new Sexo();
        
        s.setNome("masculino");
        s.setSigla("M");
        
        SexoDAO dao = new SexoDAO();
        
        dao.insert(s);
    }
    
    
}
