package es.ehu.eui.semaforo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import es.ehu.eui.semaforo.model.GestorSemaforos;

import javax.swing.JButton;

public class SemaforoPeatones extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 820323745068010478L;
	private JPanel contentPane;
	private JPanel pnlSemaforo;
	private LuzSemaforo luzVerde = null;
	private LuzSemaforo luzRoja = null;
	private Controlador controlador = null;
	private JLabel lblCont;
	private JButton btnPedirPaso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SemaforoPeatones frame = new SemaforoPeatones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SemaforoPeatones() {
		initialize();
		GestorSemaforos.getGestorSemaforos().addObserver(this);
		update(null, null);
	}

	/**
	 * Inicializa los componentes de la ventana
	 */
	private void initialize() {
		setSize(150, 555);
		this.setContentPane(getContentPane());
		setTitle("Peatones");
		addWindowListener(getControlador());
		// Centrar la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		setLocation(0,0);
		setVisible(true);

	}
	
	/**
	 * Devuelve el panel principal. Si no se ha construido aun, crea una nueva instancia
	 * @return el panel principal
	 */
	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(getPnlSemaforo(), BorderLayout.CENTER);
		}
		return contentPane;
	}

	/**
	 * Deveuelve el panel del semaforo. Si no se ha construido aun, se crea una nueva instancia
	 * @return el panel del semafor
	 */
	private JPanel getPnlSemaforo() {
		if (pnlSemaforo == null) {
			pnlSemaforo = new JPanel();
			pnlSemaforo.setLayout(new GridLayout(4, 1, 0, 10));
			pnlSemaforo.add(getLblCont());
			pnlSemaforo.add(getLuzRoja(),null);
			pnlSemaforo.add(getLuzVerde(),null);
			pnlSemaforo.add(getBtnPedirPaso());
		}
		return pnlSemaforo;
	}
	
	/**
	 * Devuelve el panel para la luz roja. Si no se ha construido aun, se crea una nueva instancia
	 * @return el panel que representa la luz roja
	 */
	private LuzSemaforo getLuzRoja() {
		if (luzRoja == null) {
			luzRoja = new LuzSemaforo("rojo", true);
		}
		return luzRoja;
	}

	/**
	 * Devuelve el panel para la luz verde. Si no se ha construido aun, se crea una nueva instancia
	 * @return el panel que representa la luz verde
	 */
	private LuzSemaforo getLuzVerde() {
		if (luzVerde == null) {
			luzVerde = new LuzSemaforo("verde", false);
		}
		return luzVerde;
	}
	
	/**
	 * Devuelve el controlador de la ventana. Si no se ha construido aun, se cra una nueva instancia
	 * @return
	 */
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}

	private class Controlador extends WindowAdapter implements ActionListener {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			GestorSemaforos.getGestorSemaforos().ponerVerde();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		GestorSemaforos gs = GestorSemaforos.getGestorSemaforos();
		boolean estaVerde = gs.estaVerde();
		if (estaVerde) {
			getLblCont().setForeground(Color.GREEN);
		}
		else {
			getLblCont().setForeground(Color.RED);		
		}
		getLuzVerde().setActivo(estaVerde);
		getLuzRoja().setActivo(!estaVerde);
		getLblCont().setText(String.valueOf(gs.getContador()));
		getBtnPedirPaso().setEnabled(!estaVerde);

	}
	private JLabel getLblCont() {
		if (lblCont == null) {
			lblCont = new JLabel("15");
			lblCont.setOpaque(true);
			lblCont.setBackground(Color.BLACK);
			lblCont.setFont(new Font("Lucida Grande", Font.BOLD, 72));
			lblCont.setHorizontalAlignment(SwingConstants.CENTER);
			lblCont.setForeground(Color.RED);
		}
		return lblCont;
	}
	private JButton getBtnPedirPaso() {
		if (btnPedirPaso == null) {
			btnPedirPaso = new JButton("Pedir paso");
			btnPedirPaso.addActionListener(getControlador());
		}
		return btnPedirPaso;
	}
}
