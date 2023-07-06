package model.entities;

public class ArmaPersonagem {
    private Integer cod_arma;
    private Integer cod_personagem;

    public ArmaPersonagem() {}

    public ArmaPersonagem(Integer cod_arma, Integer cod_personagem) {
        this.cod_arma = cod_arma;
        this.cod_personagem = cod_personagem;
    }

    public Integer getCod_arma() {
        return cod_arma;
    }

    public void setCod_arma(Integer cod_arma) {
        this.cod_arma = cod_arma;
    }

    public void setCod_personagem(Integer cod_personagem) {
        this.cod_personagem = cod_personagem;
    }

    public Integer getCod_personagem() {
        return cod_personagem;
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
        ArmaPersonagem other = (ArmaPersonagem) obj;
        if(cod_arma == null) {
            if(other.cod_arma != null)
                return false;
        }else if (!cod_arma.equals(other.cod_arma))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ArmaPersonagem [cod_arma=" + cod_arma + ", cod_personagem=" + cod_personagem + "]";
    }

    
}
