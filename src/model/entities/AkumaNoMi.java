package model.entities;

public class AkumaNoMi {
    private Integer cod_fruta;
    private String nome;
    private Integer cod_tipo;
    private Integer cod_personagem;

    public AkumaNoMi() {}

    public AkumaNoMi(Integer cod_fruta, String nome, Integer cod_tipo, Integer cod_personagem) {
        this.cod_fruta = cod_fruta;
        this.nome = nome;
        this.cod_tipo = cod_tipo;
        this.cod_personagem = cod_personagem;
    }

    public Integer getCod_fruta() {
        return cod_fruta;
    }

    public void setCod_fruta(Integer cod_fruta) {
        this.cod_fruta = cod_fruta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(Integer cod_tipo) {
        this.cod_tipo = cod_tipo;
    }

    public Integer getCod_personagem() {
        return cod_personagem;
    }

    public void setCod_personagem(Integer cod_personagem) {
        this.cod_personagem = cod_personagem;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result * ((cod_fruta == null) ? 0 : cod_fruta.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        AkumaNoMi other = (AkumaNoMi) obj;
        if(cod_fruta == null) {
            if(other.cod_fruta != null)
                return false;
        }else if (!cod_fruta.equals(other.cod_fruta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pirata [cod_fruta=" + cod_fruta + ", nome=" + nome + ", cod_tipo="
                + cod_tipo + ", cod_personagem=" + cod_personagem + "]";
    }

    
}
