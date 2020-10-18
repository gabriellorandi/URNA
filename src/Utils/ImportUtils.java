package Utils;

import Candidato.Candidato;
import Cargo.Cargo;
import Chapa.Chapa;
import Eleitor.Eleitor;
import Grupo.Grupo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportUtils {

    public static List<Eleitor> loadEleitores(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<Eleitor> eleitores = new ArrayList<>();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {

                if(!strCurrentLine.isEmpty()){
                    String[] data = strCurrentLine.split(";");

                    Eleitor eleitor = new Eleitor();
                    eleitor.setId(Long.parseLong(data[0]));
                    eleitor.setNome(data[1]);
                    eleitor.setCpf(Long.parseLong(data[2]));

                    Grupo grupo = new Grupo();
                    grupo.setId(Long.parseLong(data[3]));

                    eleitor.setGrupo(grupo);

                    eleitores.add(eleitor);
                }
            }
            return eleitores;
        } catch (IOException e) {
            throw (e);
        }
    }

    public static List<Candidato> loadCandidato(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<Candidato> candidatos = new ArrayList<>();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {

                if(!strCurrentLine.isEmpty()){

                    String[] data = strCurrentLine.split(";");

                    Candidato candidato = new Candidato();
                    candidato.setId(Long.parseLong(data[0]));
                    candidato.setNome(data[1]);
                    candidato.setCpf(Long.parseLong(data[2]));

                    Cargo cargo = new Cargo();
                    cargo.setId(Long.parseLong(data[3]));

                    candidato.setCargo(cargo);

                    Chapa chapa = new Chapa();
                    chapa.setId(Long.parseLong(data[4]));

                    candidato.setChapa(chapa);

                    candidatos.add(candidato);

                }

            }
            return candidatos;
        } catch (IOException e) {
            throw (e);
        }
    }
}