package com.fcy.Java.Clone;

public class NormalClone implements Cloneable{
    private String phone;
    private String name;
    @Override
    public String toString() {
        return "address{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public NormalClone(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }
    public Object clone(){
        NormalClone address=null;
        try{
            address=(NormalClone)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return address;
    }
}