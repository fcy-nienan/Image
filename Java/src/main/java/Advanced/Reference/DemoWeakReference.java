package Advanced.Reference;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-06  14:07
 *
 * 四种引用
 *
 * 强引用
 * 软引用
 * 若引用
 * 虚引用
 *
 *
 *
 */
@Data
@NoArgsConstructor
class User{
    private int[] array=new int[102400];
    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
@Slf4j
public class DemoWeakReference {
    public static void main(String[] args) throws InterruptedException, IOException {
        List<Reference<Object>> list=new ArrayList();
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    list.add(new SoftReference(new User(0,"1","df")));
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

        while (true){
            System.out.println("start display:"+list.size());
            for (int i=0;i<list.size();i++){
                System.out.println(list.get(i).get());
            }
            Thread.sleep(3000);
            System.gc();
        }
//        int i=0;
//        long start=System.currentTimeMillis();
//        while (true){
//            if (reference.get()!=null){
//                i++;
//                if (i==119000){
//                    Thread.sleep(1000*6);
//                }
//                log.info(String.valueOf(queue.poll()==null));
//                log.info("object has in memory and live {} loop!",i);
//            }else{
//                log.info("reference queue 's object is {}",queue.poll().get());
//                log.info("object has been collected!");
//                break;
//            }
//        }

    }

}
