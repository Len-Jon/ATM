import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author wei
 * @since 2020/5/16 20:41
 */
public class Menu extends JFrame {

    static JPanel desktop;  //显示面板
    static JPanel left;     //左按钮面板
    static JPanel right;    //右按钮面板
    static JButton select = new JButton("查询");
    static JButton in = new JButton("存钱");
    static JButton out = new JButton("取钱");
    static JButton post = new JButton("转账");
    static JButton update = new JButton("改密");
    static JButton exit = new JButton("退卡");

    public Menu() throws IOException {
        super("菜单");
        desktop = new JPanel();
        renew();//更新按钮面板
        clear();//初始化显示

        addL();
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        add(desktop, BorderLayout.CENTER);

        setBounds(0, 0, 500, 500);
        setLocationRelativeTo(null);
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
        JLabel jLabel = new JLabel("欢迎使用ATM", JLabel.CENTER);
        desktop.removeAll();
        desktop.setLayout(new GridLayout(1, 1));
        desktop.add(jLabel);
        desktop.validate();
    }

    //清除显示窗口并新建布局
    static void resetDesktop(int rows) {
        desktop.removeAll();//移除显示窗口组件
        desktop.setLayout(new GridLayout(rows, 1));
    }

    //除了查询是两排，其他都是三排，因此把这一块提取出来
    static void commonReset(JPanel input) {
        resetDesktop(3);
        desktop.add(new JLabel());//使用JLabel垫高
        desktop.add(input);
        desktop.add(new JLabel());
        desktop.validate();
    }

