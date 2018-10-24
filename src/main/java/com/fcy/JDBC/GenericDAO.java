package com.fcy.JDBC;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class GenericDAO<T,ID extends Serializable>{

	private Connection con;//数据库连接对象
	private Class<T> clazz;//实体类类型
	private Class<ID> claid;//实体类ID类类型
	private T t=null;//实体类
	private ID myid=null;//id类
	private Field[] field;//实体类的字段数组
	private String tablename;//该实体类对应的数据库表
	private String[] methodname;//反射需要调用的方法名
	private String[] fieldname;//字段名
	private String[] fieldtype;//字段类型
	public int row;
	
	public String getTablename(String classtostring){//截取数据库表字符串
		int len=classtostring.length();
		String name=classtostring.substring(6,len-3);
		return captureName(name);
	}
	public String captureName(String name,boolean flag){//首字符大小写
		char[] cs=name.toCharArray();
		if(flag)//true
			cs[0]-=32;//大写
		else//false
			cs[0]+=32;//小写
		return String.valueOf(cs);
	}
	public String captureName(String name) {//首字符大写
       name = name.substring(0, 1).toUpperCase() + name.substring(1);
       return  name;
      
    }
	public String[] transform(Field[] field){//转化
		int i=0;
		String[] methodname=new String[field.length];
		fieldname=new String[field.length];
		fieldtype=new String[field.length];
		for(Field f:field){
			String temp=f.getType().getName();
			fieldname[i]=f.getName();//字段名
			
			int index=temp.lastIndexOf(".");
			if(index!=-1){
				temp=temp.substring(index+1);
			}
			fieldtype[i]=temp;//字段类型
			methodname[i]="get"+captureName(temp);//resultset的方法名
			i++;
		}
		return methodname;
	}
	public GenericDAO(){
		Class<?> c=this.getClass();//获取dao层类型
		Type t=c.getGenericSuperclass();//获取GenericDAO类型
		this.clazz=(Class<T>)((ParameterizedType)t).getActualTypeArguments()[0];//获取pojo层类型,参数化类型User
		this.claid=(Class<ID>)((ParameterizedType)t).getActualTypeArguments()[1];
		//t=clazz.newInstance();
		//myid=claid.newInstance();
		
		tablename="My"+getTablename(c.toString());//获取数据库表名

		field=clazz.getDeclaredFields();//获取字段数组
		
		methodname=transform(field);//转化方法名
		/*if(!(t instanceof ParameterizedType)){
			throw new IllegalArgumentException(c+"没有指定的泛体类型");
		}*/
	}
	public T query(ID id){
		T cobj=null;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String sql="select * from "+tablename+" where "+fieldname[0]+"="+id;
		try{
			con=mysql.getConnection();
			PreparedStatement pt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=pt.executeQuery();
			Class cla=Class.forName("java.sql.ResultSet");
			Method next=cla.getDeclaredMethod("next");
			rs.last();
			if(rs.getRow()<1){
				return cobj;//如果结果集中没有数据返回null;
			}
			rs.first();//结果集存在则返回第一行
			List data=new ArrayList();	//存储从数据库返回的数据	
			int i=0;
			for(i=0;i<methodname.length;i++){
				Method m1=cla.getDeclaredMethod(methodname[i],int.class);
				Object obj=m1.invoke(rs,i+1);
				data.add(obj);
			}			
			Constructor cs=clazz.getDeclaredConstructor(List.class);
			cobj=(T)cs.newInstance(data);
			return (T)cobj;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con!=null)
					con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return cobj;
	}
	private List<T> getDataBaseValue(ResultSet rs)throws SQLException{//从一个结果集中返回所有的实体类数据
		List<T> cobj=null;
		//先查看该结果集中是否有数据
		try{
			rs.last();
			if(rs.getRow()<1){
				return cobj;//如果结果集中没有数据返回null;
			}
			rs.first();//结果集存在则返回第一行
			
			Class cla=Class.forName("java.sql.ResultSet");
			Method next=cla.getDeclaredMethod("next");	
			do{
				int i=0;
				List data=new ArrayList();
				for(i=0;i<methodname.length;i++){
					Method m1=cla.getDeclaredMethod(methodname[i],int.class);//调用ResultSet的getXXX方法获取数据
					Object obj=m1.invoke(rs,i+1);
					data.add(obj);//将数据添加进一个List中
				}			
				Constructor cs=clazz.getDeclaredConstructor(List.class);
				T t1=(T)cs.newInstance(data);//通过List获取一个实例
				if(cobj==null)
					cobj=new ArrayList();
				cobj.add(t1);//将实例添加进返回结果中
			}while((boolean)next.invoke(rs));
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException();
		}
		return cobj;
	}
	private String[] getMethodValue(T t)throws SQLException{//返回一个实体类的所有属性值
		String method[]=new String[fieldname.length];
		String value[]=new String[fieldname.length];
		try{
			for(int i=0;i<fieldname.length;i++){
				method[i]="get"+captureName(fieldname[i]);
				Method m=clazz.getDeclaredMethod(method[i]);
				value[i]=String.valueOf(m.invoke(t));
			}
		}catch(Exception e){
			System.out.println(this.getClass().getName()+"加载错误");
			throw new SQLException();
		}
		return value;
		
	}
	public boolean isExist(ID id){
		if(query(id)!=null)//如果存在返回真
			return true;
		else
			return false;
	}
	public ID getId(T t)throws Exception{
		Method m1=clazz.getDeclaredMethod(methodname[0]);
		return (ID)m1.invoke(t);
	}
	public boolean add(T t)throws SQLException{
		boolean addreturn=true;
		String[] value=getMethodValue(t);//获取t的属性值
		myid=(ID)value[0];
		if(isExist(myid)){//如果数据库中有该数据则更新
			update(t);
			System.out.println("添加一条数据");
			return true;
		}
		//否则插入
		String values="";
		for(int i=0;i<value.length;i++)
			values+=("'"+value[i]+"'"+",");
		values=values.substring(0,values.lastIndexOf(","));
		String sql="insert into "+tablename+" values("+values+")";
		
		try{
			Connection con=mysql.getConnection();
			PreparedStatement pt=con.prepareStatement(sql);
			int row=pt.executeUpdate();
			System.out.println(sql);
			if(row==1){
				addreturn=true;
			}else{
				addreturn=false;
				throw new SQLException();
			}
		}catch(SQLException e){
			addreturn=false;
			e.printStackTrace();
			throw new SQLException();
		}finally{
			if(con!=null)
				con.close();
		}
		
		return addreturn;
		
	}
	public int del(ID id)throws SQLException{
		String sql="delete from "+tablename+" where "+fieldname[0]+"="+id;
		int row=0;
		if(!isExist(id)){
			return row;
		}
		try{
			Connection con=mysql.getConnection();
			PreparedStatement pt=con.prepareStatement(sql);
			row=pt.executeUpdate();
			System.out.println("删除一条记录");
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException();
		}finally{
			if(con!=null)
				con.close();
		}
		return row;
	}
	public void update(T t)throws SQLException{
		String[] value=getMethodValue(t);//获取t的属性值
		myid=(ID)value[0];
		if(del(myid)==1)
			add(t);
		else{
			System.out.println("更新失败");
		}
		//String sql="update "+"from "+tablename+" where 
	}
	public List<T> SQL(String sql)throws SQLException{
		ResultSet rs=null;
		Connection con=null;
		Statement st=null;
		List<T> list=null;
		try{
			boolean var=true;
			int row;
			con=mysql.getConnection();
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			var=st.execute(sql);
			if(var){
				rs=st.getResultSet();
				list=getDataBaseValue(rs);
				return list;
			}else{
				row=st.getUpdateCount();
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException();
		}finally{
			if(con!=null){
				con.close();
			}
		}
		return list;
	}
	static class mysql{
		public static Connection getConnection(){
			return null;
		}
	}
	public static void main(String args[])throws Exception{

	}
}
