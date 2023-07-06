package model.entities;

public class Arma {
    private Integer cod_arma;
    private String nome;

    public Arma() {}

    public Arma(Integer cod_arma, String nome) {
        this.cod_arma = cod_arma;
        this.nome = nome;
    }

    public Integer getCod_arma() {
        return cod_arma;
    }

    public void setCod_arma(Integer cod_arma) {
        this.cod_arma = cod_arma;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result * ((cod_arma == null) ? 0 : cod_arma.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Arma other = (Arma) obj;
        if(cod_arma == null) {
            if(other.cod_arma != null)
                return false;
        }else if (!cod_arma.equals(other.cod_arma))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Arma [cod_arma=" + cod_arma + ", nome=" + nome + "]";
    }

    
}
