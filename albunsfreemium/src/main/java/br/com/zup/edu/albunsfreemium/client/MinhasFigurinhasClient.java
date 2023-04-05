package br.com.zup.edu.albunsfreemium.client;

import br.com.zup.edu.albunsfreemium.albun.NovoAlbumRequest;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(
        name = "minhasFigurinhasClient",
        url = "${integrations.resource-server.url}")
public interface MinhasFigurinhasClient {

    @PostMapping("/api/albuns")
    public Response cadastra(@RequestBody @Valid NovoAlbumRequest request);

    @GetMapping("/api/albuns/{id}")
    public DetalhesDoAlbumResponse detalha(@PathVariable Long id);
}
