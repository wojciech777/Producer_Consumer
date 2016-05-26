package pl.edu.agh;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * class Producer to klasa ktora dodaje do kolejki swoja literke az liczba
 * literek dodanych przez wszsystkie instancje tej klasynie przekroczy zadanej
 * wielkosci
 * 
 * @author Bomba Wojciech
 * 
 */
public class Producer extends Thread {
	private LinkedBlockingQueue<Character> queue;
	private char myLetter;
	private static int liczbaWyprodukowanychLiter;
	private int maxLiczbaLiter = 50;
	private static Object straznik = new Object(); // to jest straznik ktory
													// gwarantuje zeby zmienna
													// liczba talerzy byla
													// zawsze poprawnie
													// sprawdzana

	/**
	 * konstruktor klasy Producer
	 * 
	 * @param queue
	 *            obiekt klasy LinkedBlockingQueue do ktorej odwoluje sie ten
	 *            watek
	 * @param letter
	 *            litera ktora produkuje ten producent
	 */
	public Producer(LinkedBlockingQueue<Character> queue, char letter) {
		this.queue = queue;
		this.myLetter = letter;
	}

	@Override
	public void run() {
		System.out.println("Producent " + myLetter + " rozpoczal prace");
		for (;;) {
			synchronized (straznik) {
				if (liczbaWyprodukowanychLiter < maxLiczbaLiter) {
					try {
						queue.put(new Character(myLetter));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					++liczbaWyprodukowanychLiter;
				} else {
					try {
						queue.put(new Character('D'));
						break;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Producent " + myLetter + " zakonczyl prace");
	}

}