    //布局填充
    static JPanel fillLabel(JComponent e) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 3));
        jPanel.add(new JLabel());
        jPanel.add(e);
        jPanel.add(new JLabel());
        return jPanel;
    }

    //添加监听事件
    static void addL() {
        //查询余额
        select.addActionListener(e -> {
            resetDesktop(2);
            desktop.add(new JLabel("账户余额", JLabel.CENTER), BorderLayout.NORTH);
            desktop.add(new JLabel("" + Main.account.getMoney(), JLabel.CENTER), BorderLayout.SOUTH);
            desktop.validate();

        });
        //存钱
        in.addActionListener(e -> {
            JPanel input = new JPanel();//input面板
            //input面板核心组件
            JLabel jLabel = new JLabel("存款金额", JLabel.CENTER);
            JTextField jTextField = new JTextField();
            JButton jButton = new JButton("存钱");
            //为按钮添加监听
            jButton.addActionListener(x -> {
                int m = Integer.parseInt(jTextField.getText());//获取金额
                if (m < 0) {
                    JOptionPane.showMessageDialog(Main.menu, "非法金额");
                }
                if (m % 100 != 0) {
                    JOptionPane.showMessageDialog(Main.menu, "请输入100的倍数");
                } else {
                    Main.account.setMoney(Main.account.getMoney() + m);
                    JOptionPane.showMessageDialog(Main.menu, "存钱成功");
                    try {
                        Account.renew();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    clear();
                }
            });
            //input面板布局
            input.setLayout(new GridLayout(3, 1));
            //核心组件填充
            JPanel[] p = new JPanel[3];
            p[0] = fillLabel(jLabel);
            p[1] = fillLabel(jTextField);
            p[2] = fillLabel(jButton);
            //添加核心组件->input
            for (JPanel panel : p) {
                input.add(panel);
            }
            //input->desktop
            commonReset(input);
        });

        //取钱
        out.addActionListener(e -> {
            JPanel input = new JPanel();//input面板
            //input面板核心组件
            JLabel jLabel = new JLabel("取款金额", JLabel.CENTER);
            JTextField jTextField = new JTextField();
            JButton jButton = new JButton("取钱");
            //为按钮添加监听
            jButton.addActionListener(x -> {
                int m = Integer.parseInt(jTextField.getText());
                if (m < 0) {
                    JOptionPane.showMessageDialog(Main.menu, "非法金额");
                }
                if (m % 100 != 0) {
                    JOptionPane.showMessageDialog(Main.menu, "请输入100的倍数");
                } else {
                    if (Main.account.getMoney() > m) {
                        Main.account.setMoney(Main.account.getMoney() - m);
                        JOptionPane.showMessageDialog(Main.menu, "取款成功");
                        try {
                            Account.renew();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        clear();
                    } else {
                        JOptionPane.showMessageDialog(Main.menu, "余额不足");
                    }
                }
            });
            //input面板布局
            input.setLayout(new GridLayout(3, 1));
            JPanel[] ps = new JPanel[3];
            //核心组件填充
            ps[0] = fillLabel(jLabel);
            ps[1] = fillLabel(jTextField);
            ps[2] = fillLabel(jButton);
            //核心组件->input
            for (JPanel p : ps) {
                input.add(p);
            }
            //input->desktop
            commonReset(input);
        });
        //改密
        update.addActionListener(e -> {
            JPanel input = new JPanel();//input面板
            //input面板核心组件
            JLabel jLabel = new JLabel("请输入6位数新密码");
            JPasswordField jPasswordField = new JPasswordField();
            JLabel confirmNote = new JLabel("请确认6位数新密码");
            JPasswordField confirm = new JPasswordField();
            JButton jButton = new JButton("确认");
            //为按钮添加监听
            jButton.addActionListener(x -> {
                String s = String.valueOf(jPasswordField.getPassword());
                String c = String.valueOf(confirm.getPassword());
                if (s.length() != 6) {
                    JOptionPane.showMessageDialog(Main.menu, "密码非法！");
                } else {
                    if (s.equals(c)) {
                        Main.account.setPwd(s);
                        JOptionPane.showMessageDialog(Main.menu, "改密成功");
                        clear();
                        try {
                            Account.renew();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        Main.menu.dispose();
                        new Login();
                    } else {
                        JOptionPane.showMessageDialog(Main.menu, "两次密码不一致");
                    }
                }
            });
            //input面板布局
            input.setLayout(new GridLayout(5, 1));
            //核心组件填充
            JPanel[] jp = new JPanel[5];
            jp[0] = fillLabel(jLabel);
            jp[1] = fillLabel(jPasswordField);
            jp[2] = fillLabel(confirmNote);
            jp[3] = fillLabel(confirm);
            jp[4] = fillLabel(jButton);
            //核心组件->input
            for (JPanel jPanel : jp) {
                input.add(jPanel);
            }
            //input->desktop
            commonReset(input);
        });
        post.addActionListener(e -> {
            JPanel input = new JPanel();//input面板
            //input面板核心组件
            JLabel jLabel1 = new JLabel("目标账户");
            JTextField jTextField1 = new JTextField();
            JLabel jLabel2 = new JLabel("转账金额");
            JTextField jTextField2 = new JTextField();
            JButton jButton = new JButton("转账");
            //为按钮添加监听
            jButton.addActionListener(x -> {
                int money = Integer.parseInt(jTextField2.getText());
                if (money > Main.account.getMoney()) {
                    JOptionPane.showMessageDialog(null, "余额不足");
                } else if (money < 0) {
                    JOptionPane.showMessageDialog(null, "金额非法");
                } else {
                    for (int i = 0; i < FileIO.list.size(); i++) {
                        if (FileIO.list.get(i).getCard().equals(jTextField1.getText())) {
                            Account account = FileIO.list.get(i);
                            FileIO.list.remove(i);

                            Main.account.setMoney(Main.account.getMoney() - money);
                            account.setMoney(account.getMoney() + money);
                            FileIO.list.add(account);
                            try {
                                Account.renew();
                                JOptionPane.showMessageDialog(null, "转账成功");
                                clear();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "无此账户");
                }
            });
            //input面板布局
            input.setLayout(new GridLayout(5, 1));
            //核心组件填充
            JPanel[] jPanels = new JPanel[5];
            jPanels[0] = fillLabel(jLabel1);
            jPanels[1] = fillLabel(jTextField1);
            jPanels[2] = fillLabel(jLabel2);
            jPanels[3] = fillLabel(jTextField2);
            jPanels[4] = fillLabel(jButton);
            //核心组件->input
            for (JPanel jPanel : jPanels) {
                input.add(jPanel);
            }
            //input->desktop
            commonReset(input);
        });
        //退出
        exit.addActionListener(e -> {
            System.out.println("exit");
            JOptionPane.showMessageDialog(null, "请记得取走您的银行卡");
            Main.menu.dispose();
            new Login();
        });
    }
}
