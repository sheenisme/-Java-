package com.sheen.water.data.Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.sheen.water.data.po.Orders;

public class OrdersTableModel extends AbstractTableModel {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Orders> data = new ArrayList<Orders>();
 
  @Override
  public int getRowCount() {
    return data.size();
  }
 
  @Override
  public int getColumnCount() {
    //根据实际情况返回列数
    return 7;
  }
 
  @Override
  public String getColumnName(int column) {
    //根据实际情况返回列名
    if (column == 0)
      return "no";
    else if(column == 1)
    	return "customer_no";
    else if(column == 2)
    	return "order_time";
    else if(column == 3)
    	return "total_money";
    else if(column == 4)
    	return "state";
    else if(column == 5)
    	return "sender_phone";		
    return "sender_name";
  }
 
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Orders po = data.get(rowIndex);
    if (columnIndex == 0)
      return po.getNo();
    else if (columnIndex == 1)
        return po.getCustomer_no();
    else if (columnIndex == 2)
        return po.getOrder_time();
    else if (columnIndex == 3)
        return po.getTotal_money();
    else if (columnIndex == 4)
        return po.getState();
    else if (columnIndex == 5)
        return po.getSender_phone();
       return po.getSender_name();
  }
 
  public void setData(List<Orders> data) {
    if (data == null)
      throw new IllegalArgumentException("参数data不能为null。");
    this.data = data;
 
    fireTableDataChanged();
  }
}