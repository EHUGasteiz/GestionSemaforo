package es.ehu.eui.semaforo.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestorSemaforosTest {

	private GestorSemaforos gSemaforos = null;

	@Before
	public void setUp() throws Exception {
		gSemaforos = GestorSemaforos.getGestorSemaforos();
		// Poner el semaforo en verde
		gSemaforos.ponerVerde();
	}

	@Test
	public void testGetGestorSemaforos() {
		assertSame(gSemaforos, GestorSemaforos.getGestorSemaforos());
	}

	@Test
	public void testEstaVerde() {
		assertTrue(gSemaforos.estaVerde());
		while (gSemaforos.getContador() != 1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		assertFalse(gSemaforos.estaVerde());
	}
	
	@Test
	public void testGetContador() {
		int previous = gSemaforos.getContador();
		while (previous != 1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			int current = gSemaforos.getContador();

			assertEquals(previous-1,current);
			previous=current;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		while (previous != 1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			int current = gSemaforos.getContador();

			assertEquals(previous-1,current);
			previous=current;
		}
	}
	
	@Test
	public void testPonerVerde() {
		assertTrue(gSemaforos.estaVerde());
		while (gSemaforos.getContador() != 1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		assertFalse(gSemaforos.estaVerde());
		gSemaforos.ponerVerde();

		assertTrue(gSemaforos.estaVerde());
	}
}
