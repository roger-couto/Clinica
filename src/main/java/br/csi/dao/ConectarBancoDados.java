package br.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDados {

     public static Connection ConectarBancoPostgres()
             throws ClassNotFoundException, SQLException {

         Class.forName("org.postgresql.Driver");
         System.out.println("Driver loaded");

         String url = "jdbc:postgresql://localhost:5432/postgres";
         String user = "postgres";
         String senha = "1234";

         Connection conn = DriverManager.getConnection(url, user, senha);
         return conn;

     }

}
