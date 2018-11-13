package at.pwimmer.test.net.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class IPTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private static final String[] columnNames = {"Name", "IP-Adresse"};
	private static final Class<?>[] columnClasses = {String.class, String.class};
	
	private final List<PingSystem> list;
	
	public IPTableModel(List<PingSystem> list) {
		if(list != null) {
			this.list = list;
		}
		else {
			throw new IllegalArgumentException("The passed Map is null!");
		}
	}
	
	public IPTableModel() {
		this.list = new ArrayList<>();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClasses[columnIndex];
	}

	@Override
	public int getRowCount() {
		return list.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:		return list.get(rowIndex).getName();
		case 1:		return list.get(rowIndex).getIPAddress();
		default:	return "N/A";
		}
	}
}