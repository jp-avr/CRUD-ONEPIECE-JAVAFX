package model.entities;

public class Arco {
    private Integer cod_arco;
    private String nome;

    public Arco() {}

    public Arco(Integer cod_arco, String nome) {
        this.cod_arco = cod_arco;
        this.nome = nome;
    }

    public Integer getCod_arco() {
        return cod_arco;
    }

    public void setCod_arco(Integer cod_arco) {
        this.cod_arco = cod_arco;
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
        result = prime * result * ((cod_arco == null) ? 0 : cod_arco.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Arco other = (Arco) obj;
        if(cod_arco == null) {
            if(other.cod_arco != null)
                return false;
        }else if (!cod_arco.equals(other.cod_arco))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Arco [cod_arco=" + cod_arco + ", nome=" + nome + "]";
    }

    
}
