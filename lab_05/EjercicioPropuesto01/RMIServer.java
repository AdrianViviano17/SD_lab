import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String[] args) throws Exception {

        LocateRegistry.createRegistry(1099);

        Calculator calculator = new CalculatorImpl();

        Naming.rebind("rmi://localhost/CalculatorService", calculator);

        System.out.println("Servidor RMI activo");
    }
}