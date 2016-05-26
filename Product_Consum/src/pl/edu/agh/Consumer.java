package pl.edu.agh;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * class Consumer pobiera kolejne literki z kolejki i wyswietla je az do momentu
 * gdy napotka zatruta literke ktora powoduje przerwanie dzialania programu
 * 
 * @author Bomba Wojciech
 * 
 */
public class Consumer extends Thread {
	private LinkedBlockingQueue<Character> queue;
	private Character trucizna = new Character('D');
	private Character bufor = null;
	private int mojNumer;

	/**
	 * konstruktor klasy Consumer
	 * 
	 * @param queue
	 *            obiekt klasy LinkedBlockingQueue do ktorej odwoluje sie ten
	 *            watek
	 * @param numerPorzadkowy
	 *            numer ktory nadawany jest konsumentowi w celu jego latwej
	 *            identyfikacji
	 */
	public Consumer(LinkedBlockingQueue queue, int numerPorzadkowy) {
		this.queue = queue;
		mojNumer = numerPorzadkowy;
	}

	@Override
	public void run() {
		System.out.println("Consumer " + mojNumer + " rozpoczal prace");
		for (;;) {
			if (queue.size() > 0) {
				try {
					bufor = queue.peek();

					if (bufor.equals(trucizna))
						break;
					else
						System.out.println(queue.take());

					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Consumer " + mojNumer + " zakonczyl prace");
	}
}
