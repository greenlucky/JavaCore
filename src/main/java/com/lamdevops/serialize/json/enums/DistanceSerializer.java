package com.lamdevops.serialize.json.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DistanceSerializer extends StdSerializer {

    public DistanceSerializer() {
        super(DistanceSerializeCus.class);
    }

    public DistanceSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Object o, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        DistanceSerializeCus distance = (DistanceSerializeCus) o;
        generator.writeStartObject();
        generator.writeFieldName("name");
        generator.writeString(distance.name());
        generator.writeFieldName("unit");
        generator.writeString(distance.getUnit());
        generator.writeFieldName("meters");
        generator.writeNumber(distance.getMeters());
        generator.writeEndObject();
    }
}
