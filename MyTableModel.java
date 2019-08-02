import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class MyTableModel extends AbstractTableModel {

	public MyTableModel() {
		// TODO Auto-generated constructor stub
	}
	
	private String[] columns;
    private Object[][] rows;
    
    public MyTableModel(Object[][] data, String[] columnsName){
        this.columns = columnsName;
        this.rows = data;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int col)
    {
        return getValueAt(0, col).getClass();
    }
    
    @Override
    public int getRowCount() {
    
        return this.rows.length;
        
    }
    
    @Override
    public int getColumnCount() {
    
        return this.columns.length;    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    
        return this.rows[rowIndex][columnIndex];        
    }
    
    @Override
    public String getColumnName(int col){
        
        return this.columns[col];
        
    }
   
}
