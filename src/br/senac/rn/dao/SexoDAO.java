
package br.senac.rn.dao;

import br.senac.rn.model.Sexo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SexoDAO {
    
    private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public SexoDAO(){
        db= new DataBase();
    }
    
    
    public boolean insert(Sexo sexo) {
       if (db.open()){
           sql= "INSERT INTO sexo (NOME, SIGLA) VALUES (?, ?);";
           try{
              ps= db.conexao.prepareStatement(sql);
              ps.setString(1, sexo.getNome());
              ps.setString(2,sexo.getSigla());
         
       
              
              
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
   public boolean delete (Sexo sexo){
       if (db.open()){
        sql= "DELETE FROM SEXO WHERE ID = ? ";
           try{
               ps = db.conexao.prepareStatement(sql);
               ps.setInt(1,sexo.getId());
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
   
   public boolean update (Sexo sexo){
       if (db.open()){
        sql= "UPDATE SEXO SET NOME = ?, SIGLA=? WHERE ID = ?;";
           try{
               ps = db.conexao.prepareStatement(sql);
               ps.setString(1, sexo.getNome());
               ps.setString(2,sexo.getSigla());
   
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
   
   public List<Sexo> selectAll(){
     if (db.open()){
       List<Sexo> sexos = new ArrayList();
       sql = "SELECT * FROM sexos";
       try {
           ps = db.conexao.prepareStatement(sql);
           rs= ps.executeQuery();
           while (rs.next()){
                 Sexo sexo = new Sexo();
                 sexo.setNome(rs.getString(1));
                 sexo.setSigla(rs.getString(2));
              
                 
                 sexos.add(sexo);    
           
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
   public List<Sexo> selectFilter(String filter){
   if (db.open()){
       List<Sexo> sexos = new ArrayList();
       String filtro = "%"+filter +"%";
       sql = "SELECT * FROM SEXO WHERE NOME LIKE ? OR SIGLA LIKE ? ;";
       try {
           ps = db.conexao.prepareStatement(sql);
           ps.setString(1, filtro);
           ps.setString(2, filtro);
           rs= ps.executeQuery();
           while (rs.next()){
                 Sexo sexo = new Sexo();
                 sexo.setNome(rs.getString(1));
                 sexo.setSigla(rs.getString(2));
                
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
   
   public Sexo select(int id){
   if (db.open()){
       sql = "SELECT * FROM SEXO WHERE ID = ?;";
       
       try {
           ps = db.conexao.prepareStatement(sql);
           ps.setInt(1, id);
           rs= ps.executeQuery();  
           if (rs.next()){
                 Sexo sexo = new Sexo();
                 sexo.setNome(rs.getString(1));
                 sexo.setSigla(rs.getString(2));
                 
                  
                 rs.close();
                 ps.close();
                 db.close();    
                 return sexo;
           }
         
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
   
   }
   
    
    
    
}
    
    


    
    

