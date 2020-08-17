package Cargo;

import Utils.PSQLException;
import Utils.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {
    public List<Cargo> selecionarChapas() {


        List<Cargo> cargos = new ArrayList<>();
        try{
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("SELECT id,nome FROM Cargo ");

            ResultSet rs = prestmt.executeQuery();

            while(rs.next()) {
                Cargo c = new Cargo();

                c.setId( rs.getLong("id") );
                c.setNome( rs.getString("nome") );

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

    public Cargo cadastrarCargo(Cargo c) {

        try {
            Connection conn = PostgreSQLJDBC.conectar();
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO Cargo(nome) VALUES (?)");
            prestmt.setString(1,c.getNome());
            prestmt.execute();
            prestmt.close();
        } catch (SQLException sql) {
            new PSQLException(sql);
        }
        return c;

    }
}
