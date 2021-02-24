package es.ehu.eui.semaforo.model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase representa el modulo que gestiona los semaforos.
 * Implementa los patrones Singleton y Observable
 * 
 * @author mikel
 *
 */
public class GestorSemaforos  extends Observable{
	private static GestorSemaforos mGestorSemaforos = new GestorSemaforos();
	private boolean estaVerde; // Indica si el semaforo esta verde para los peatones o no
	private static final int PERIODO = 15;
	private int cont;
	private Timer timer = null;
	
	private GestorSemaforos ()
	{
		estaVerde = false;
		cont = PERIODO;
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				actualizarCont();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	/**
	 * getGestorSemaforos
	 * Devuelve la instancia unica de la clase
	 * @return el gestor de semaforos
	 */
	public static GestorSemaforos getGestorSemaforos() {
		return mGestorSemaforos;
	}
	
	/**
	 * getContador
	 * Devuelve cuantos segundos quedan para cambiar la luz del semaforo
	 * @return
	 */
	public int getContador() {
		return cont;
	}
	
	/**
	 * estaVerde
	 * Devuelve true si el semaforo esta verda para los peatones y falso en caso contrario
	 * @return
	 */
	public boolean estaVerde() {
		return estaVerde;
	}
	
	/**
	 * ponerVerde
	 * Si el semafor esta rojo para los peatones, lo pone en verde
	 */
	public void ponerVerde() {
		if (!estaVerde()) {
			cont = PERIODO;
			estaVerde = true;
			// Notificar el cambio
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Actualiza el contador del tiempo. Cuando llega a 0 cambia el color de la luz
	 */
	private void actualizarCont() {
		cont--;
		if (cont == 0) {
			cont = PERIODO;
			estaVerde = !estaVerde;
		}
		// Notifica el cambio en el estado
		setChanged();
		notifyObservers();
		
	}
}
