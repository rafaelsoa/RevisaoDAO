package br.senac.rn.dao;

import br.senac.rn.model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {
   

    
    private final DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public ProdutoDAO(){
        db= new DataBase();
    }
    
    
    public boolean insert(Produto produto) {
       if (db.open()){
           sql= "INSERT INTO produto (NOME, valor, id_categoria) VALUES (?, ?, ?);";
           try{
              ps= db.conexao.prepareStatement(sql);
              ps.setString(1, produto.getNome());
              ps.setFloat(2,produto.getValor());
              ps.setInt(3, produto.getCategoria().getId());
              
            
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
   public boolean delete (Produto produto){
       if (db.open()){
        sql= "DELETE FROM produto WHERE ID = ? ";
           try{
               ps = db.conexao.prepareStatement(sql);
               ps.setInt(1,produto.getId());
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
   
   public boolean update (Produto produto){
       if (db.open()){
        sql= "UPDATE produto SET NOME = ?, valor=?, id_categoria=? WHERE ID = ?;";
           try{
               ps = db.conexao.prepareStatement(sql);
               ps.setString(1, produto.getNome());
               ps.setFloat(2,produto.getValor());
               ps.setInt (3, produto.getCategoria().getId());
   
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
   
   public List<Produto> selectAll(){
     if (db.open()){
       List<Produto> produtos = new ArrayList();
       CategoriaDAO categoriadao = new CategoriaDAO();
       sql = "SELECT * FROM produto";
       try {
           ps = db.conexao.prepareStatement(sql);
           rs= ps.executeQuery();
           
           while (rs.next()){
                 Produto produto = new Produto();
                 produto.setId(rs.getInt(1));
                 produto.setNome(rs.getString(2));
                 produto.setValor(rs.getFloat(3));
                 produto.setCategoria(categoriadao.selectId(rs.getInt(4)));
                 
                 produtos.add(produto);    
           
           }
           rs.close();
           ps.close();
           db.close();
           return produtos;
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
       
   }
   public List<Produto> selectFilter(String filter){
   if (db.open()){
       List<Produto> produtos = new ArrayList();
       String filtro = "%"+filter +"%";
       sql = "SELECT * FROM SEXO WHERE NOME LIKE ? OR SIGLA LIKE ? ;";
       CategoriaDAO categoriadao = new CategoriaDAO();
       try {
           ps = db.conexao.prepareStatement(sql);
           ps.setString(1, filtro);
           ps.setString(2, filtro);
           rs= ps.executeQuery();
           while (rs.next()){
                 Produto produto = new Produto();
                 produto.setId(rs.getInt(1));
                 produto.setNome(rs.getString(2));
                 produto.setValor(rs.getFloat(3));
                 produto.setCategoria(categoriadao.selectId(rs.getInt(4)));
                 produtos.add(produto); 
                
           }
           rs.close();
           ps.close();
           db.close();
           return produtos;
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
   
   }
   
   public Produto selectId(int id){
   if (db.open()){
       sql = "SELECT * FROM produto WHERE ID = ?;";
       CategoriaDAO categoriadao = new CategoriaDAO();
       try {
           ps = db.conexao.prepareStatement(sql);
           ps.setInt(1, id);
           rs= ps.executeQuery();  
           if (rs.next()){
                 Produto produto = new Produto();
                 produto.setId(rs.getInt(1));
                 produto.setNome(rs.getString(2));
                 produto.setValor(rs.getFloat(3));
                 produto.setCategoria(categoriadao.selectId(rs.getInt(4)));
                 
                 
                  
                 rs.close();
                 ps.close();
                 db.close();    
                 return produto;
           }
         
       }catch (SQLException error){
          System.out.println("ERRO: "+ error.toString());
       
       }
     
     }
       db.close();
         return null;
   
   }
   
    
    
    
}
    
    
    
    
    
