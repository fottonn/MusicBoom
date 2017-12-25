package ru.bugmakers.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.examples.Utils;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.IOException;

/**
 * Created by Ivan
 */
public class ReqValidator {

    public static void validate(final Object req, final String schema) {

        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final JsonNode jsonReq = mapper.convertValue(req, JsonNode.class);
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

        JsonSchema jsonSchema = null;
        try {
            final JsonNode schemaNode = Utils.loadResource(schema);
            jsonSchema = factory.getJsonSchema(schemaNode);
        } catch (Exception e) {
            //TODO throw json schema load exception
        }

        if (jsonSchema == null) throw new NullPointerException(); //TODO throw json schema exception

        try {
            jsonSchema.validate(jsonReq);
        } catch (ProcessingException e) {
            //TODO throw validation exception
        }

    }

}
