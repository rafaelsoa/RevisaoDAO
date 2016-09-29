package br.senac.rn.dao;


import br.senac.rn.model.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {
    
    private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public CategoriaDAO(){
        db= new DataBase();
    }
    
    
    public boolean insert(Categoria categoria) {
       if (db.open()){
           sql= "INSERT INTO categoria (NOME) VALUES (?);";
           try{
              ps= db.conexao.prepareStatement(sql);
              ps.setString(1, categoria.getNome());
            
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
   public boolean delete (Categoria categoria){
       if (db.open()){
        sql= "DELETE FROM categoria WHERE ID = ? ";
           try{
               ps = db.conexao.prepareStatement(sql);
               ps.setInt(1,categoria.getId());
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
   
   public boolean update (Categoria categoria){
       if (db.open()){
        sql= "UPDATE SEXO set id = ?, SET NOME = ? WHERE ID = ?;";
           try{
               ps = db.conexao.prepareStatement(sql);
               ps.setInt (1, categoria.getId());
               ps.setString(2, categoria.getNome());
               
   
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
   
   public List<Categoria> selectAll(){
     if (db.open()){
       List<Categoria> categorias = new ArrayList();
       sql = "SELECT * FROM categoria";
       try {
           ps = db.conexao.prepareStatement(sql);
           rs= ps.executeQuery();
           while (rs.next()){
                 Categoria categoria = new Categoria();
                 categoria.setId(rs.getInt(1));
                 categoria.setNome(rs.getString(2));
           
                 
                 categorias.add(categoria);    
           
           }
           rs.close();
           ps.close();
           db.close();
           return categorias;
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
       
   }
   public List<Categoria> selectFilter(String filter){
   if (db.open()){
       List<Categoria> categorias = new ArrayList();
       String filtro = "%"+filter +"%";
       sql = "SELECT * FROM categoria WHERE NOME LIKE ?  ;";
       try {
           ps = db.conexao.prepareStatement(sql);
           ps.setString(1, filtro);
           ps.setString(2, filtro);
           rs= ps.executeQuery();
           while (rs.next()){
                 Categoria categoria = new Categoria();
                 categoria.setNome(rs.getString(1));
                 
                
           }
           rs.close();
           ps.close();
           db.close();
           return categorias;
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
   
   }
   
   public Categoria selectId(int id){
   if (db.open()){
       sql = "SELECT * FROM SEXO WHERE ID = ?;";
       
       try {
           ps = db.conexao.prepareStatement(sql);
           ps.setInt(1, id);
           rs= ps.executeQuery();  
           if (rs.next()){
                 Categoria categoria = new Categoria();
                 categoria.setNome(rs.getString(1));
                 
                  
                 rs.close();
                 ps.close();
                 db.close();    
                 return categoria;
           }
         
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
   
   }
   
    
    
    
}
    
    


    
    

