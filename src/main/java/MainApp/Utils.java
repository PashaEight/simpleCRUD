package MainApp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Utils {

    public static void printMenu() {
        System.out.println();
        System.out.println("type a command: ");
        System.out.println("insert - insert pay (-id <value> -amount <value>)");
        System.out.println("print - get pays");
        System.out.println("exit - exit program");
        System.out.println();
    }

    public static Payment createPay(String s) throws Exception {
        String[] arr = s.split(" ");

        Options options = new Options();
        options.addOption("id", true, "pay id");
        options.addOption("amount", true, "pay amount");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, arr);
            String id = cmd.getOptionValue("id");
            String amount = cmd.getOptionValue("amount");
            Payment payment = new Payment(id, amount);
            return payment;
        } catch (Exception e) {
            throw new RuntimeException("wrong arguments");
        }
    }

    public static String getCommand(String s) {
        int i = s.indexOf(" ");
        if (i == -1) {
            return s;
        } else {
            return s.substring(0, i);
        }
    }
}
