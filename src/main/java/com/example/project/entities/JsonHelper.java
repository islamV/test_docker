package com.example.project.entities;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import org.springframework.stereotype.Component;

@Component
public class JsonHelper {
    private final ObjectMapper om;
    public JsonHelper(ObjectMapper om) { this.om = om; }

    public JsonNode toJsonNode(String s) {
        if (s == null || s.isBlank()) return NullNode.getInstance();
        try { return om.readTree(s); } catch (Exception e) { return NullNode.getInstance(); }
    }

    public String toJsonString(JsonNode n) {
        if (n == null || n.isNull()) return null;
        try { return om.writeValueAsString(n); } catch (Exception e) { throw new RuntimeException(e); }
    }
}
