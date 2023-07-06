package model.entities;

public class Alianca {
    private Integer cod_alianca;
    private String nome;

    public Alianca() {}

    public Alianca(Integer cod_alianca, String nome) {
        this.cod_alianca = cod_alianca;
        this.nome = nome;
    }

    public Integer getCod_alianca() {
        return cod_alianca;
    }

    public void setCod_alianca(Integer cod_alianca) {
        this.cod_alianca = cod_alianca;
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
        result = prime * result * ((cod_alianca == null) ? 0 : cod_alianca.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Alianca other = (Alianca) obj;
        if(cod_alianca == null) {
            if(other.cod_alianca != null)
                return false;
        }else if (!cod_alianca.equals(other.cod_alianca))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Alianca [cod_alianca=" + cod_alianca + ", nome=" + nome + "]";
    }

    
}
