package com.company.regex;


//"^\\[?(\\{.*\\})[\\,\\]]?$"
public class Regex {

    public static void main(String[] args) {
        String input1 = "[{\"unix_time\": 1538076151, \"category_id\": 1009, \"ip\": \"172.10.2.42\", \"type\": \"view\"},";
        String input2 = "{\"unix_time\": 1538076151, \"category_id\": 1004, \"ip\": \"172.10.1.139\", \"type\": \"click\"},";
        String input3 = "{\"unix_time\": 1538076160, \"category_id\": 1019, \"ip\": \"172.20.0.0\", \"type\": \"click\"}]";

        replaceAndPrint(input1);
        replaceAndPrint(input2);
        replaceAndPrint(input3);

    }

    private static void replaceAndPrint(String input) {
        System.out.println(input.replaceFirst("^\\[?(\\{.*\\})[\\,\\]]?$", "$1"));
    }
}
