
package br.senac.rn.dao;

import br.senac.rn.model.Categoria;
import br.senac.rn.model.Cliente;
import br.senac.rn.model.Produto;
import br.senac.rn.model.Sexo;
import br.senac.rn.model.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    
    private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public VendaDAO(){
        db= new DataBase();
    }
    
     public boolean insert(Categoria categoria) {
       if (db.open()){
           sql= "INSERT INTO venda (id_cliente, valor) VALUES (?, ?);";
           Venda venda = new Venda();
           try{
              ps= db.conexao.prepareStatement(sql);
              ps.setInt(1, venda.getCliente().getId());
              ps.setFloat(2, venda.getValor());
              ps.executeUpdate();
              
              List<Produto> lista = new ArrayList();
              lista = venda.getProdutos();
              for(Produto p: venda.getProdutos()){
                  sql = "INSERT INTO produto_venda (id_venda, id_produto) VALUES (?, ?);";
                  ps = db.conexao.prepareStatement(sql);
                  ps.setInt(1, venda.getId());
                  ps.setInt(2, p.getId());
                  ps.executeUpdate();
              }
        
           } catch (SQLException error){
             System.out.println("ERRO: "+ error.toString());
           
           }
      }
       db.close();
       return false;
    
}
     
      public List<Venda> selectAll(){
     if (db.open()){
       List<Venda> vendas = new ArrayList();
       sql = "SELECT * FROM venda join produto_venda on id = id_venda";
       ClienteDAO clientedao = new ClienteDAO();
       try {
           ps = db.conexao.prepareStatement(sql);
           rs= ps.executeQuery();
           while (rs.next()){
                 Venda venda = new Venda();
                 venda.setId(rs.getInt("id"));
                 venda.setCliente(clientedao.selectId(rs.getInt("id_cliente")));
                 venda.setValor(rs.getFloat("valor"));
                 String sql2 = "select* from venda_produto where id_venda=  ?";
                 PreparedStatement ps2 = db.open.PreparedStatement(sql2);
                 
                 
        
                 
                 Cliente
                 vendas.add(venda);
                 
                 
                 vendas.add(venda);    
           
           }
           rs.close();
           ps.close();
           db.close();
           return sexos;
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
       
   }
     
     
     
     
     
}
