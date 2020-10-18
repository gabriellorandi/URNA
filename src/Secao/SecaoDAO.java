package Secao;

import Eleicao.Eleicao;
import Mesario.Mesario;
import Utils.AlertUtils;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SecaoDAO {

    public Secao cadastrarSecao(Secao s, Eleicao e) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Secao(logradouro,numero,mesario_id,eleicao_id) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            prestmt.setString(1,s.getLogradouro());
            prestmt.setInt(2,s.getNumero());
            prestmt.setLong(3,s.getMesario().getId());
            prestmt.setLong(4,e.getId());
            prestmt.executeUpdate();


//            ResultSet ids = prestmt.getGeneratedKeys();
//            if (ids.next()) {
//                Long secaoId =  ids.getLong("id");
//
//                for(Eleitor eleitor : s.getEleitores()) {
//
//                    prestmt = conn.prepareStatement("INSERT INTO Secao_Eleitor(secao_id,eleitor_id) VALUES (?,?)");
//                    prestmt.setLong(1,secaoId);
//                    prestmt.setLong(2,eleitor.getId());
//
//                    prestmt.execute();
//
//                }
//
//            }

            prestmt.close();
        } catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
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
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public List<Secao> selecionarSecoes() {

        List<Secao> mesarios = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT c.id as id ,c.logradouro as logradouro,c.numero as numero ," +
                            "m.id as mesarioId, m.nome as mesarioNome FROM Secao c " +
                         "INNER JOIN Mesario m ON c.mesario_id = m.id ");

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Secao s = new Secao();

                s.setId( rs.getLong("id") );
                s.setLogradouro( rs.getString("logradouro") );
                s.setNumero( rs.getInt("numero") );

                Mesario mesario = new Mesario();
                mesario.setId( rs.getLong("mesarioId") );
                mesario.setNome( rs.getString("mesarioNome") );

                s.setMesario(mesario);

                mesarios.add(s);
            }

            prestmt.close();
        }catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return mesarios;
    }

    public Secao selecionarSecaoMesario(Mesario mesario) {

        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT c.id as id ,c.logradouro as logradouro,c.numero as numero " +
                            "FROM Secao c " +
                            "INNER JOIN Mesario m ON c.mesario_id = m.id ");

            ResultSet rs = prestmt.executeQuery();

            if(rs.next()) {
                Secao s = new Secao();

                s.setId( rs.getLong("id") );
                s.setLogradouro( rs.getString("logradouro") );
                s.setNumero( rs.getInt("numero") );

                s.setMesario(mesario);

                return s;
            }

            prestmt.close();
        }catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return null;

    }
}
