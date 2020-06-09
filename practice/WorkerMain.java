package practice;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.collectingAndThen;

public class WorkerMain {
    public static void main(String[] args) {

        List<Worker> workers = WorkerUtils.getWorkerList();
        Predicate<Double> salCalculate = sal -> (sal < 60000);
        Function<LocalDate, Integer> ageCalculation = dob -> (Period.between(dob,LocalDate.now()).getYears());

        //1.List of Workers whose salary is less then 60000
        System.out.println("1. List of Workers whose salary is less then 60000:");
        List<String> list1 = workers.stream()
                .filter(w -> salCalculate.test(w.getSal()))
                .map(w -> w.getName())
                .collect(Collectors.toList());
        list1.forEach(System.out::println);

        //2.Count of workers whose salary greater than 30000 less than 60000
        System.out.println("\n2. Count of workers whose salary greater than 30000 less than 60000");
        long count = workers.stream()
        		.filter(w -> w.getSal() > 30000 && w.getSal() < 60000)
        		.count();
        System.out.println(count);
        
        //3.Oldest worker,get worker object and print
        System.out.println("\n3. Oldest worker,get worker object and print");
        Worker oldestWorker = workers.stream()
                .min(Comparator.comparing(worker -> worker.getDob()))
                .get();
        System.out.println(oldestWorker);
        
        //4.Junior worker,get worker object and print
        System.out.println("\n4. Junior worker,get worker object and print");
        Worker juniorWorker = workers.stream()
        		.max(Comparator.comparing(w -> w.getDob()))
        		.get();
        System.out.println(juniorWorker);
        
        //5.Highest paid worker,get worker object and print
        System.out.println("\n5. Highest paid worker,get worker object and print");
        Worker highestPaid = workers.stream()
        		.max(Comparator.comparing(w -> w.getSal()))
        		.get();
        System.out.println(highestPaid);
        
        //6.lowest paid worker,get worker object and print
        System.out.println("\n6. Lowest paid worker,get worker object and print");
        Worker lowestPaid = workers.stream()
        		.min(Comparator.comparing(w -> w.getSal()))
        		.get();
        System.out.println(lowestPaid);
        
        //7.Sort worker age, lowest to Highest,get list of worker names and print
        System.out.println("\n7. Sort worker age, lowest to Highest,get list of worker names and print");
        List<String> workersAsc = workers.stream()
        		.sorted(Comparator.comparing(w -> w.getAge()))
        		.map(w -> w.getName())
        		.collect(Collectors.toList());
        System.out.println(workersAsc);
        
        //8.Sort worker age , highest to lowest,get list of worker names and print
        System.out.println("\n8. Sort worker age , highest to lowest,get list of worker names and print");
        Comparator<Worker> comparator = Comparator.comparing(w -> w.getAge());
        List<String> workersDesc = workers.stream()
        		.sorted(comparator.reversed())
        		.map(w -> w.getName())
        		.collect(Collectors.toList());
        System.out.println(workersDesc);
        
        //9.Average Salary of all workers
        System.out.println("\n9. Average Salary of all workers");
        double avgSal = workers.stream()
        		.mapToDouble(w -> w.getSal())
        		.average()
        		.getAsDouble();
        System.out.println(avgSal);
        
        //10.Average age of all workers
        System.out.println("\n10. Average age of all workers");
        double avgAge = workers.stream()
                .mapToInt(w -> ageCalculation.apply(w.getDob()))
                .average()
                .getAsDouble();
        System.out.println(avgAge);
        
        //11.Group Using Department and print for each department
        System.out.println("\n11. Group Using Department and print for each department");
        Map<String, List<Worker>> workerDeptGrouped = workers.stream()
        		.collect(Collectors.groupingBy(w -> w.getDept()));
        workerDeptGrouped.forEach((k,v) -> {System.out.println("Department: "+k +", Worker Details: "+v);
        								});
        
        //12.count of worker age> 20 and salary <30000
        System.out.println("\n12. Count of worker age> 20 and salary <30000");
        count = workers.stream()
        		.filter(w -> w.getAge() > 20 && w.getSal() < 30000)
        		.count();
        System.out.println(count);
        
        //13.Worker Count from each group
        System.out.println("\n13. Worker Count from each group");
        workerDeptGrouped.forEach((k,v) -> {System.out.println("Department: "+k +", Worker Count: "+ v.size()); });
        
        //14.Total Salary of Each dept
        System.out.println("\n14. Total Salary of Each dept");
        workerDeptGrouped.forEach((k,v) -> {System.out.println("Department: "+k +", Salary: "
        										+ v.stream().mapToDouble(w -> w.getSal()).sum()); });
        
        //15.Average salary of each dept
        System.out.println("\n15. Average salary of each dept");
        workerDeptGrouped.forEach((k,v) -> {System.out.println("Department: "+k +", Salary: "
				+ v.stream().mapToDouble(w -> w.getSal()).average()); });
        
        //16.Oldest employee of each dept
        System.out.println("\n16. Oldest employee of each dept");
        workerDeptGrouped.forEach((k,v) -> {System.out.println("Department: "+k +", Oldest Employee: "
				+ v.stream().min(Comparator.comparing(w -> w.getDob())).get()); });
        
        //17.Given the list of worker, find the worker with name “Some Name”.
        System.out.println("\n17. Given the list of worker, find the worker with name “Andrew”.");
        Worker worker = workers.stream().filter(w -> w.getName().equals("Andrew")).findFirst().get();
        System.out.println(worker);
        
        //18.Join the all Worker names with “,” using java 8
        System.out.println("\n18. Join the all Worker names with “,” using java 8");
        String commaSeparatedWorker = workers.stream().map(w -> w.getName()).collect(Collectors.joining(","));
        System.out.println(commaSeparatedWorker);
        
        //19.remove the duplicate elements from the list
        System.out.println("\n19. Remove the duplicate elements from the list");
        HashSet<String> workerNames = new HashSet<>();
        List<Worker> uniqueWorker = workers.stream()
        		.filter(w -> workerNames.add(w.getName()))
        		.collect(Collectors.toList());
        uniqueWorker.forEach(System.out::println);
        		
        //20.List of employee whose name starts with "A"
        System.out.println("\n20. List of employee whose name starts with \"A\"");
        List<Worker> workersA = workers.stream()
        		.filter(w -> w.getName().startsWith("A"))
        		.collect(Collectors.toList());
        workersA.forEach(System.out::println);
    }
}
