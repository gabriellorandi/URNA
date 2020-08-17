package Candidato;

import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO {

    public Candidato cadastrarCandidato(Candidato c) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Candidato(id,nome,cpf) VALUES (?,?,?)");
            prestmt.setLong(1,c.getId());
            prestmt.setString(2,c.getNome());
            prestmt.setLong(3,c.getCpf());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
        return c;
    }

    public void removerCandidato(Long id) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Candidato WHERE id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
    }

    public List<Candidato> selecionarCandidatos() {

        List<Candidato> candidatoes = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,nome,cpf FROM Candidato ");

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Candidato c = new Candidato();

                c.setId( rs.getLong("id") );
                c.setNome( rs.getString("nome") );
                c.setCpf( rs.getLong("cpf") );

                candidatoes.add(c);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return candidatoes;
    }

}
