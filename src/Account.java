/**
 * @author wei
 * @since 2020/5/16 19:24
 * Bean对象为以后的数据库操作拓展提供实体类
 */
public class Account {
    private int id;
    private String card;
    private String pwd;

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
}
