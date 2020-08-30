package Mesario;

import Eleicao.Eleicao;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesarioDAO {

    public Mesario cadastrarMesario(Mesario m, Eleicao e) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Mesario(nome,cpf,login,senha,admin,eleicao_id) VALUES (?,?,?,?,?,?)");
            prestmt.setString(1,m.getNome());
            prestmt.setString(2,m.getCpf());
            prestmt.setString(3,m.getLogin());
            prestmt.setString(4,m.getSenha());
            prestmt.setBoolean(5,m.isAdmin());
            prestmt.setLong(6,e.getId());
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

    public Mesario selecionarMesario(Long id) {

        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,nome,cpf,login,senha FROM Mesario WHERE id = ? AND admin = FALSE ");
            prestmt.setLong(1,id);

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Mesario m = new Mesario();

                m.setId( rs.getLong("id") );
                m.setNome( rs.getString("nome") );
                m.setCpf( rs.getString("cpf") );
                m.setLogin( rs.getString("login") );
                m.setSenha( rs.getString("senha") );

               return m;
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return null;
    }

    public List<Mesario> selecionarMesarios() {

        List<Mesario> mesarios = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,nome,cpf,login,senha FROM Mesario WHERE  admin = FALSE  ");

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
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

    public Long getLogin(String login, String senha, Boolean admin) {

        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT id FROM Mesario WHERE login LIKE ? AND senha LIKE ? AND admin = ? ");

            prestmt.setString(1,login.toLowerCase());
            prestmt.setString(2,senha);
            prestmt.setBoolean(3,admin);

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Long id = rs.getLong( "id" );
                prestmt.close();
                return id;
            }

            return null;


        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return null;
    }

}
