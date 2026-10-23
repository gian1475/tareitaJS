package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/reniec")
public class DniControl {
    // el token no se sube a GitHub: se pone en la variable de entorno APIPERU_TOKEN
    @Value("${APIPERU_TOKEN:}")
    private String API_TOKEN;

    private final String API_URL = "https://apiperu.dev/api/dni";

    @GetMapping
    public String mostrarFormulario() {
        return "pagdni"; // reniec.html
    }

    @PostMapping("/buscar")
    public String buscarDni(@RequestParam("dni") String dni, Model model) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_TOKEN);
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("dni", dni);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    API_URL, HttpMethod.POST, entity, Map.class);

            Map data = (Map) response.getBody().get("data");

            // Extraer valores
            model.addAttribute("nombres", data.get("nombres"));
            model.addAttribute("apePat", data.get("apellido_paterno"));
            model.addAttribute("apeMat", data.get("apellido_materno"));
            model.addAttribute("dni", dni);

        } catch (Exception e) {
            model.addAttribute("error", "No se pudo consultar el DNI");
        }

        return "pagdni"; // devuelve a la misma página
    }
}
