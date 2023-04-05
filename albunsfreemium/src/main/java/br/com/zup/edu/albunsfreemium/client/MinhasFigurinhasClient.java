package br.com.zup.edu.albunsfreemium.client;

import br.com.zup.edu.albunsfreemium.albun.NovoAlbumRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(
        name = "minhasFigurinhasClient",
        url = "${integrations.resource-server.url}")
public interface MinhasFigurinhasClient {

    @PostMapping("/api/albuns")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoAlbumRequest request);
}
