package Voto;

import Candidato.Candidato;
import Eleicao.Eleicao;
import Eleitor.Eleitor;

import java.time.ZonedDateTime;

public class Voto {

    private Long id;

    private ZonedDateTime data;

    private Candidato candidato;

    private Eleicao eleicao;

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
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
}
