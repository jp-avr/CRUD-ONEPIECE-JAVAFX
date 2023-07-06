package model.entities;

public class Ilha {
    private Integer cod_ilha;
    private String nome;
    private Integer cod_arco;

    public Ilha() {}

    public Ilha(Integer cod_ilha, String nome, Integer cod_arco) {
        this.cod_ilha = cod_ilha;
        this.nome = nome;
        this.cod_arco = cod_arco;
    }

    public Integer getCod_ilha() {
        return cod_ilha;
    }

    public void setCod_ilha(Integer cod_ilha) {
        this.cod_ilha = cod_ilha;
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
        result = prime * result * ((cod_ilha == null) ? 0 : cod_ilha.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Ilha other = (Ilha) obj;
        if(cod_ilha == null) {
            if(other.cod_ilha != null)
                return false;
        }else if (!cod_ilha.equals(other.cod_ilha))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ilha [cod_ilha=" + cod_ilha + ", nome=" + nome + ", cod_arco=" + cod_arco + "]";
    }

    
}
