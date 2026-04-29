package com.ganeshmsk.cursorbasedpagination.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Base64;

public class CursorUtil {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static String encode(Cursor cursor) {
        try {
            String json = mapper.writeValueAsString(cursor);
            return Base64.getEncoder().encodeToString(json.getBytes());
        } catch (Exception e) {
            e.printStackTrace(); // 👈 important for debugging
            throw new RuntimeException("Failed to encode cursor");
        }
    }

    public static Cursor decode(String cursorStr) {
        try {
            String json = new String(Base64.getDecoder().decode(cursorStr));
            return mapper.readValue(json, Cursor.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid cursor");
        }
    }
}
