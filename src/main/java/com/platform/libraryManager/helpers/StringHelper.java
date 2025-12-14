package com.platform.libraryManager.helpers;

public abstract class StringHelper {

    public static String generateRandomString(int length) {
        return java.util.stream.IntStream.range(0, length)
                .mapToObj(i -> "" + Character.forDigit((int) (Math.random() * 36), 36))
                .collect(java.util.stream.Collectors.joining());
    }

}
