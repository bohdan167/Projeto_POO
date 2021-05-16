package FM.Main;

public class Main {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();

        Team t = new Team();
        System.out.println(t.toString() + "\n");
        System.out.println(t.initial11toString());

    }
}