package Comprovante;

import Eleicao.Eleicao;
import Eleitor.Eleitor;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComprovanteDAO {

    public Comprovante cadastrarCargo(Comprovante c) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "INSERT INTO Comprovante(id,eleicao_id,eleitor_id) VALUES (?,?,?)");
            prestmt.setLong(1,c.getId());
            prestmt.setLong(1,c.getEleicao().getId());
            prestmt.setLong(1,c.getEleitor().getId());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
        return c;

    }

    public List<Comprovante> selecionarCargos() {


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
            new PSQLException(sql);
        }
        return comprovantes;

    }

}
