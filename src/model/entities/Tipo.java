package model.entities;

public class Tipo {
    private Integer cod_tipo;
    private String nome;

    public Tipo() {}

    public Tipo(Integer cod_tipo, String nome) {
        this.cod_tipo = cod_tipo;
        this.nome = nome;
    }

    public Integer getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(Integer cod_tipo) {
        this.cod_tipo = cod_tipo;
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
        result = prime * result * ((cod_tipo == null) ? 0 : cod_tipo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Tipo other = (Tipo) obj;
        if(cod_tipo == null) {
            if(other.cod_tipo != null)
                return false;
        }else if (!cod_tipo.equals(other.cod_tipo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tipo [cod_tipo=" + cod_tipo + ", nome=" + nome + "]";
    }

    
}
