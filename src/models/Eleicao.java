package models;

import java.util.List;

public class Eleicao {

    private Long id;
    private Double hora;

    private List<Mesario> mesarios;
    private List<Candidato> candidatos;
    private List<Secao> secoes;
    private List<Voto> votos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHora() {
        return hora;
    }

    public void setHora(Double hora) {
        this.hora = hora;
    }

    public List<Mesario> getMesarios() {
        return mesarios;
    }

    public void setMesarios(List<Mesario> mesarios) {
        this.mesarios = mesarios;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public List<Secao> getSecoes() {
        return secoes;
    }

    public void setSecoes(List<Secao> secoes) {
        this.secoes = secoes;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
}
