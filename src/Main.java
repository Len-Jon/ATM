import java.io.IOException;

/**
 * @author wei
 * @since 2020/5/16 16:37
 */
public class Main {
    public static Account account = new Account();
    public static Menu menu;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        account.setCard("");
        account.setPwd("");
        account.setMoney(50000);
        FileIO fileIO = new FileIO();
        new Login();
    }
}
