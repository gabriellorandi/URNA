package Voto;

import Candidato.Candidato;
import Eleicao.Eleicao;
import Secao.Secao;
import Utils.AlertUtils;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;
import javafx.scene.control.Alert;

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
                PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Voto(data,candidato_id,eleicao_id,secao_id) VALUES (?,?,?,?)");
                prestmt.setObject(1,v.getData());
                prestmt.setLong(2,candidatoId);
                prestmt.setLong(3,v.getEleicao().getId());
                prestmt.setLong(4,v.getSecao().getId());
                prestmt.execute();
                prestmt.close();
            } catch (SQLException sql) {
                AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
            }
            return v;
        }

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Voto(data,eleicao_id,secao_id) VALUES (?,?,?)");
            prestmt.setObject(1,v.getData());
            prestmt.setLong(2,v.getEleicao().getId());
            prestmt.setLong(3,v.getSecao().getId());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
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
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public List<Voto> selecionarVotos(Eleicao e, Secao s) {

        List<Voto> votos = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT v.id as id, v.data as data, c.id as candidatoId  FROM Voto v " +
                         "LEFT JOIN Candidato c ON c.id = v.candidato_id " +
                         "INNER JOIN Eleicao el ON el.id = v.eleicao_id " +
                         "INNER JOIN Secao s ON s.id = v.secao_id " +
                         " WHERE v.eleicao_id = ? AND v.secao_id = ? ");
            prestmt.setLong(1,e.getId());
            prestmt.setLong(2,s.getId());

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Voto v = new Voto();

                v.setId( rs.getLong("id") );
                v.setData( rs.getDate("data").toLocalDate() );

                Candidato candidato = new Candidato();
                candidato.setId(  rs.getLong("candidatoId") );


                v.setCandidato(candidato);

                votos.add(v);
            }

            prestmt.close();
        }catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return votos;
    }

}
