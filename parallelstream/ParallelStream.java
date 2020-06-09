package parallelstream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStream {
    public static void main(String ...args){


        List<Integer> list = IntStream.rangeClosed(1,10000000)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("starting ...");

        long normalTime = System.currentTimeMillis();

        int normalSum = list.stream()
                .filter(i -> i%2 == 0)
                .mapToInt(i -> i+1)
                .sum();

        System.out.println("Time taken for normal stream :" + (System.currentTimeMillis() - normalTime)+" ms");

        long parallelTime = System.currentTimeMillis();

        int parallelSum = list.parallelStream()
                .filter(i -> i%2 == 0)
                .mapToInt(i -> i+1)
                .sum();

        System.out.println("Time taken for parallel stream :" + (System.currentTimeMillis() - parallelTime)+" ms");

    }
}
