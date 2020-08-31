package Eleicao;

import Mesario.Mesario;
import Utils.AlertUtils;
import Utils.PostgreSQLJDBC;
import javafx.scene.control.Alert;

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
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
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
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
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
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return eleicoes;
    }

    public Eleicao selecionarEleicaoMesario(Mesario mesario) {

        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT e.id as id, e.dia as dia FROM Eleicao e " +
                            "INNER JOIN Mesario m ON m.eleicao_id = e.id  " +
                            "WHERE m.id = ? ");
            prestmt.setLong(1,mesario.getId());

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Eleicao e = new Eleicao();

                e.setId( rs.getLong("id") );
                e.setDia( rs.getDate( "dia" ).toLocalDate() );

                return e;
            }

            prestmt.close();
        }catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return null;

    }
}
