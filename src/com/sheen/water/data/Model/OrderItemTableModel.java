package com.sheen.water.data.Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.sheen.water.data.po.OrderItems;

public class OrderItemTableModel extends AbstractTableModel{

	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<OrderItems> data = new ArrayList<OrderItems>();
	 
	  @Override
	  public int getRowCount() {
	    return data.size();
	  }
	 
	  @Override
	  public int getColumnCount() {
	    //根据实际情况返回列数
	    return 5;
	  }
	 
	  @Override
	  public String getColumnName(int column) {
	    //根据实际情况返回列名
	    if (column == 0)
	      return "order_no";
	    else if(column == 1)
	    	return "kind";
	    else if(column == 2)
	    	return "price";
	    else if(column == 3)
	    	return "quantity";
	   return "sub_total";
	  }
	 
	  @Override
	  public Object getValueAt(int rowIndex, int columnIndex) {
	    OrderItems po = data.get(rowIndex);
	    if (columnIndex == 0)
	      return po.getOrder_no();
	    else if (columnIndex == 1)
	        return po.getKind();
	    else if (columnIndex == 2)
	        return po.getPrice();
	    else if (columnIndex == 3)
	        return po.getQuantity();
	   return po.getSub_total();
	  }
	 
	  public void setData(List<OrderItems> data) {
	    if (data == null)
	      throw new IllegalArgumentException("参数data不能为null。");
	    this.data = data;
	 
	    fireTableDataChanged();
	  }
}
