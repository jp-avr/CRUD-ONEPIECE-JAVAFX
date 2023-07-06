package model.entities;

public class Tripulacao {
    private Integer cod_tripulacao;
    private String nome;
    private Integer cod_alianca;

    public Tripulacao() {}

    public Tripulacao(Integer cod_tripulacao, String nome, Integer cod_alianca) {
        this.cod_tripulacao = cod_tripulacao;
        this.nome = nome;
        this.cod_alianca = cod_alianca;
    }

    public Integer getCod_tripulacao() {
        return cod_tripulacao;
    }

    public void setCod_tripulacao(Integer cod_tripulacao) {
        this.cod_tripulacao = cod_tripulacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCod_alianca() {
        return cod_alianca;
    }

    public void setCod_alianca(Integer cod_alianca) {
        this.cod_alianca = cod_alianca;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result * ((cod_tripulacao == null) ? 0 : cod_tripulacao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Tripulacao other = (Tripulacao) obj;
        if(cod_tripulacao == null) {
            if(other.cod_tripulacao != null)
                return false;
        }else if (!cod_tripulacao.equals(other.cod_tripulacao))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tripulacao [cod_tripulacao=" + cod_tripulacao + ", nome=" + nome + ", cod_alianca="
                + cod_alianca + "]";
    }

    
}
