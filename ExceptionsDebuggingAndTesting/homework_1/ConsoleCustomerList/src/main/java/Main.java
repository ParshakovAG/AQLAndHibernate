import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Marker INPUT_HISTORY_MARKER = MarkerManager.getMarker("INPUT_HISTORY");
    private static final Marker INVALID_COMMAND = MarkerManager.getMarker("INVALID_COMMAND");
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Неверный ввод! Используйте примеры команд: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Примеры команд:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            logger.info(INPUT_HISTORY_MARKER, "Пользователь ввёл: " + command);

            try {

            String[] tokens = command.split("\\s+", 2);

            if (tokens[0].equals("add")) {
                executor.addCustomer(tokens[1]);

            } else if (tokens[0].equals("list")) {
                executor.listCustomers();
            } else if (tokens[0].equals("remove")) {
                executor.removeCustomer(tokens[1]);
            } else if (tokens[0].equals("count")) {
                System.out.println("There are " + executor.getCount() + " customers");
            } else if (tokens[0].equals("help")) {
                System.out.println(helpText);
            } else {
                System.out.println(COMMAND_ERROR);
            }

            } catch (AddCustomerException e) {
                e.printStackTrace();
            } catch (WrongCustomerPhone e) {
                e.printStackTrace();
            } catch (WrongCustomerEmail e) {
                e.printStackTrace();
            }
            logger.error(INVALID_COMMAND, "Неверный ввод, воспользуйтесь командой \"help\"");
        }
    }
}

