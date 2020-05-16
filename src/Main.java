/**
 * @author wei
 * @since 2020/5/16 16:37
 */
public class Main {
    public static Account account = new Account();

    public static void main(String[] args) {
        account.setCard("62170033");
        account.setPwd("123456");
        Login login = new Login();
    }
}
