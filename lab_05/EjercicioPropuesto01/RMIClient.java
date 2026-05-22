import java.rmi.Naming;

public class RMIClient {

    public static void main(String[] args) throws Exception {

        Calculator calculator = (Calculator)
                Naming.lookup("rmi://localhost/CalculatorService");

        System.out.println("Multiplicación: " + calculator.multiply(4, 3));
        System.out.println("División: " + calculator.divide(8, 2));
        System.out.println("Potencia: " + calculator.power(2, 3));
    }
}