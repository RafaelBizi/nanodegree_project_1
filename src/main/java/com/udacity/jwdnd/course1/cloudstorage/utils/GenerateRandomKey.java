package com.udacity.jwdnd.course1.cloudstorage.utils;

import java.util.*;

public class GenerateRandomKey {

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}
