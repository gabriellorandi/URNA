package Voto;

import Candidato.Candidato;
import Eleicao.Eleicao;
import Eleitor.Eleitor;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class VotoDAO {

    public Voto cadastrarVoto(Voto v) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Voto(id,data,eleitor_id,candidato_id,eleicao_id) VALUES (?,?,?,?,?)");
            prestmt.setLong(1,v.getId());
            prestmt.setObject(2,v.getData());
            prestmt.setLong(3,v.getEleitor().getId());
            prestmt.setLong(4,v.getCandidato().getId());
            prestmt.setLong(5,v.getEleicao().getId());
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
                         "INNER JOIN Eleitor e ON e.id = eleitor_id " +
                         "INNER JOIN Candidato c ON c.id = candidato_id " +
                         "INNER JOIN Eleicao el ON el.id = eleicao_id " +
                         "FROM Voto ");

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Voto v = new Voto();

                v.setId( rs.getLong("id") );
                v.setData( rs.getObject("data", ZonedDateTime.class) );
                v.setEleitor( rs.getObject("e", Eleitor.class) );
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
