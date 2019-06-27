package com.sheen.water.tools;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class JTableDataUtil<T>
{
	private Class<T> clazz;
	public JTableDataUtil(Class<T> clazz)
	{
		this.clazz = clazz;
	}
	/**
	 * 该方法是把保存好的list数据保存到vector中，方便
	 *  向jtable中传输多行数据
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Vector> getVector(List<T> list)
	{
		List<Vector> vectorList = new ArrayList<Vector>();
		Field[] fields = clazz.getDeclaredFields();
		for (T t : list)
		{
			// Class clazz = obj.getClass();
			// Field[] fields = clazz.getDeclaredFields();
			Vector<Object> vector = new Vector<Object>();
			for (Field field : fields)
			{
				try
				{
					PropertyDescriptor pd = new PropertyDescriptor(
							field.getName(), clazz);
					Method getMethod = pd.getReadMethod();
					Object value = getMethod.invoke(t);
					vector.add(value);
				} catch (IntrospectionException e)
				{
					e.printStackTrace();
				} catch (InvocationTargetException e)
				{
					e.printStackTrace();
				} catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
			vectorList.add(vector);
		}
		return vectorList;
	}

	/**
	 * 该方法是把保存好的list数据保存到Object[][]2维数组中，方便
	 * 向jtable中传输多行数据
	 * @param list
	 * @return
	 */
	public Object[][] getObjData(List<T> list)
	{
		Field[] fields = clazz.getDeclaredFields();
		Object[][] data = new Object[list.size()][fields.length];
		for (int i = 0; i < list.size(); i++)
		{
			T t = (T) list.get(i);
			Object[] a = new Object[fields.length];
			for (int j = 0; j < fields.length; j++)
			{
				Field field = fields[j];
				try
				{
					PropertyDescriptor pd = new PropertyDescriptor(
							field.getName(), clazz);
					Method getMethod = pd.getReadMethod();
					Object value = getMethod.invoke(t);
					a[j] = value;
				} catch (IntrospectionException e)
				{
					e.printStackTrace();
				} catch (InvocationTargetException e)
				{ 
					e.printStackTrace();
				} catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
			data[i] = a;
		}
		return data;
	}
	
	/**
 	* 该方法是把javabean封装的数据保存到vector方便jtable使用
 	* @param t
 	* @return
 	*/
	public Vector<Object> addData(T t)
	{
		Field[] fields = clazz.getDeclaredFields();
		Vector<Object> vector = new Vector<Object>();
		for (Field field : fields)
		{
			try
			{
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
				Method getMethod = pd.getReadMethod();
				Object value = getMethod.invoke(t);
				vector.add(value);
			} catch (IntrospectionException e)
			{
				e.printStackTrace();
			} catch (InvocationTargetException e)
			{
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return vector;
	}
}