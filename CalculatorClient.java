
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.Map;

public class CalculatorClient {

    private static Map<String, String> getArgs(String[] args) {
        Map<String, String> result = new HashMap<>();
        result.put("clientID", "DEFAULT");
        result.put("verbose", "true");

        if (args.length > 2) {
            System.out.println("Too many arguments.");
            System.out.println("Usage: CalculatorClient [-d] [clientID]");
            System.out.println("Using option = -d, clientID = DEFAULT");
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("-d")) {
                result.put("verbose", "false");
            }
            else {
                result.put("clientID", args[0]);
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("-d")) {
                result.put("verbose", "false");
            }
            else {
                System.out.println("Invalid option" + args[0]);
                System.out.println("Usage: CalculatorClient [-d] [clientID]");
                System.out.println("Using option = -d");
            }
            result.put("clientID", args[1]);
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> parsedArgs = getArgs(args);
        boolean verbose = Boolean.parseBoolean(parsedArgs.get("verbose"));

        String clientID = parsedArgs.get("clientID");
        if  (verbose){
            System.out.println("ClientID: " + clientID);
            System.out.println("Verbose: " + verbose);
        }
        ClientConsole console = new ClientConsole(verbose);
        try {
            Calculator calculator = (Calculator) Naming.lookup(
                    "rmi://localhost/CalculatorService");
            console.run(calculator, clientID);
        } catch (MalformedURLException murle) {
            System.out.println();
            System.out.println(
                    "MalformedURLException");
            System.out.println(murle);
        } catch (RemoteException re) {
            System.out.println();
            System.out.println(
                    "RemoteException");
            System.out.println(re);
        } catch (NotBoundException nbe) {
            System.out.println();
            System.out.println(
                    "NotBoundException");
            System.out.println(nbe);
        }
    }
}