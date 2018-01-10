package ru.bugmakers.validator.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.examples.Utils;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import ru.bugmakers.exceptions.common.JsonSchemaValidationException;

import java.io.IOException;

/**
 * Created by Ivan
 */
public class JsonSchemaValidator {

    public static void validate(final Object req, final String schema) throws JsonSchemaValidationException {

        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final JsonNode jsonReq = mapper.convertValue(req, JsonNode.class);
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

        JsonSchema jsonSchema;
        JsonNode schemaNode;

        try {
            schemaNode = Utils.loadResource(schema);
        } catch (IOException e) {
            throw new JsonSchemaValidationException();
            //TODO log json schema load exception
        }

        if (schemaNode == null) throw new JsonSchemaValidationException();

        try {
            jsonSchema = factory.getJsonSchema(schemaNode);
        } catch (ProcessingException e) {
            throw new JsonSchemaValidationException();
            //TODO log processing exception
        }

        try {
            jsonSchema.validate(jsonReq);
        } catch (ProcessingException e) {
            throw new JsonSchemaValidationException();
            //TODO log validation exception
        }

    }

}
