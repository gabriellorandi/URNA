package Candidato;

import Cargo.Cargo;
import Chapa.Chapa;
import Grupo.Grupo;

import java.io.File;

public class Candidato {

    private Long id;
    private String nome;
    private Long cpf;
    private File foto;

    private Chapa chapa;
    private Cargo cargo;
    private Grupo grupo;

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

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Chapa getChapa() {
        return chapa;
    }

    public void setChapa(Chapa chapa) {
        this.chapa = chapa;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
}
