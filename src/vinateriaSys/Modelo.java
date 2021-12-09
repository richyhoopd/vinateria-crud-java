package vinateriaSys;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Modelo extends AbstractTableModel {

	private ArrayList<Vino> lista;
	private String[] cabecera = {"Nombre", "Tipo", "Descripcion", "Precio"};
	
	public Modelo(ArrayList<Vino> list) {
		this.lista = list;
	}


	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return cabecera[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Vino student = lista.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return student.tomarNomVino();
			case 1:
				return student.tomarAnejo();
			case 2:
				return student.tomarDescripcion();
			case 3:
				return student.tomarPrecio();
	
			default:
				return null;
		}
	}
	
	public Vino getRow(int indiceFila) {
		return lista.get(indiceFila);
	}
	
	public void add(Vino s) {
		this.lista.add(s);
		fireTableDataChanged();
	}
	
	public void remove(int indice) {
		this.lista.remove(indice);
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}
}
