package Faker;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
@Slf4j
public class DemoFaker {
    private static void faker(){
        Map<String,String> map=new LinkedHashMap<>();
        Faker faker=new Faker(Locale.CHINA);
        map.put("name",faker.name().name());

    }
    private static void DemoFaker(){
        Faker faker = new Faker(Locale.CHINA);
        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton

        String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(streetAddress);
        System.out.println(name);
    }
    private static void makeDataForWordCount() throws FileNotFoundException, UnsupportedEncodingException {
        int count=100000;
        String fileFullPath="D:\\data.txt";
        List<String> list = getList(count);
        Collections.shuffle(list);
        //多少个单词生成一个换行符
        Random random=new Random();

        File file=new File(fileFullPath);
        if (file.exists()){
            file.delete();
            log.warn("file is existed and delete it !");
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("create file failed!");
            return ;
        }
        FileOutputStream fileInputStream=new FileOutputStream(file);
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fileInputStream, "utf-8"));
        int newLine=random.nextInt(10);
        int wordCount=0;
        int flushCount=10000;
        log.info("start to write and size :"+list.size());
        for (String s : list) {
            try {
                writer.write(s);
                wordCount++;
                flushCount--;
                writer.write(" ");
                if (wordCount==newLine){
                    writer.newLine();
                    newLine=random.nextInt(10);
                }
                if (flushCount==0){
                    writer.flush();
                    flushCount=10000;
                    log.info("flush finished!");
                }
            } catch (IOException e) {
                try {
                    writer.flush();
                    writer.close();
                }catch (Exception t){
                    t.printStackTrace();
                }
                log.error("write error and close it !");
                e.printStackTrace();
                return;
            }
        }
        try {
            writer.flush();
            writer.close();
        }catch (Exception e){
            log.error("close writer failed!");
            e.printStackTrace();
        }
    }
    private static List<String> getList(int count){
        Faker faker=new Faker(Locale.CHINA);
        List<String> list=new ArrayList();
        add(list,faker.name().firstName(),count);
        add(list,faker.name().lastName(),count);
        add(list,faker.address().country(),count);
        add(list,faker.address().cityName(),count);
        add(list,faker.address().streetName(),count);
        add(list,faker.book().author(),count);
        add(list,faker.book().publisher(),count);
        add(list,faker.book().title(),count);
        return list;
    }
    private static void add(List<String> list,String value,int count){
        for (int i=0;i<count;i++){
            list.add(value);
        }
    }
}
