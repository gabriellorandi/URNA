package Eleicao;

import Candidato.Candidato;
import Secao.Secao;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EleicaoDAO {

    public Eleicao cadastrarEleicao(Eleicao e) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Eleicao(dia) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            prestmt.setDate(1, java.sql.Date.valueOf( e.getDia() ) );
            prestmt.executeUpdate();

            ResultSet ids = prestmt.getGeneratedKeys();
            if (ids.next()) {
                Long eleicaoId =  ids.getLong("id");

                for(Candidato candidato : e.getCandidatos()) {

                    prestmt = conn.prepareStatement("INSERT INTO Eleicao_Candidato(eleicao_id,candidato_id) VALUES (?,?)");
                    prestmt.setLong(1,eleicaoId);
                    prestmt.setLong(2,candidato.getId());

                    prestmt.execute();

                }

                for(Secao secao : e.getSecoes()) {

                    prestmt = conn.prepareStatement("INSERT INTO Eleicao_Secao(eleicao_id,secao_id) VALUES (?,?)");
                    prestmt.setLong(1,eleicaoId);
                    prestmt.setLong(2,secao.getId());

                    prestmt.execute();

                }
            }

            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
        return e;
    }

    public void removerEleicao(Long id)  {

        try {
            Connection conn = PostgreSQLJDBC.conectar();

            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Eleicao_Candidato WHERE eleicao_id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();

            prestmt = conn.prepareStatement("DELETE FROM Eleicao WHERE id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();

            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
    }

    public List<Eleicao> selecionarEleicoes() {

        List<Eleicao> eleicoes = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,dia FROM Eleicao ");

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Eleicao e = new Eleicao();

                e.setId( rs.getLong("id") );
                e.setDia( rs.getDate( "dia" ).toLocalDate() );

                eleicoes.add(e);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return eleicoes;
    }

}
