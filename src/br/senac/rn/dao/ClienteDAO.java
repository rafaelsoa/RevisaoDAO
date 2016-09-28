
package br.senac.rn.dao;

import br.senac.rn.model.Cliente;
import br.senac.rn.model.Sexo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
   

    
    private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public ClienteDAO(){
        db= new DataBase();
    }
    
    
    public boolean insert(Cliente cliente) {
       if (db.open()){
           sql= "INSERT INTO cliente (NOME, cpf, id_sexo) VALUES (?, ?, ?);";
           try{
              ps= db.conexao.prepareStatement(sql);
              ps.setString(1, cliente.getNome());
              ps.setString(2,cliente.getCpf());
              ps.setInt(3, cliente.getSexo().getId());
              
            
              if (ps.executeUpdate()== 1){
                 ps.close();
                 db.close();
                 return true;
              }
           } catch (SQLException error){
             System.out.println("ERRO: "+ error.toString());
           
           }
      }
       db.close();
       return false;
       
       
    }
   public boolean delete (Cliente cliente){
       if (db.open()){
        sql= "DELETE FROM cliente WHERE ID = ? ";
           try{
               ps = db.conexao.prepareStatement(sql);
               ps.setInt(1,cliente.getId());
               if(ps.executeUpdate() == 1){
                  ps.close();
                  db.close();
                  return true;
               }
           
           } catch (SQLException error){
             System.out.println("ERRO: "+ error.toString());
           
           }
      }
       db.close();
       return false;
      
   }
   
   public boolean update (Cliente cliente){
       if (db.open()){
        sql= "UPDATE cliente SET NOME = ?, cpf=?, id_sexo=? WHERE ID = ?;";
           try{
               ps = db.conexao.prepareStatement(sql);
               ps.setString(1, cliente.getNome());
               ps.setString(2,cliente.getCpf());
               ps.setInt (3, cliente.getSexo().getId());
   
               if (ps.executeUpdate() ==1){
                  ps.close();
                  db.close();
                  return true;
           }
           
           } catch (SQLException error){
             System.out.println("ERRO: "+ error.toString());
           
           }
      }
         db.close();
         return false;
       
   }
   
   public List<Cliente> selectAll(){
     if (db.open()){
       List<Cliente> clientes = new ArrayList();
       SexoDAO sexodao = new SexoDAO();
       sql = "SELECT * FROM cliente";
       try {
           ps = db.conexao.prepareStatement(sql);
           rs= ps.executeQuery();
           
           while (rs.next()){
                 Cliente cliente = new Cliente();
                 cliente.setId(rs.getInt(1));
                 cliente.setNome(rs.getString(2));
                 cliente.setCpf(rs.getString(3));
                 cliente.setSexo(sexodao.selectId(rs.getInt(4)));
                 
                 clientes.add(cliente);    
           
           }
           rs.close();
           ps.close();
           db.close();
           return clientes;
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
       
   }
   public List<Cliente> selectFilter(String filter){
   if (db.open()){
       List<Cliente> clientes = new ArrayList();
       String filtro = "%"+filter +"%";
       sql = "SELECT * FROM SEXO WHERE NOME LIKE ? OR SIGLA LIKE ? ;";
       SexoDAO sexodao = new SexoDAO();
       try {
           ps = db.conexao.prepareStatement(sql);
           ps.setString(1, filtro);
           ps.setString(2, filtro);
           rs= ps.executeQuery();
           while (rs.next()){
                 Cliente cliente = new Cliente();
                 cliente.setId(rs.getInt(1));
                 cliente.setNome(rs.getString(2));
                 cliente.setCpf(rs.getString(3));
                 cliente.setSexo(sexodao.selectId(rs.getInt(4)));
                 clientes.add(cliente); 
                
           }
           rs.close();
           ps.close();
           db.close();
           return clientes;
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
   
   }
   
   public Cliente selectId(int id){
   if (db.open()){
       sql = "SELECT * FROM cliente WHERE ID = ?;";
       SexoDAO sexodao = new SexoDAO();
       try {
           ps = db.conexao.prepareStatement(sql);
           ps.setInt(1, id);
           rs= ps.executeQuery();  
           if (rs.next()){
                 Cliente cliente = new Cliente();
                 cliente.setId(rs.getInt(1));
                 cliente.setNome(rs.getString(2));
                 cliente.setCpf(rs.getString(3));
                 cliente.setSexo(sexodao.selectId(rs.getInt(4)));
                 
                 
                  
                 rs.close();
                 ps.close();
                 db.close();    
                 return cliente;
           }
         
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
   
   }
   
    
    
    
}
    
    
    
    
    
