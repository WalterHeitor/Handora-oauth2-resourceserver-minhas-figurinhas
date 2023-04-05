package br.com.zup.edu.albunsfreemium.albun;

import br.com.zup.edu.albunsfreemium.client.DetalhesDoAlbumResponse;
import br.com.zup.edu.albunsfreemium.client.MinhasFigurinhasClient;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class AlbumFreemiunController {

    @Autowired
    MinhasFigurinhasClient client;

    @PostMapping("/api/albuns")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoAlbumRequest request,
                                      UriComponentsBuilder uriBuilder) {

        Response _response = client.cadastra(request);

        Long id = getAlbumId(_response);
        DetalhesDoAlbumResponse detalhesDoAlbumResponse = client.detalha(id);

        URI location = uriBuilder.path("/api/albuns/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new AlbumResponse(detalhesDoAlbumResponse));
    }

    private Long getAlbumId(Response albumResponse) {

            String location = albumResponse.headers()
                    .get("Location").stream().findFirst().get();

            String albumId = location.substring(location.lastIndexOf("/") + 1);
            return Long.parseLong(albumId);
    }
}
