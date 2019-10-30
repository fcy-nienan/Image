package Diff;

import static Diff.FileDifferentInfo.*;

public enum DiffType{
    ADD(0,ADDENTRY),DELETE(1,DELETEENTRY),CONTENTDIFF(2,ENTRYDIFF);
    private int inddex;
    private String description;
    DiffType(int index,String description){
        this.inddex=index;
        this.description=description;
    }
    public String toString(){
        return this.description;
    }
}