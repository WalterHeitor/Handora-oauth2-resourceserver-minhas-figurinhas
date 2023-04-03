package br.com.zup.edu.minhasfigurinhas.albuns;

import base.SpringBootIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NovoAlbumControllerTest extends SpringBootIntegrationTest {

    @Autowired
    private AlbumRepository repository;

    @BeforeEach
    private void setUp() {
        repository.deleteAll();
    }

    @Test
    public void deveCadastrarNovoAlbumComSuasFigurinhas() throws Exception {
        // cenário
        NovoAlbumRequest novoAlbum = new NovoAlbumRequest("CDZ",
                "Cavaleiros do Zodiaco",
                List.of(
                        new NovaFigurinhaRequest("Seya", "http://animes.com/cdz/seya.png"),
                        new NovaFigurinhaRequest("Hyoga", "http://animes.com/cdz/hyoga.png")
                )
        );

        // ação
        Jwt jwt = Jwt.withTokenValue("token") // apenas outra forma de gerar o jwt
                .header("alg", "none")
                .claim("sub", "user-id")
                .claim("preferred_username", "walter")
                .claim("scope", "minhas-figurinhas:write")
                .build();

        System.out.println(jwt);
        mockMvc.perform(POST("/api/albuns", novoAlbum)
                        .with(jwt().jwt(jwt)))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("**/api/albuns/*"))
        ;

        // validação
        assertEquals(1, repository.count(), "total de albuns");
    }

    @Test
    public void naoDeveCadastrarNovoAlbumComSuasFigurinhas_quandoParametrosInvalidos() throws Exception {
        // cenário
        NovoAlbumRequest albumInvalido = new NovoAlbumRequest("", "", null);

        // ação
        Jwt jwt = Jwt.withTokenValue("token") // apenas outra forma de gerar o jwt
                .header("alg", "none")
                .claim("sub", "user-id")
                .claim("preferred_username", "walter")
                .claim("scope", "minhas-figurinhas:write")
                .build();
        mockMvc.perform(POST("/api/albuns", albumInvalido)
                .with(jwt().jwt(jwt)))
                .andExpect(status().isBadRequest())
        ;

        // validação
        assertEquals(0, repository.count(), "total de albuns");
    }

    @Test
    public void naoDeveCadastrarNovoAlbumComSuasFigurinhasSemToken() throws Exception {
        // cenário
        NovoAlbumRequest novoAlbum = new NovoAlbumRequest("CDZ",
                "Cavaleiros do Zodiaco",
                List.of(
                        new NovaFigurinhaRequest("Seya", "http://animes.com/cdz/seya.png"),
                        new NovaFigurinhaRequest("Hyoga", "http://animes.com/cdz/hyoga.png")
                )
        );

        // ação


        mockMvc.perform(POST("/api/albuns", novoAlbum))
                .andExpect(status().isUnauthorized())
        ;

        // validação
        assertEquals(0, repository.count(), "total de albuns");
    }
    @Test
    public void naoDeveCadastrarNovoAlbumComSuasFigurinhasSemEscopoValido() throws Exception {
        // cenário
        NovoAlbumRequest novoAlbum = new NovoAlbumRequest("CDZ",
                "Cavaleiros do Zodiaco",
                List.of(
                        new NovaFigurinhaRequest("Seya", "http://animes.com/cdz/seya.png"),
                        new NovaFigurinhaRequest("Hyoga", "http://animes.com/cdz/hyoga.png")
                )
        );

        // ação
        mockMvc.perform(POST("/api/albuns", novoAlbum)
                        .with(jwt()))
                .andExpect(status().isForbidden())
        ;

        // validação
        assertEquals(0, repository.count(), "total de albuns");
    }

}