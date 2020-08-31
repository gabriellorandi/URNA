package Chapa;

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

public class ChapaDAO {

    public Chapa cadastrarChapa(Chapa c) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Chapa(sigla,nome) VALUES (?,?)");
            prestmt.setString(1,c.getSigla());
            prestmt.setString(2,c.getNome());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return c;
    }

    public void removerChapa(Long id) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Chapa WHERE id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public List<Chapa> selecionarChapas() {

        List<Chapa> chapas = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,sigla,nome FROM Chapa ");

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Chapa e = new Chapa();

                e.setId( rs.getLong("id") );
                e.setSigla( rs.getString("sigla") );
                e.setNome( rs.getString("nome") );

                chapas.add(e);
            }

            prestmt.close();
        }catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return chapas;
    }

}
