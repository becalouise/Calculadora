package Calculadora;

import java.sql.*;

public class ConexaoMySql {
    public static String URL = "jdbc:mysql://localhost:3306/calculadora";
    public static String USER = "root";
    public static String PWD = "root";

    private Connection dbconn = null;
    private Statement sqlmgr = null;

    public void openDataBase() {
        try {
            dbconn = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Conectado com sucesso em: " + URL);
            sqlmgr = dbconn.createStatement();
        } catch (Exception Error) {
            System.out.println("Error on connect: " + Error.getMessage());
        }
    }

    public void closeDatabase() throws SQLException {
        sqlmgr.close();
        dbconn.close();
    }

    public int executaQuery(String sql) {
        try {
            return sqlmgr.executeUpdate(sql);
        } catch (Exception Error) {
            System.out.println("Error on connect" + Error.getMessage());
        }
        return -1;

    }

}
