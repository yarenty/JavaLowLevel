package com.yarenty.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;
import org.junit.Test;

import java.util.TreeMap;

public class TestJSON {

    JSONString js;

    public static String createJSON() {
        String out;

        out = new JSONStringer()
                .array()
                .object()
                .key("com.yarenty")
                .value(
                        new JSONStringer()
                                .object()
                                .key("Inside")
                                .value(23)
                                .endObject()
                )
                .endObject()
                .object()
                .key("array inside")
                .array().value(1).value(2).value(3).endArray()
                .endObject()
                .object()
                .key("object inside object")
                .value(
                        new JSONStringer()
                                .object()
                                .key("new object")
                                .value("value of new object")
                                .endObject()
                )
                .endObject()
                .object()
                .key("key 3")
                .value(3)
                .endObject()

                .endArray()
                .toString();


        return out;
    }

    @Test
    public void doIT() {

        final String json = createJSON();
        System.out.println(json);

        System.out.println(process(json));
    }


    public String process(final String s) {
        final String out = null;
        final JSONArray a = new JSONArray(s);

        final JSONObject o1 = a.getJSONObject(0);
        final JSONObject o2 = a.getJSONObject(1);
        final JSONArray a2 = o2.getJSONArray("array inside");
        a.put(123);
        a.put(new JSONObject("{\"yaro\":\"666\"}"));

        final TreeMap<Integer, String> tm;


        return a.toString();
    }

}
