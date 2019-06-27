package com.sheen.water.data.Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.sheen.water.data.po.Products;
 
public class ProductsTableModel extends AbstractTableModel {
 
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Products> data = new ArrayList<Products>();
 
  @Override
  public int getRowCount() {
    return data.size();
  }
 
  @Override
  public int getColumnCount() {
    //根据实际情况返回列数
    return 2;
  }
 
  @Override
  public String getColumnName(int column) {
    //根据实际情况返回列名
    if (column == 0)
      return "Kind";
    return "Price";
  }
 
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Products po = data.get(rowIndex);
    if (columnIndex == 0)
      return po.getKind();
    return po.getPrice();
  }
 
  public void setData(List<Products> data) {
    if (data == null)
      throw new IllegalArgumentException("参数data不能为null。");
    this.data = data;
 
    fireTableDataChanged();
  }
}