package sample.console.ui.view;

import javax.swing.table.AbstractTableModel;

public class TableModel
    extends AbstractTableModel
{
    private String[] columnNames;
    private String[][] data = new String[][] { };

    public TableModel(String[] columnNames)
    {
        this.columnNames = columnNames;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount()
    {
        return data.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object dataValue, int rowIndex, int columnIndex)
    {
        data[rowIndex][columnIndex] = (String)dataValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void updateTableModelData(String[][] inputData)
    {
        data = new String[inputData.length][getColumnCount()];
        for (int index = 0; index < data.length; index++)
        {
            data[index][0] = String.valueOf(index + 1);
            for (int col = 1; col < getColumnCount(); col++)
            {
                data[index][col] = inputData[index][col - 1];
            }
        }
        fireTableDataChanged();
    }
}
