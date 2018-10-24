package com.fcy.Sort;

public class student implements Comparable<student>{
    private String username;
    private int age;
    private int Score;

    public student(String username, int age, int score) {
        this.username = username;
        this.age = age;
        Score = score;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int compareTo(student o) {
        if(o.getScore()>this.Score){
            return 1;
        }else if(o.getScore()==this.Score) {
            return 0;
        }else{
            return -1;
        }
    }
}
