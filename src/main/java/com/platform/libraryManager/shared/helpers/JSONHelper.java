package com.platform.libraryManager.shared.helpers;

import tools.jackson.databind.ObjectMapper;

public abstract class JSONHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String createJSONObject(Object object) { return mapper.writeValueAsString(object); }
}
