import javax.swing.*;
import java.awt.*;

/**
 * @author wei
 * @since 2020/5/16 20:41
 */
public class Menu extends JFrame {
    static JPanel desktop;
    static JPanel left;
    static JPanel right;
    static JButton select = new JButton("查询");
    static JButton in = new JButton("存钱");
    static JButton out = new JButton("取钱");
    static JButton post = new JButton("转账");
    static JButton update = new JButton("改密");
    static JButton exit = new JButton("退卡");

    public Menu() {
        desktop = new JPanel();
        renew();
        clear();

        addL();


        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        add(desktop, BorderLayout.CENTER);
        setBounds(0, 0, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        validate();
        setVisible(true);
    }

    /**
     * 更新按钮面板
     */
    static void renew() {
        left = new JPanel();
        right = new JPanel();
        left.setLayout(new GridLayout(7, 1));
        right.setLayout(new GridLayout(7, 1));
        for (int i = 1; i <= 7; i++) {
            if (i % 2 == 1) {
                left.add(new JPanel());
                right.add(new JPanel());
            } else if (i == 2) {
                left.add(select);
                right.add(post);
            } else if (i == 4) {
                left.add(in);
                right.add(update);
            } else {
                left.add(out);
                right.add(exit);
            }
        }

    }

    static void clear() {
        JLabel jLabel = new JLabel("欢迎使用ATM");
        desktop.removeAll();
        desktop.setLayout(new GridLayout(1, 1));
        desktop.add(jLabel);
        desktop.validate();
    }

    static void addL() {
        select.addActionListener(e -> {
            desktop.removeAll();
            desktop.setLayout(new GridLayout(2, 1));
            desktop.add(new JLabel("账户余额"), BorderLayout.NORTH);
            desktop.add(new JLabel("" + Main.account.getMoney()), BorderLayout.SOUTH);
            desktop.validate();

        });
        in.addActionListener(e -> {
            JPanel input = new JPanel();

            JLabel jLabel = new JLabel("存款金额");
            JTextField jTextField = new JTextField();
            JButton jButton = new JButton("存钱");

            jButton.addActionListener(x -> {
                int m = Integer.parseInt(jTextField.getText());
                if (m % 100 != 0) {
                    JOptionPane.showMessageDialog(Main.menu, "请输入100的倍数");
                } else {
                    Main.account.setMoney(Main.account.getMoney() + m);
                    clear();
                }
            });

            input.setLayout(new GridLayout(3, 3));
            input.add(new JLabel());
            input.add(jLabel);
            input.add(new JLabel());
            input.add(new JLabel());
            input.add(jTextField);
            input.add(new JLabel());
            input.add(new JLabel());
            input.add(jButton);
            input.add(new JLabel());

            desktop.removeAll();
            desktop.setLayout(new GridLayout(4, 1));
            desktop.add(new JLabel());

            desktop.add(input);
            desktop.add(new JLabel());
            desktop.add(new JLabel());
            desktop.validate();

        });
        out.addActionListener(e -> {
            JPanel input = new JPanel();

            JLabel jLabel = new JLabel("取款金额");
            JTextField jTextField = new JTextField();
            JButton jButton = new JButton("取钱");

            jButton.addActionListener(x -> {
                int m = Integer.parseInt(jTextField.getText());
                if (m % 100 != 0) {
                    JOptionPane.showMessageDialog(Main.menu, "请输入100的倍数");
                } else {
                    if (Main.account.getMoney() > m) {
                        Main.account.setMoney(Main.account.getMoney() - m);
                        JOptionPane.showMessageDialog(Main.menu, "取款成功");
                        clear();
                    } else {
                        JOptionPane.showMessageDialog(Main.menu, "余额不足");
                    }
                }
            });
            input.setLayout(new GridLayout(3, 3));
            input.add(new JLabel());
            input.add(jLabel);
            input.add(new JLabel());
            input.add(new JLabel());
            input.add(jTextField);
            input.add(new JLabel());
            input.add(new JLabel());
            input.add(jButton);
            input.add(new JLabel());

            desktop.removeAll();
            desktop.setLayout(new GridLayout(4, 1));
            desktop.add(new JLabel());

            desktop.add(input);
            desktop.add(new JLabel());
            desktop.add(new JLabel());
            desktop.validate();

        });
    }
}