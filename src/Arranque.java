import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
class Ventana extends JFrame implements KeyListener, ActionListener {
	
	int width = 26;
	int height = 26;
	boolean decim = false;
	
	
	
	JTextField grad = new JTextField(10);
	JComboBox comboDesde = new JComboBox();
	
	JComboBox comboGrados = new JComboBox();
	JTextField cajaRes = new JTextField(10);
	
	
	
	Conversor c = new Conversor();
	
	public Ventana() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setTitle("conversor");
		setVisible(true);
		
		dimensionar(new JLabel("Convertir:"), 1, 1, 3, 1);
		dimensionar(grad, 4, 1, 2, 1);
		dimensionar(comboDesde, 7, 1, 4, 1);
		grad.addKeyListener(this);
		grad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!grad.getText().equals("")) {
					String r = ""+c.res(Double.parseDouble(grad.getText()));
					cajaRes.setText(decimales(r, 2));
				}
				
			}
		});
		comboDesde.addItem('C');
		comboDesde.addItem('F');
		comboDesde.addItem('K');
		comboDesde.addItem('R');
		comboDesde.addActionListener(this);
		
		dimensionar(new JLabel("A:"), 1, 3, 1, 1);
		comboGrados.addItem('F');
		comboGrados.addItem('C');
		comboGrados.addItem('R');
		comboGrados.addItem('K');
		dimensionar(comboGrados, 2, 3, 4, 1);
		comboGrados.addActionListener(this);
		
		
		dimensionar(cajaRes, 7, 3, 3, 1.25);
		cajaRes.setEditable(false);
		
	}
	void dimensionar(JComponent c, int x, int y, double w, double h) {
		c.setBounds(x*width,y*height,(int)w*width,(int)h*height);
		add(c);
	}
	boolean impedirLetras(KeyEvent e) {
		char a = e.getKeyChar();
		return !(Character.isDigit(a) || (a == '.' && !decim));
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		for(char l : grad.getText().toCharArray()) {
			
			if(l == '.') { decim = true; break;}
			
		}
		
		if(impedirLetras(e)) e.consume();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox contraria = null;
		JComboBox actual = (JComboBox)e.getSource();
		//identificar la seleccion previa
		char contra, act;
		if(e.getSource() == comboDesde) {
			
			contraria = comboGrados;
			act = c.desde;
			contra = c.grado;
		}else {
			
			contraria = comboDesde;
			act = c.grado;
			contra = c.desde;
		}
		//invertir la seleccion si se escoge la misma escala de grados
		if(actual.getSelectedItem() == contraria.getSelectedItem()) {
			contraria.setSelectedItem(act);
		}
		//actualizar estados
		c.desde = (char)comboDesde.getSelectedItem();
		c.grado = (char)comboGrados.getSelectedItem();
		
		if(!grad.getText().equals("")) {
			String r = ""+c.res(Double.parseDouble(grad.getText()));
			cajaRes.setText(decimales(r, 2));
		}
	}
	String decimales(String r, int n) {
		int p = r.indexOf('.');
		if(p!= -1) r = r.substring(0, Math.min(r.length(), p+n+1));
		return r;
	}
}
public class Arranque {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ventana();
	}

}
