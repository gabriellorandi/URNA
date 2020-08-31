package Eleitor;


import Eleicao.Eleicao;
import Grupo.Grupo;
import Mesario.Mesario;
import Secao.Secao;
import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EleitorDAO {

    public Eleitor cadastrarEleitor(Eleitor e, Eleicao el) {

        try {

            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Eleitor(id,nome,cpf,eleicao_id,grupo_id,secao_id) VALUES (?,?,?,?,?,?)");
            prestmt.setLong(1,e.getId());
            prestmt.setString(2,e.getNome());
            prestmt.setLong(3,e.getCpf());
            prestmt.setLong(4,el.getId());
            prestmt.setLong(5,e.getGrupo().getId());
            prestmt.setLong(6,e.getSecao().getId());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
        return e;
    }

    public void removerEleitor(Long id) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM Eleitor WHERE id = ?");
            prestmt.setLong(1,id);
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
    }


    public List<Eleitor> selecionarEleitoresEleicao(Mesario m) {

        List<Eleitor> eleitores = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT e.id as id,e.nome as nome, e.cpf as cpf FROM Eleitor e " +
                            "INNER JOIN Secao_Eleitor se ON se.eleitor_id = e.id " +
                            "INNER JOIN Secao s ON  s.id = se.secao_id " +
                            "INNER JOIN Eleicao_Secao els ON els.secao_id = s.id  " +
                            "WHERE s.mesario_id = ? ");

            prestmt.setLong(1,m.getId());

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Eleitor e = new Eleitor();

                e.setId( rs.getLong("id") );
                e.setNome( rs.getString("nome") );
                e.setCpf( rs.getLong("cpf") );

                eleitores.add(e);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
        return eleitores;
    }

    public List<Eleitor> selecionarEleitores() {

        List<Eleitor> eleitores = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement(
                    "SELECT e.id as id ,e.nome as nome ,e.cpf as cpf, " +
                            "g.id as grupoId, g.nome as grupoNome, " +
                            "s.id as secaoId, s.logradouro as secaoLogradouro, s.numero as secaoNumero FROM Eleitor e " +
                         "INNER JOIN Grupo g ON g.id = e.grupo_id " +
                         "INNER JOIN Secao s ON s.id = e.secao_id ");

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Eleitor e = new Eleitor();

                e.setId( rs.getLong("id") );
                e.setNome( rs.getString("nome") );
                e.setCpf( rs.getLong("cpf") );

                Grupo grupo = new Grupo();
                grupo.setId( rs.getLong("grupoId"));
                grupo.setNome( rs.getString("grupoNome"));

                Secao secao = new Secao();
                secao.setId(  rs.getLong("secaoId") );
                secao.setLogradouro(  rs.getString("secaoLogradouro") );
                secao.setNumero(  rs.getInt("secaoNumero") );

                e.setGrupo(grupo);
                e.setSecao(secao);

                eleitores.add(e);
            }

            prestmt.close();
        }catch (SQLException sql) {
            new PSQLException(sql);
        }
         return eleitores;
    }

}
