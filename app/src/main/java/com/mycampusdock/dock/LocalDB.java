package com.mycampusdock.dock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocalDB {
    private static HashMap<String, String> auth;
    private static HashMap<String, String> users;
    private static HashMap<String, Post> data;

    // replicating database
    static {
        auth = new HashMap<>();
        users = new HashMap<>();
        data = new HashMap<>();
        auth.put("ogil7190@gmail.com", "ogil7190");
        auth.put("meeteshmehta4@gmail.com", "ogil7190");
        users.put("ogil7190@gmail.com", "Vivek Kumar");
        users.put("meeteshmehta4@gmail.com", "Meetesh Mehta");
        data.put("one", new Post("Android Workshop", "We are going to organise an event on Android day celebration. #OGIL7190", "3 Aug, 2018"));
        data.put("two", new Post("Meeting CRC Portal", "We are having an urgent meeting with CRC regarding the portal discussion and delivering a premium new dock.", "4 Aug, 2018"));
        data.put("three", new Post("AD-PD Exam", "Please check the exam scheduled on Saturday from 11 am. IT IS COMPULSORY FOR STUDENT TO ATTEMPT THIS EXAM.", "10 Aug, 2018"));
    }

    public static boolean auth(String key, String pass) {
        String value = auth.get(key);
        if (value != null) {
            if (value.equals(pass)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static String user(String key) {
        String value = users.get(key);
        if (value != null) {
            return value;
        }
        return "Dummy";
    }

    public static List<Post> dummydata() {
        List<Post> list = new ArrayList<>();
        for (String s : data.keySet()) {
            list.add(data.get(s));
        }
        return list;
    }
}
