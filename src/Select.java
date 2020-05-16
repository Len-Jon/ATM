import javax.swing.*;

/**
 * @author wei
 * @since 2020/5/16 20:46
 */
public class Select extends JPanel {
    public Select(){
        JLabel title = new JLabel("账户余额");
        JLabel data = new JLabel(String.valueOf(Main.account.getMoney()));
        add(title);
        add(data);

        validate();
        setVisible(true);
    }
}
