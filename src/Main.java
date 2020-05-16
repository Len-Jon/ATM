/**
 * @author wei
 * @since 2020/5/16 16:37
 */
public class Main {
    public static Account account = new Account();
    public static Menu menu;

    public static void main(String[] args) {
        account.setCard("");
        account.setPwd("");
        account.setMoney(50000);
        Login login = new Login();
    }
}
