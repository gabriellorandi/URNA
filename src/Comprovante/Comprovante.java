package Comprovante;

import Eleicao.Eleicao;
import Eleitor.Eleitor;

public class Comprovante {

    private Long id;

    private Eleicao eleicao;
    private Eleitor eleitor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    public Eleitor getEleitor() {
        return eleitor;
    }

    public void setEleitor(Eleitor eleitor) {
        this.eleitor = eleitor;
    }
}
