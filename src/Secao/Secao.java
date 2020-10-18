package Secao;

import Mesario.Mesario;
import Voto.Voto;

import java.util.List;

public class Secao {

    private Long id;
    private String logradouro;
    private Integer numero;

    private List<Voto> votos;
    private Mesario mesario;

    @Override
    public String toString() {
        return logradouro+", "+numero;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public Mesario getMesario() {
        return mesario;
    }

    public String getMesarioNome() {
        return mesario.getNome();
    }

    public void setMesario(Mesario mesario) {
        this.mesario = mesario;
    }

}
