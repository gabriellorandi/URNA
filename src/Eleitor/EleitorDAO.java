package Eleitor;


import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EleitorDAO {

    public Eleitor cadastrarEleitor(Eleitor e) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Eleitor(id,nome,cpf) VALUES (?,?,?)");
            prestmt.setLong(1,e.getId());
            prestmt.setString(2,e.getNome());
            prestmt.setLong(3,e.getCpf());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
        return e;
    }

    public void removerEleitor(Long id) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Eleitor WHERE id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
    }

    public List<Eleitor> selecionarEleitores() {

        List<Eleitor> eleitores = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,nome,cpf FROM Eleitor ");

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Eleitor e = new Eleitor();

                e.setId( rs.getLong("id") );
                e.setNome( rs.getString("nome") );
                e.setCpf( rs.getLong("cpf") );

                eleitores.add(e);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
         return eleitores;
    }

}
