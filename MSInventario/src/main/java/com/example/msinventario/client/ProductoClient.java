package com.example.msinventario.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class ProductoClient {

    @Value("${msproductos.url:http://localhost:8080}")
    private String productosUrl;

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    // El RestTemplate se inyecta desde RestTemplateConfig (con timeouts configurados)
    public ProductoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map getProducto(Long productoId) {
        String url = productosUrl + "/productos/" + productoId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al comunicarse con MSProductos: " + e.getMessage(), e);
        }
    }
}
