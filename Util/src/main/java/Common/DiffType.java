package Common;


import static Common.Constants.*;

public enum DiffType{
    ADD_CONTENT(0,ADD_CONTENT_STRING),
    DELETE_CONTENT(1,DELETE_CONTENT_STRING),
    NOT_EQUAL_CONTENT(2,DIFFERENT_CONTENT_STRING),
    EQUAL_CONTENT(3,EQUAL_CONTENT_STRING),
    ADD_FILE(4,ADD_FILE_STRING),
    DELETE_FILE(5,DELETE_FILE_STRING),
    NOT_EQUAL_FILE(6,NOT_EQUAL_FILE_STRING),
    EQUAL_FILE(7,EQUAL_FILE_STRING);
    private int index;
    private String description;
    DiffType(int index,String description){
        this.index=index;
        this.description=description;
    }
    public String toString(){
        return this.description;
    }
}