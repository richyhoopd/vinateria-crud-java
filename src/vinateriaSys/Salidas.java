package vinateriaSys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author Oscar ppai Diaz
 * Inventario de Vinateria Familiar
 *
 */

public class Salidas extends JFrame {
	
	private JTextField nombreEntrada;
	private JTextField estadoVino;
	private JTextField descVino;
	private JTextField precioVino;
	
	private JButton btGuardar;
	private JButton btEditar;
	private JButton btBorrar;
	private JButton btBuscar;
	private JButton btLimpar;
	
	private JLabel jnome;
	private JLabel janerepo;
	private JLabel jdescvino;
	private JLabel jprecio;
	
	private JTable tabla;
	
	private JRadioButton rbAnejo;
	private JRadioButton rbReposado;
	private ButtonGroup grupo;
	
	private ArrayList<Vino> listTable = new ArrayList<>();
	private ArrayList<Vino> listCopy = new ArrayList<>();
	
	private JScrollPane scrollPane;
	private Modelo myModel;
	
	private char anejoReposado;
	
	private archivoDBVino archivo;

	
	public Salidas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(10, 10, 600, 460);
		this.setTitle("Vinos y Licores Oscar");
		
		jnome = new JLabel("Nombre:");
		nombreEntrada = new JTextField();
		
		janerepo = new JLabel("Añejo o Reposado:");
		estadoVino = new JTextField();
		
		jdescvino = new JLabel("Descripcion:");
		descVino = new JTextField();
		
		jprecio = new JLabel("Precio:");
		precioVino = new JTextField();
		
		btGuardar = new JButton("Guardar");
		btGuardar.addActionListener(listenerGuardar());
		
		btEditar = new JButton("Editar");
		btEditar.addActionListener(listenerEditar());
		
		btBuscar = new JButton("Buscar");
		btBuscar.addActionListener(listenerBuscar());
		
		btBorrar = new JButton("Borrar");
		btBorrar.addActionListener(listenerBorrar());
		
		btLimpar = new JButton("Limpar");
		btLimpar.addActionListener(listenerOfLimpar());
		
		myModel = new Modelo(listTable);
		tabla = new JTable(myModel);
		
		tabla.addMouseListener(mouseMovimiento());
		
		rbAnejo = new JRadioButton();
		rbReposado = new JRadioButton();
		grupo = new ButtonGroup();	
		
		rbAnejo.setText("Añejo");
		rbReposado.setText("Reposado");
		
		//table.setBounds(100, 170, 445, 200);
		
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(76, 170, 445, 200);
		
		archivo = new archivoDBVino("vinosinv.txt");
		
		archivo.creaObjeto(listTable);//Montando arquivo no array
		
		rbAnejo.setBounds(400, 40, 80, 30);
		rbReposado.setBounds(490, 40, 80, 30);
		
		jnome.setBounds(76, 15, 50, 30);
		janerepo.setBounds(430, 15, 50, 30);
		jdescvino.setBounds(76, 65, 100, 30);
		jprecio.setBounds(430, 65, 100, 30);
		
		nombreEntrada.setBounds(76, 40, 300, 30);
		estadoVino.setBounds(430, 40, 90, 30);
		descVino.setBounds(76, 90, 350, 30);
		precioVino.setBounds(430, 90, 90, 30);
		
		btGuardar.setBounds(76, 130, 100, 30);
		btBuscar.setBounds(185, 130, 120, 30);
		btBorrar.setBounds(314, 130, 100, 30);
		btEditar.setBounds(423, 130, 94, 30);
		btLimpar.setBounds(225, 375,100, 30);
		
		this.add(rbReposado);
		this.add(rbAnejo);
		
		this.add(nombreEntrada);
		this.add(jnome);
		//this.add(inputSexo);
		//this.add(jsexo);
		
		this.add(descVino);
		this.add(jdescvino);
		
		this.add(precioVino);
		this.add(jprecio);
		
		this.add(btGuardar);
		this.add(btEditar);
		this.add(btBuscar);
		this.add(btBorrar);
		this.add(btLimpar);
		
