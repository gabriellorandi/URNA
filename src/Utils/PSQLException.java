package Utils;

import java.sql.SQLException;

public class PSQLException {

    public PSQLException (SQLException sql) {
        System.err.println( "Code: "+sql.getErrorCode()+" - Erro ao executar: "+sql.getMessage() );
        System.exit(0);
    }

}
