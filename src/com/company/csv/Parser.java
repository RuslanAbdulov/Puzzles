package com.company.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {

    static int TRANSACTION_DATE = 27;
    static int HEADER = 0;

    public static void main(String[] args) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        try {
            Scanner in = new Scanner(new FileReader("src/com/company/csv/AE_Points_10162020.txt"));
            int linenNumber = 0;
            while (in.hasNext()) {
                String line = in.next();
                String[] tuple = line.split("\\|");
                linenNumber++;

                if (tuple.length < TRANSACTION_DATE + 1) {
                    System.out.println(linenNumber + ":" + line);
                    continue;
                }
                String header = tuple[HEADER];
                String txnDate = tuple[TRANSACTION_DATE];

                if (!header.equals("H") && (txnDate.isEmpty())) {
                    System.out.println(linenNumber + ":" + line);
                }
            }
        } catch (FileNotFoundException ignored) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
