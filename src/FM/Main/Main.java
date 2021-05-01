package FM.Main;

public class Main {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();

        Team t = new Team();
        Team n = new Team();

        System.out.println(t.getInitial11());

        n.addPLAYER(t.getDefense()[2].getName(),t.getDefense()[2].getNumber(),t);
        t.addPLAYER(n.getDefense()[7].getName(),n.getDefense()[7].getNumber(),n);
        System.out.println(n.toString());
        System.out.println(t.toString());
    }
}