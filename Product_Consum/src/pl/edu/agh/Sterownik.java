package pl.edu.agh;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * wywoluje odpowiednie watki przez co program poprawnie symuluje prace Pracownikow i Konsumentow
 * @author Administrator
 *
 */
public class Sterownik {
	private static Producer producerA;
	private static Producer producerB;
	private static Producer producerC;
	private static Consumer consumer1;
	private static Consumer consumer2;
	private static LinkedBlockingQueue<Character> queue = new LinkedBlockingQueue<Character>();

	public static void main(String[] args) {
		producerA = new Producer(queue, 'A');
		producerB = new Producer(queue, 'B');
		producerC = new Producer(queue, 'C');
		consumer1 = new Consumer(queue, 1);
		consumer2 = new Consumer(queue, 2);

		producerA.start();
		producerB.start();
		producerC.start();
		consumer1.start();
		consumer2.start();

		// System.out.println("Program konczy prace"); //ku mojemu (lekkiemu)
														// zdziwieniu to nie wypisywalo sie na koncu
	}
}
