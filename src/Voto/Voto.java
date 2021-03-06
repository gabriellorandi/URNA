package Voto;

import Candidato.Candidato;
import Eleicao.Eleicao;
import Secao.Secao;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class Voto {

    private Long id;

    private LocalDate data;

    private Candidato candidato;

    private Eleicao eleicao;

    private Secao secao;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }
}
