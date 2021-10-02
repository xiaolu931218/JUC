package com.test.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.LongStream;

public class TestForkJoin {

    public static void main(String[] args) {
        LongStream longStream = LongStream.rangeClosed(1, 10000000L);
        long sum = 0;
        Instant start = Instant.now();

        System.out.println(longStream.reduce(0,Long::sum));
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());

    }

}
