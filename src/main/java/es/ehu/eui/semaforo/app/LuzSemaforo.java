package es.ehu.eui.semaforo.app;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

public class LuzSemaforo extends JPanel {

	private static final long serialVersionUID = 3049040957332920611L;
	private String color;
	private boolean activo;
	private JLabel lblLuzSemaforo;

	/**
	 * Crea una instancia de LuzSemaforo
	 *
	 */
	public LuzSemaforo(String pColor, boolean pActivo) {
		super();
		initialize();
		color = pColor.toLowerCase();
		setActivo(pActivo);
	}

	/**
	 * Inicializa los componentes del panel
	 */
	private void initialize() {
		this.setSize(getPreferredSize());
		this.setLayout(new GridBagLayout());
		this.add(getLblLuzSemaforo());
	}

	/**
	 * Devuelve la instancia de luz. Si no se ha creado aun, crea primero el
	 * objeto
	 * 
	 * @return la luz del semaforo
	 */
	private JLabel getLblLuzSemaforo() {
		if (lblLuzSemaforo == null) {
			lblLuzSemaforo = new JLabel("");
		}
		return lblLuzSemaforo;
	}

	/**
	 * Modifica el estado de la luz y actualiza la vista
	 * @param pActivo - el estodo de la luz (true- encendida, false - apagada)
	 */
	public void setActivo(boolean pActivo) {
		activo = pActivo;
		
		getLblLuzSemaforo().setIcon(
				new ImageIcon(this.getClass().getResource(
						String.format("/circulo_%1$s.png",
								activo ? color : "apagado"))));

	}
}
