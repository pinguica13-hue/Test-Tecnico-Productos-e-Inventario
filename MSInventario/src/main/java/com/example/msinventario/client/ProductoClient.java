package com.example.msinventario.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Component
public class ProductoClient {

    @Value("${msproductos.url:http://localhost:8080}")
    private String productosUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map getProducto(Long productoId) 
    {
        String url = productosUrl + "/productos/" + productoId;
        return restTemplate.getForObject(url, Map.class);
    }
}
