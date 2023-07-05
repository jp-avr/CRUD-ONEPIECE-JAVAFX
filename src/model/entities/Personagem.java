package model.entities;

public class Personagem {
    private Integer cod_personagem;
    private Integer cod_pirata;
    private Integer cod_marinha;

    public Personagem() {}

    public Personagem(Integer cod_personagem, Integer cod_pirata, Integer cod_marinha) {
        this.cod_personagem = cod_personagem;
        this.cod_pirata = cod_pirata;
        this.cod_marinha = cod_marinha;
    }

    public Integer getCod_personagem() {
        return cod_personagem;
    }

    public void setCod_personagem(Integer cod_personagem) {
        this.cod_personagem = cod_personagem;
    }

    public Integer getCod_pirata() {
        return cod_pirata;
    }

    public void setCod_pirata(Integer cod_pirata) {
        this.cod_pirata = cod_pirata;
    }

     public Integer getCod_marinha() {
        return cod_marinha;
    }

    public void setCod_marinha(Integer cod_marinha) {
        this.cod_marinha = cod_marinha;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result * ((cod_personagem == null) ? 0 : cod_personagem.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Personagem other = (Personagem) obj;
        if(cod_personagem == null) {
            if(other.cod_personagem != null)
                return false;
        }else if (!cod_personagem.equals(other.cod_personagem))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Personagem [cod_personagem=" + cod_personagem + ", cod_pirata="
                + cod_pirata + ", cod_mariha=" + cod_marinha + "]";
    }

    
}
