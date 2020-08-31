package Cargo;

import Eleicao.Eleicao;
import Grupo.Grupo;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {
    public List<Cargo> selecionarCargos() {


        List<Cargo> cargos = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT c.id as id, c.nome as nome, g.id as grupoId, g.nome as grupoNome FROM Cargo c " +
                        "LEFT JOIN Grupo g ON g.id = c.grupo_id  ");

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Cargo c = new Cargo();

                c.setId( rs.getLong("id") );
                c.setNome( rs.getString("nome") );

                Grupo g = new Grupo();

                g.setId( rs.getLong("grupoId") );
                g.setNome(rs.getString("grupoNome"));

                c.setGrupo(g);

                cargos.add(c);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return cargos;

    }

    public void removerCargo(Long id) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Cargo WHERE id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }

    }

    public Cargo cadastrarCargo(Cargo c, Eleicao e) {

        if(c.getGrupo() == null) {

            try {
                Connection conn = PostgreSQLJDBC.conectar();
                PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Cargo(nome,eleicao_id) VALUES (?,?)");
                prestmt.setString(1,c.getNome());
                prestmt.setLong(2,e.getId());
                prestmt.execute();
                prestmt.close();
            } catch (SQLException sql) {
                new PSQLException(sql);
            }

        } else {

            try {
                Connection conn = PostgreSQLJDBC.conectar();
                PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Cargo(nome,grupo_id) VALUES (?,?)");
                prestmt.setString(1,c.getNome());
                prestmt.setLong(2,c.getGrupo().getId());
                prestmt.execute();
                prestmt.close();
            } catch (SQLException sql) {
                new PSQLException(sql);
            }

        }


        return c;

    }

    public List<Cargo> selecionarCargosEleicao(Eleicao eleicao) {

        List<Cargo> cargos = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT c.id as id, c.nome as nome, g.id as grupoId, g.nome as grupoNome FROM Cargo c " +
                            "LEFT JOIN Grupo g ON g.id = c.grupo_id " +
                            "WHERE c.eleicao_id = ? ");

            prestmt.setLong(1,eleicao.getId());

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Cargo c = new Cargo();

                c.setId( rs.getLong("id") );
                c.setNome( rs.getString("nome") );

                Grupo g = new Grupo();

                g.setId( rs.getLong("grupoId") );
                g.setNome(rs.getString("grupoNome"));

                c.setGrupo(g);

                cargos.add(c);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return cargos;

    }
}
