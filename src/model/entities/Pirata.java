package model.entities;

import java.io.Serializable;

public class Pirata implements Serializable{ //Serializable serve para os objetos poderem ser transformados em sequencia de bytes
    //serve para o objeto seja gravado em arquivo, seja trafegado em rede etc

    private static final long serialVersionUID = 1L;

    private Integer cod_pirata;
    private String nome;
    private Integer recompensa;
    private Integer cod_ilha;
    private Integer cod_tripulacao;

    public Pirata() {}

    public Pirata(Integer cod_pirata, String nome, Integer recompensa, Integer cod_ilha, Integer cod_tripulacao) {
        this.cod_pirata = cod_pirata;
        this.nome = nome;
        this.recompensa = recompensa;
        this.cod_ilha = cod_ilha;
        this.cod_tripulacao = cod_tripulacao;
    }

    public Integer getCod_pirata() {
        return cod_pirata;
    }

    public void setCod_pirata(Integer cod_pirata) {
        this.cod_pirata = cod_pirata;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Integer recompensa) {
        this.recompensa = recompensa;
    }

    public Integer getCod_ilha() {
        return cod_ilha;
    }

    public void setCod_ilha(Integer cod_ilha) {
        this.cod_ilha = cod_ilha;
    }

    public Integer getCod_tripulacao() {
        return cod_tripulacao;
    }

    public void setCod_tripulacao(Integer cod_tripulacao) {
        this.cod_tripulacao = cod_tripulacao;
    }

    @Override
    public int hashCode() { //SERVE PARA COMPARAR OS OBJETOS PELO CONTEÚDO E NÃO PELA REFERÊNCIA DE PONTEIROS
        final int prime = 31;
        int result = 1;
        result = prime * result * ((cod_pirata == null) ? 0 : cod_pirata.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) { //
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Pirata other = (Pirata) obj;
        if(cod_pirata == null) {
            if(other.cod_pirata != null)
                return false;
        }else if (!cod_pirata.equals(other.cod_pirata))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pirata [cod_pirata=" + cod_pirata + ", nome=" + nome + ", recompensa=" + recompensa + ", cod_ilha="
                + cod_ilha + ", cod_tripulacao=" + cod_tripulacao + "]";
    }

    
}
