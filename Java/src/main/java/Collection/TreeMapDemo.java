package Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

public class TreeMapDemo {
    public static void main(String[] args) {
        HashSet set;
        ArrayList list;

//        testComparableTreeMap();
        testCompartorTreeMap();
    }
    public static void testComparableTreeMap(){
        Random random=new Random();
        Map<User,String> map=new TreeMap<>();
        for(int i=0;i<10;i++){
            int x=random.nextInt(100);
            System.out.println(x);
            map.put(new User("username",x),i+" value");
        }
        Set<Map.Entry<User,String>> set=map.entrySet();
        set.forEach(e->{
            System.out.println(e.getKey()+"  "+e.getValue());
        });
    }
    public static void testCompartorTreeMap(){
        Random random=new Random();
        Map<Person,String> map=new TreeMap<>(new ComparatorDemo());
        for(int i=0;i<10;i++){
            map.put(new Person(random.nextInt(100)),"value "+i);
        }
        Set<Map.Entry<Person,String>> entries=map.entrySet();
        entries.forEach(e->{
            System.out.println(e.getKey()+"  "+e.getValue());
        });
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person{
        private int age;
    }
    static class ComparatorDemo implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            Person p1=(Person)o1,p2=(Person)o2;
            return p1.getAge()-p2.getAge();
        }
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class User implements Comparable
    {
        private String username;
        private int age;
        @Override
        public int compareTo(Object o) {
            User u=(User)o;
            if(u.age==this.age){
                return 1;
            }
            return this.age-u.getAge();
        }
    }
}
