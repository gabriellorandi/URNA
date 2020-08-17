package Secao;

import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecaoDAO {

    public Secao cadastrarSecao(Secao s) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Secao(id,) VALUES (?,?,?,?)");
            prestmt.setLong(1,s.getId());
            prestmt.setString(2,s.getLogradouro());
            prestmt.setInt(3,s.getNumero());
            prestmt.setString(4,s.getComplemento());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
        return s;
    }

    public void removerSecao(Long id) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Secao WHERE id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
    }

    public List<Secao> selecionarMesarios() {

        List<Secao> mesarios = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,nome,login,senha FROM Secao ");

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Secao s = new Secao();

                s.setId( rs.getLong("id") );
                s.setLogradouro( rs.getString("logradouro") );
                s.setNumero( rs.getInt("numero") );
                s.setComplemento( rs.getString("complemento") );

                mesarios.add(s);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return mesarios;
    }

}
