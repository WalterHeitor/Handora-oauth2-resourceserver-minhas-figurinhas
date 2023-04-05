package br.com.zup.edu.albunsfreemium.client;

import java.util.List;

public class DetalhesDoAlbumResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String dono;
    private List<DetalheDaFigurinhaResponse> figurinhas;

    public DetalhesDoAlbumResponse(Long id, String titulo, String descricao,
                                   String dono, List<DetalheDaFigurinhaResponse> figurinhas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dono = dono;
        this.figurinhas = figurinhas;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDono() {
        return dono;
    }

    public List<DetalheDaFigurinhaResponse> getFigurinhas() {
        return figurinhas;
    }
}
