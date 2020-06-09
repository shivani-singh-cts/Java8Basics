package flatmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlatMap {

    public static void main(String ...args){
        List<List<Integer>> listOfList  = new ArrayList<>();
        listOfList.add(IntStream.rangeClosed(1,100)
                .boxed()
                .collect(Collectors.toList())
        );

        int sum = listOfList.stream()
                .flatMap(Collection::stream)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum :"+sum);
    }
}
