package org.tatastrive.callbackapi.utility;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attributes) {
       if (attributes == null || attributes.isEmpty()){
           return "";
       } else {
           return String.join(",", attributes);
       }
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(s.split(","));
        }
    }
}
