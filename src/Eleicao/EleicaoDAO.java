package Eleicao;

import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EleicaoDAO {

    public void cadastrarEleicao(Eleicao e) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Eleicao(dia) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            prestmt.setDate(1, java.sql.Date.valueOf( e.getDia() ) );
            prestmt.executeUpdate();

            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
    }

    public void removerEleicao(Long id)  {

        try {
            Connection conn = PostgreSQLJDBC.conectar();

            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Eleicao WHERE id = ?");
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
