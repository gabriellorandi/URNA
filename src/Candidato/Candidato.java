package Candidato;

import Cargo.Cargo;
import Chapa.Chapa;

import java.io.File;
import java.util.List;

public class Candidato {

    private Long id;
    private String nome;
    private Long file;
    private File foto;

    private List<Chapa> chapas;
    private Cargo cargo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getFile() {
        return file;
    }

    public void setFile(Long file) {
        this.file = file;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public List<Chapa> getChapas() {
        return chapas;
    }

    public void setChapas(List<Chapa> chapas) {
        this.chapas = chapas;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
