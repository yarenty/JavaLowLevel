package com.yarenty.jms.transaction;

import java.util.Properties;

public class Resource {
    public static final String FILE_NAME = "Resource.properties";

    protected static Properties sResources;

    public synchronized static Properties getResources() throws Exception {
        if (sResources == null) {
            sResources = new Properties();
            sResources.load(Resource.class.getResourceAsStream(FILE_NAME));
        }
        return sResources;
    }
}