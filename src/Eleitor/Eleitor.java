package Eleitor;

import Grupo.Grupo;
import Secao.Secao;

import java.util.Objects;

public class Eleitor {

    private Long id;
    private String nome;
    private Long cpf;

    private Grupo grupo;
    private Secao secao;

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

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getGrupoNome() {
        return (this.grupo!=null)?grupo.getNome():"";
    }


    public String getSecaoNome() {
        return (this.secao!=null)?getSecao().toString():"";
    }

    @Override
    public String toString() {
        return id+" "+nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eleitor eleitor = (Eleitor) o;
        return Objects.equals(id, eleitor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }
}
