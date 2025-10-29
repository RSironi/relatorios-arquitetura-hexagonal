package br.com.rsironn.relatorios_arquitetura_hexagonal.domain.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {

    @Autowired
    private final ObjectMapper objectMapper;

    public JsonUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public <T> T fromJson(String json, Class<T> classeConvertida) {
        try {
            return objectMapper.readValue(json, classeConvertida);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao converter string JSON para objeto", e);
        }
    }
}