package Comprovante;

import Eleicao.Eleicao;
import Eleitor.Eleitor;
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

public class ComprovanteDAO {

    public Comprovante cadastrarComprovante(Comprovante c) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "INSERT INTO Comprovante(eleicao_id,eleitor_id,secao_id) VALUES (?,?,?)");
            prestmt.setLong(1,c.getEleicao().getId());
            prestmt.setLong(2,c.getEleitor().getId());
            prestmt.setLong(3,c.getSecao().getId());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return c;

    }

    public List<Comprovante> selecionarComprovantes() {


        List<Comprovante> comprovantes = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT c.id as id,e.id as eleicaoId, el.id as eleitorId, el.nome as eleitorNome, el.cpf as eleitorCpd " +
                            "FROM Comprovante c " +
                            " INNER JOIN Eleicao e ON e.id = eleicao_id " +
                            " INNER JOIN Eleitor el ON el.id = eleitor_id  ");

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Comprovante c = new Comprovante();

                c.setId( rs.getLong("id") );

                Eleicao eleicao = new Eleicao();
                eleicao.setId( rs.getLong("eleicaoId") );

                Eleitor eleitor = new Eleitor();
                eleitor.setId( rs.getLong("eleitorId") );
                eleitor.setNome( rs.getString("eleitorNome") );
                eleitor.setCpf( rs.getLong("eleitorCpf") );

                c.setEleicao(eleicao);
                c.setEleitor(eleitor);

                comprovantes.add(c);
            }

            prestmt.close();
        }catch (SQLException sql) {
            AlertUtils.alert("Erro no banco de dados","Code: "+sql.getErrorCode()+" - Erro:"+sql.getMessage(), Alert.AlertType.ERROR);
        }
        return comprovantes;

    }

}
