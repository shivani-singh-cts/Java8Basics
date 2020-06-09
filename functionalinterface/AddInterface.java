package functionalinterface;

@FunctionalInterface
public interface AddInterface {

    int add(int a, int b);

    default int substract(int a, int b){
        return a -b;
    };

}
