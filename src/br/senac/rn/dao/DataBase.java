
package br.senac.rn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
  
    public Connection conexao = null;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String BANCO = URL + "revisao_db";
    private final String USUARIO = "root";
    private final String SENHA = "senac";

    public boolean open() {
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(BANCO, USUARIO, SENHA);
            System.out.println("Conectou");
            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("Driver não encontrado!");
            return false;
        } catch (SQLException erro) {
            System.out.println("Falha ao conectar!");
            return false;
        }
    }

    public void close(){
        try {
            conexao.close();
            System.out.println("Desconectou");
        } catch (SQLException erro) {
            System.out.println("Falha ao desconectar");
            System.out.println("Erro: " + erro.toString());
        }


        
    }
    
    
    
}
