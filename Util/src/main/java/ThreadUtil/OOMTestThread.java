package ThreadUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OOMTestThread extends Thread{
    private static List<Object> list=new ArrayList<>();

    @Override
    public void run () {
        while (true){
            try {
                list.add(new byte[1024 * 100]);
                TMS.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }catch (Error error){

            }
        }
    }
}
