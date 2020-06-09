package diamondproblem;

public class Person implements Poet, Writer{
    public static void main(String ...args){
        Person person = new Person();
        person.write();
    }

    @Override
    public void write() {
        Poet.super.write();
    }
}
