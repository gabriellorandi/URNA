package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLJDBC {

    public static Connection conectar( )  {

        Connection c;

        try {
            Class.forName("org.postgresql.Driver");
            c = (DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/urna",
                            "postgres", "123321"));
            return c;
        } catch ( Exception e ) {
            System.err.println( " Erro ao abrir o banco de dados " );
            System.exit(0);
            return null;
        }
    }

}
