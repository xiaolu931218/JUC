package com.test.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestVisualVM {

    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("totalMemory>>>" + (float) totalMemory / 1024 / 1024 + "M");
        System.out.println("maxMemory>>>"+maxMemory/1024/1024+"M");
        String a = "A"+ "c";
        String b = a + "B";


        List<Integer> list = new ArrayList<>();
        while (true) {
            try {

                Thread.sleep(TimeUnit.MILLISECONDS.toMillis(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(10000);
        }

    }

}
