package Grammer.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class DemoReferenceOrValue {
    public static void main (String args[]) {
        student student=new student("fcy","123");
        System.out.println(student);
        update(student);
        System.out.println(student);
        setNull(student);
        System.out.println(student);
    }
    public static void update(student student){
        student.setUsername("updated:"+student.getUsername());
    }
    public static void setNull(student student){
        student=null;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class student{
        private String username;
        private String password;
    }
}
