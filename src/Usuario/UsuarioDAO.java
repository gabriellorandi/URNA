package Usuario;

import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {


    public boolean isAdmin(String login, String senha) {

        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT id,login,senha,admin FROM Usuario WHERE login LIKE ? AND senha LIKE ? AND admin = TRUE ");

            prestmt.setString(1,login.toLowerCase());
            prestmt.setString(2,senha.toLowerCase());

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                prestmt.close();
                return true;
            }

            return false;


        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return false;
    }

}
