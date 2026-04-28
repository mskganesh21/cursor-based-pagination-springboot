package com.ganeshmsk.cursorbasedpagination.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CursorUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String encode(Cursor cursor){
        try{
            String json = mapper.writeValueAsString(cursor);
            return Base64.getEncoder().encodeToString(json.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to encode cursor");
        }
    }

    public static Cursor decode(String cursorStr){
        try{
            String json = new String(Base64.getDecoder().decode(cursorStr));
            return mapper.readValue(json,Cursor.class);
        } catch (Exception e) {
                throw new RuntimeException("Invalid cursor");
        }
    }
}
