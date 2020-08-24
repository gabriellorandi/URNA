package Eleicao;

import Candidato.Candidato;
import Secao.Secao;

import java.time.LocalDate;
import java.util.List;

public class Eleicao {

    private Long id;
    private LocalDate dia;


    private List<Secao> secoes;
    private List<Candidato> candidatos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }


    public List<Secao> getSecoes() {
        return secoes;
    }

    public void setSecoes(List<Secao> secoes) {
        this.secoes = secoes;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public Integer getHaCandidatos(){
        if(candidatos==null) return 0;
        return candidatos.size();
    }

    public Integer getHaSecoes(){
        if(secoes==null) return 0;
        return secoes.size();
    }

}
