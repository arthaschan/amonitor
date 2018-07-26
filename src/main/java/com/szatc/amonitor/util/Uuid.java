package com.szatc.amonitor.util;

import java.util.UUID;

public class Uuid {

    public  static  String generateUuid()
    {
        return UUID.randomUUID().toString();
    }
}
