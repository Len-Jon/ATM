import java.io.*;
import java.util.LinkedList;

/**
 * @author wei
 * @since 2020/5/18 21:40
 */
public class FileIO {
    public static LinkedList<Account> list;

    public static File fl;
    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;

    public FileIO() throws IOException, ClassNotFoundException {
        fl = new File("F:/Account.dat");
        if (!fl.exists()) {
            fl.createNewFile();
            Account account1 = new Account();
            account1.setId(1);
            account1.setPwd("123456");
            account1.setCard("888888");
            account1.setMoney(50000);
            Account account2 = new Account();
            account2.setId(2);
            account2.setPwd("222222");
            account2.setCard("2");
            account2.setMoney(5000);
            list = new LinkedList<>();
            list.add(account1);
            list.add(account2);
            Write();
        } else {
            Read();
        }
    }

    public static void Read() throws IOException, ClassNotFoundException {
        fis = new FileInputStream(fl);
        ois = new ObjectInputStream(fis);
        list = (LinkedList<Account>) ois.readObject();
        ois.close();
        fis.close();
    }

    public static void Write() throws IOException {
        fos = new FileOutputStream(fl);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.flush();
        oos.close();
        fos.close();
    }
}
