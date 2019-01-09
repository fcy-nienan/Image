package com.fcy.Java.Serializable;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @descripiton Externalizable
 * 
 * @author fcy
 * @date 2013-10-15
 *
 */
public class ExternalizableTest implements Externalizable {

    private transient String content;
	private transient String testfield;
	public String second;
	public ExternalizableTest(){
		content="";
	}
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(content);
    }
	public String toString(){
		return content+"  "+testfield+"  "+second;
	}
    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        content = (String) in.readObject();
    }

    public static void main(String[] args) throws Exception {
        
        ExternalizableTest et = new ExternalizableTest();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                new File("test")));
        out.writeObject(et);

        ObjectInput in = new ObjectInputStream(new FileInputStream(new File(
                "test")));
        ExternalizableTest st = (ExternalizableTest) in.readObject();
        System.out.println(st);
		//System.out.println(st.testfield);
		//System.out.println(st.second);
        out.close();
        in.close();
    }
}