package MainApp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.util.*;

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

    public static List<String> getParams(String s) throws Exception {
        String[] arr = s.split(" ");

        Options options = new Options();
        options.addOption("id", true, "pay id");
        options.addOption("amount", true, "pay amount");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, arr);

        String id = cmd.getOptionValue("id");
        String amount = cmd.getOptionValue("amount");

        List<String> params = Arrays.asList(id, amount);
        return params;
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
