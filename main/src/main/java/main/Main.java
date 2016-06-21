package main;

import java.io.IOException;

import contract.*;
import map.Map;

/**
 * The Class Main.
 *
 * @author Jean-Aymeric Diet
 */
public abstract class Main {

	/**
	 * The main method.
	 *
	 * @param args
	 *          the arguments
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(final String[] args) throws IOException, InterruptedException {
		//final Model model = new Model();
		/*final View view = new View(model);
		final Controller controller = new Controller(view, model);
		view.setController(controller);
		controller.control();*/
		new Map();
		
	}
}