import java.io.IOException;
import java.io.Serializable;

/**
 * @author wei
 * @since 2020/5/16 19:24
 * Bean对象为以后的数据库操作拓展提供实体类
 */
public class Account implements Serializable {
    private int id;
    private String card;
    private String pwd;
    private int money;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //刷新用户信息
    public static void renew() throws IOException {
        for (int i = 0; i < FileIO.list.size(); i++) {
            if (FileIO.list.get(i).getId() == Main.account.getId()) {
                FileIO.list.remove(i);
                FileIO.list.add(Main.account);
                break;
            }
        }
        FileIO.Write();
    }
}
