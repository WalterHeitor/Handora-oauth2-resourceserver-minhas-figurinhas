package br.com.zup.edu.albunsfreemium.client;

public class DetalheDaFigurinhaResponse {

    private Long id;
    private String descricao;
    private String enderecoDaImagem;

    public DetalheDaFigurinhaResponse(Long id, String descricao, String enderecoDaImagem) {
        this.id = id;
        this.descricao = descricao;
        this.enderecoDaImagem = enderecoDaImagem;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEnderecoDaImagem() {
        return enderecoDaImagem;
    }
}
