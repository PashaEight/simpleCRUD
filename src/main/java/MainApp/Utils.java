package MainApp;

public class Utils {

    public static void printMenu() {
        System.out.println();
        System.out.println("type a command: ");
        System.out.println("create - create table pay");
        System.out.println("insert - insert pay");
        System.out.println("read - get pays");
        System.out.println("exit - exit program");
        System.out.println();
    }

    public static String[] getParams(String s) {
        return null;
    }

    public static String getCommand(String s) {
        int i = s.indexOf(" ");
        if (i==-1) {
            return s;
        }
        else {
            return s.substring(0, i);
        }
    }
}
