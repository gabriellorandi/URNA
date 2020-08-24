package Voto;

import Candidato.Candidato;
import Eleicao.Eleicao;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class VotoDAO {

    public Voto cadastrarVoto(Voto v) {

        Long candidatoId = (v.getCandidato()!=null)?v.getCandidato().getId():null;

        if(candidatoId != null) {
            try {
                Connection conn = PostgreSQLJDBC.conectar();
                PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Voto(data,candidato_id,eleicao_id) VALUES (?,?,?)");
                prestmt.setObject(1,v.getData());
                prestmt.setLong(2,candidatoId);
                prestmt.setLong(3,v.getEleicao().getId());
                prestmt.execute();
                prestmt.close();
            } catch (SQLException sql) {
                new PSQLException(sql);
            }
            return v;
        }

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Voto(data,eleicao_id) VALUES (?,?)");
            prestmt.setObject(1,v.getData());
            prestmt.setLong(2,v.getEleicao().getId());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
        return v;

    }

    public void removerVoto(Long id) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Voto WHERE id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
    }

    public List<Voto> selecionarVotos() {

        List<Voto> votos = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT id,data,eleitor_id,candidato_id,eleicao_id " +
                         "INNER JOIN Candidato c ON c.id = candidato_id " +
                         "INNER JOIN Eleicao el ON el.id = eleicao_id " +
                         "FROM Voto ");

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Voto v = new Voto();

                v.setId( rs.getLong("id") );
                v.setData( rs.getDate("data").toLocalDate() );
                v.setCandidato( rs.getObject("c", Candidato.class) );
                v.setEleicao(rs.getObject("el", Eleicao.class));

                votos.add(v);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return votos;
    }

}