		this.add(scrollPane);
		
		grupo.add(rbAnejo);
		grupo.add(rbReposado);

	}
		
	private MouseListener mouseMovimiento() {
			MouseListener mouse = new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent evt) {
					int n = tabla.getSelectedRow();
					Vino s = myModel.getRow(n);
					
					nombreEntrada.setText(s.tomarNomVino());
					
					if(s.tomarAnejo() == 'M') {
						rbAnejo.setSelected(true);;
					}else {
						rbReposado.setSelected(true);
					}
					
					precioVino.setText(String.valueOf(s.tomarPrecio()));
					descVino.setText(s.tomarDescripcion());
				
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
	
				}
			};
			return mouse;
		}

	private ActionListener listenerBorrar() {
		ActionListener myListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				archivo.borraDatos(listTable, precioVino.getText().trim());
				int n = tabla.getSelectedRow();
				
				archivo.creaObjeto(listTable);
				myModel.fireTableRowsDeleted(n, n);
				myModel.fireTableDataChanged();
				
				limpaCampos();
			}
		};
		return myListener;
	}

	private ActionListener listenerBuscar() {
		ActionListener myListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dados = archivo.busqueda(precioVino.getText().trim(), listTable);
				
				String [] recebeSplit = dados.split(";");
				nombreEntrada.setText(recebeSplit[1]);
				estadoVino.setText(recebeSplit[3]);
				precioVino.setText(recebeSplit[0]);
				descVino.setText(recebeSplit[2]);
				
			}
		};
		return myListener;
	}

	private ActionListener listenerEditar() {
		ActionListener myListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int n = tabla.getSelectedRow();
				anejoReposado = radioAnejoReposado();
				Vino s = new Vino(nombreEntrada.getText(), anejoReposado, Integer.parseInt(precioVino.getText()), descVino.getText());

				listTable.add(n, s);
				listCopy.addAll(listTable);
				
				archivo.editaArchivo(listCopy, Integer.parseInt(precioVino.getText()), s);
				archivo.creaObjeto(listTable);
				
				myModel.fireTableRowsUpdated(n, n);

			}
		};
		return myListener;
	}

	private ActionListener listenerGuardar() {
		ActionListener myListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				anejoReposado = radioAnejoReposado();
				if(nombreEntrada.getText().trim().equals("") || descVino.getText().trim().equals("") ||precioVino .getText().trim().equals("") || anejoReposado == 'n') {
					JOptionPane.showMessageDialog(Salidas.this, "TODOS LOS CAMPOS SON OBLIGATORIOS", "Campo Obligatorio", JOptionPane.WARNING_MESSAGE);
				}else {
						
						boolean limpa;
						listTable.add(new Vino(nombreEntrada.getText(), anejoReposado, Integer.parseInt(precioVino.getText()), descVino.getText()));								
						
						if(listTable.size() > 0) {	
							listCopy.add(listTable.get(listTable.size() -1));
						}
						
						limpa = archivo.guardaDatos(listCopy);
						archivo.creaObjeto(listTable);
						myModel.fireTableDataChanged();
						
						if (limpa == true) {
							limpaCampos();
						}
						
					
				}
			}
			
		};
		return myListener;
	}
	
	private char radioAnejoReposado() {
		char an = ' ';
		if(rbAnejo.isSelected()) {
			an = 'A';
		} else if(rbReposado.isSelected()) {
			an = 'R';
		} else {
			JOptionPane.showMessageDialog(Salidas.this, "Escoja un tipo de vino");
			return an = 'n';
		}
		return an;
	}
	
	private ActionListener listenerOfLimpar() {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
			}
		};
		return listener;
	}
	
	private void limpaCampos() {
		nombreEntrada.setText("");
		grupo.clearSelection();
		descVino.setText("");
		precioVino.setText("");
	}

	
	  public static void main(String[] args) {
		  Salidas tela = new Salidas();
		  tela.setVisible(true);
	  }
	 
	
}
