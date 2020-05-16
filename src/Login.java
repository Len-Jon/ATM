import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

        jPanel.setLayout(new GridLayout(4, 1));
        jPanel.add(new Label("请输入卡号"));
        jPanel.add(jTextField);
        jPanel.add(new Label("请输入密码"));
        jPanel.add(jPasswordField);

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
        }
        if (e.getSource() == login) {
            //检查卡号是否正确
            if (Main.account.getCard().equals(jTextField.getText())) {
                System.out.println("get");
                if(Arrays.equals(Main.account.getPwd().toCharArray(), jPasswordField.getPassword())){
                    System.out.println("登陆成功");
                }
                else {
                    System.out.println(Main.account.getPwd());
                    System.out.println(jPasswordField.getPassword());
                    System.out.println("密码不正确");
                }
            }
            else{
                System.out.println("账号不正确");
            }
        }
    }
}
