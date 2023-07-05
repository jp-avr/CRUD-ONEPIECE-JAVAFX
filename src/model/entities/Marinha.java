package model.entities;

public class Marinha {
    private Integer cod_marinha;
    private String name;
    private double recompensa;
    private Integer cod_ilha;
    private Integer cod_tripulacao;

    public Marinha() {}

    public Marinha(Integer cod_marinha, String name, double recompensa, Integer cod_ilha, Integer cod_tripulacao) {
        this.cod_marinha = cod_marinha;
        this.name = name;
        this.recompensa = recompensa;
        this.cod_ilha = cod_ilha;
        this.cod_tripulacao = cod_tripulacao;
    }

    public Integer getCod_marinha() {
        return cod_marinha;
    }

    public void setCod_marinha(Integer cod_marinha) {
        this.cod_marinha = cod_marinha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(double recompensa) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result * ((cod_marinha == null) ? 0 : cod_marinha.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Marinha other = (Marinha) obj;
        if(cod_marinha == null) {
            if(other.cod_marinha != null)
                return false;
        }else if (!cod_marinha.equals(other.cod_marinha))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pirata [cod_marinha=" + cod_marinha + ", name=" + name + ", recompensa=" + recompensa + ", cod_ilha="
                + cod_ilha + ", cod_tripulacao=" + cod_tripulacao + "]";
    }

    
}
