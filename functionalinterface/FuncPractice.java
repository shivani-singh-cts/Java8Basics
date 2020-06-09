package functionalinterface;

public class FuncPractice {

    public static void main(String ...args){

        AddInterface oldStyle = new AddInterface() {
            @Override
            public int add(int a, int b) {
                return a+b;
            }
        };

        AddInterface newStyle = (a,b) -> (a+b);

        System.out.println("Old style : "+oldStyle.add(2,3));
        System.out.println("New style : "+newStyle.add(3,5));

    }
}
