package Mesario;

import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesarioDAO {

    public Mesario cadastrarMesario(Mesario m) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Mesario(id,nome,login,senha) VALUES (?,?,?,?)");
            prestmt.setLong(1,m.getId());
            prestmt.setString(2,m.getNome());
            prestmt.setString(3,m.getLogin());
            prestmt.setString(4,m.getSenha());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
        return m;
    }

    public void removerMesario(Long id) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Mesario WHERE id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
    }

    public List<Mesario> selecionarMesarios() {

        List<Mesario> mesarios = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,nome,login,senha FROM Mesario ");

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Mesario m = new Mesario();

                m.setId( rs.getLong("id") );
                m.setNome( rs.getString("nome") );
                m.setLogin( rs.getString("login") );
                m.setSenha( rs.getString("senha") );

                mesarios.add(m);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return mesarios;
    }

}
