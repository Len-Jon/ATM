import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author wei
 * @since 2020/5/16 16:43
 */
public class Login extends JFrame implements ActionListener {
    JPanel jPanel;
    JTextField jTextField;
    JPasswordField jPasswordField;
    JButton login;
    JButton logout;

    public Login() {
        super("请登录");

        //jPanel面板放在中间，登陆登出按钮在两侧
        jPanel = new JPanel();
        jTextField = new JTextField();
        jPasswordField = new JPasswordField();
        login = new JButton("登陆");
        logout = new JButton("退出");
        setLayout(new BorderLayout());

        //用空标签填充
        jPanel.setLayout(new GridLayout(8, 1));
        jPanel.add(new JLabel());
        jPanel.add(new JLabel());
        jPanel.add(new JLabel("请输入卡号"));
        jPanel.add(jTextField);
        jPanel.add(new JLabel("请输入密码"));
        jPanel.add(jPasswordField);
        jPanel.add(new JLabel());
        jPanel.add(new JLabel());

        add(jPanel, BorderLayout.CENTER);
        add(login, BorderLayout.WEST);
        add(logout, BorderLayout.EAST);


        login.addActionListener(this);
        logout.addActionListener(this);
        //限制只能输入数字，这里不完善，对复制粘贴操作不起效
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar < KeyEvent.VK_0 || keyChar > KeyEvent.VK_9) {
                    e.consume();
                }
            }
        };
        jTextField.addKeyListener(keyAdapter);
        jPasswordField.addKeyListener(keyAdapter);

        setBounds(0, 0, 500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        validate();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logout) {
            //如果监听到的按钮登出
            JOptionPane.showMessageDialog(null, "确认退出！");
            dispose();
            System.exit(0);
        }
        if (e.getSource() == login) {
            //效率可预见的低
            for (Account account : FileIO.list) {
                //检查卡号是否正确
                if (jTextField.getText().equals(account.getCard())) {
                    Main.account.setId(account.getId());
                    Main.account.setCard(account.getCard());
                    Main.account.setMoney(account.getMoney());
                    Main.account.setPwd(account.getPwd());
                    if (Arrays.equals(Main.account.getPwd().toCharArray(), jPasswordField.getPassword())) {
                        JOptionPane.showMessageDialog(this, "登陆成功");
                        try {
                            Main.menu = new Menu();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "密码错误");
                    }
                    return;

                }


            }

            JOptionPane.showMessageDialog(this, "卡号不正确");

        }
    }
}
