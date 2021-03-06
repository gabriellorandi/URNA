package Candidato;

import Cargo.Cargo;
import Chapa.Chapa;
import Eleicao.Eleicao;
import Utils.AlertUtils;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO {


    public Candidato procurarCandidato(Eleicao e, Cargo c, Long votoId) {

        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT c.id as id,c.nome as nome,c.cpf as cpf," +
                            " cargo.id as cargoId,cargo.nome as cargoNome," +
                            " chapa.id as chapaId,chapa.sigla as chapaSigla,chapa.nome as chapaNome " +
                            " FROM Candidato c " +
                            " INNER JOIN Cargo cargo ON cargo.id = cargo_id " +
                            " INNER JOIN Chapa chapa ON chapa.id = chapa_id " +
                            " INNER JOIN Eleicao e ON e.id = c.eleicao_id " +
                            " WHERE e.id = ? AND c.id = ? AND cargo.id = ? ");

            prestmt.setLong(1, e.getId() );
            prestmt.setLong(2, votoId );
            prestmt.setLong(3,c.getId());

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Candidato cand = new Candidato();

                cand.setId( rs.getLong("id") );
                cand.setNome( rs.getString("nome") );
                cand.setCpf( rs.getLong("cpf") );

                Cargo cargo = new Cargo();
                cargo.setId( rs.getLong("cargoId") );
                cargo.setNome(  rs.getString("cargoNome") );

                Chapa chapa = new Chapa();
                chapa.setId(rs.getLong("chapaId"));
                chapa.setSigla(  rs.getString("chapaSigla") );
                chapa.setNome(  rs.getString("chapaNome") );

                cand.setCargo(cargo);
                cand.setChapa(chapa);

                prestmt.close();
                return cand;
            }


        }catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }

        return null;

    }

    public Candidato cadastrarCandidato(Candidato c, Eleicao e) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "INSERT INTO Candidato(id,nome,cpf,chapa_id,cargo_id,eleicao_id) " +
                            "VALUES (?,?,?,?,?,?)");
            prestmt.setLong(1,c.getId());
            prestmt.setString(2,c.getNome());
            prestmt.setLong(3,c.getCpf());
            prestmt.setLong(4,c.getChapa().getId());
            prestmt.setLong(5,c.getCargo().getId());
            prestmt.setLong(6,e.getId());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
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

    public List<Candidato> selecionarCandidatos(Eleicao eleicao) {

        List<Candidato> candidatoes = new ArrayList<>();

        if(eleicao == null) {
            return candidatoes;
        }

        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT c.id as id,c.nome as nome,c.cpf as cpf," +
                            " cargo.id as cargoId,cargo.nome as cargoNome," +
                            " chapa.id as chapaId,chapa.sigla as chapaSigla,chapa.nome as chapaNome " +
                            " FROM Candidato c " +
                            " INNER JOIN Cargo cargo ON cargo.id = cargo_id " +
                            " INNER JOIN Chapa chapa ON chapa.id = chapa_id " +
                            " WHERE c.eleicao_id = ? ");
            prestmt.setLong(1,eleicao.getId());

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Candidato c = new Candidato();

                c.setId( rs.getLong("id") );
                c.setNome( rs.getString("nome") );
                c.setCpf( rs.getLong("cpf") );

                Cargo cargo = new Cargo();
                cargo.setId( rs.getLong("cargoId") );
                cargo.setNome(  rs.getString("cargoNome") );

                Chapa chapa = new Chapa();
                chapa.setId(rs.getLong("chapaId"));
                chapa.setSigla(  rs.getString("chapaSigla") );
                chapa.setNome(  rs.getString("chapaNome") );

                c.setCargo(cargo);
                c.setChapa(chapa);

                candidatoes.add(c);
            }

            prestmt.close();
        }catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return candidatoes;
    }

}
