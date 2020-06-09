package diamondproblem;

public interface Writer {
    default void write(){
        System.out.println("Writer");
    };
}
