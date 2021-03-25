package com.deloitte.SpaceStation.security.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonMessageCreator {

    public static String create(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("message", message);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
    }
}
