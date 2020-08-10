package Repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQLJDBC {

    public static Connection conectar( ) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/urna",
                            "adm", "123321");
            return c;
        } catch ( Exception e ) {
            System.err.println( " Erro ao abrir o banco de dados " );
            System.exit(0);
            return null;
        }
    }

}
