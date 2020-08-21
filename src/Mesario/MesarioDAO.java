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
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Mesario(nome,cpf,login,senha,admin) VALUES (?,?,?,?,?)");
            prestmt.setString(1,m.getNome());
            prestmt.setString(2,m.getCpf());
            prestmt.setString(3,m.getLogin());
            prestmt.setString(4,m.getSenha());
            prestmt.setBoolean(5,m.isAdmin());
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
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,nome,cpf,login,senha FROM Mesario ");

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Mesario m = new Mesario();

                m.setId( rs.getLong("id") );
                m.setNome( rs.getString("nome") );
                m.setCpf( rs.getString("cpf") );
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
