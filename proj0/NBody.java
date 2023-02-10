public class NBody {
	/**
	 *  Reads the double corresponding to the radius of the uiverse in given file
	 *
	 *  @param   fileName  the given file
	 *
	 *  @return            the radius of the universe
	 */
	public static double readRadius(String fileName) {
		In in = new In(fileName);

		in.readInt();
		double radius = in.readDouble();
		in.close();

		return radius;
	}

	/**
	 *  Reads the planets in the given file and return them as an array
	 *
	 *  @param   fileName  the given file
	 *
	 *  @return            the array of planets
	 */
	public static Planet[] readPlanets(String fileName) {
		/* Start reading in fileName */
		In in = new In(fileName);

		/* Reads the number of planets */
		int n = in.readInt();
		/* Ignore the radius of the universe */
		in.readDouble();
		/* New an array of n planets */
		Planet[] pArr = new Planet[n];
		/* Keep looking until all planets has bean read */
		for (int i = 0; i < n; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			pArr[i] = new Planet(xP, yP, xV, yV, m, img);
		}

		return pArr;
	}

	public static void drawBackground(double radius, String img) {
		/**
		 *  Sets up the universe so it goes from 
		 *  -radius, -radius up to radius, radius
		 */
		StdDraw.setScale(-radius, radius);

		StdDraw.picture(0, 0, img);
	}

	public static void drawAllPlanets(Planet[] pArr) {
		for (Planet p : pArr) {
			p.draw();
		}
	}

	public static void main(String[] args) {
		/* Store the 0th and 1st command line arguments as doubles named T and dt */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);

		/* Store the 2nd command line argument as a String named filename */
		String filename = args[2];

		/**
		 *  Read in the planets and the universe radius from the file 
		 *  described by filename using your methods from earlier in this assignment
		 */
		double radius = readRadius(filename);
		Planet[] pArr = readPlanets(filename);

		/* Store the number of planets */
		int n = pArr.length;

		/* Draw the initial universe state */
		String background = "./images/starfield.jpg";
		drawBackground(radius, background);
		drawAllPlanets(pArr);

		/* Enable double buffering */
		StdDraw.enableDoubleBuffering();

		/**
		 *  Create a time variable and set it to 0.
		 *  Set up a loop to loop until this time variable is T.
		 *  Increase your time variable by dt.
		 */
		for (double time = 0; time < T; time += dt) {
			/* Create an xForces array and yForces array. */
			double[] xForces = new double[n];
			double[] yForces = new double[n];

			/**
			 *  Calculate the net x and y forces for each planet,
			 *  storing these in the xForces and yForces arrays respectively.
			 */
			for (int i = 0; i < n; i++) {
				xForces[i] = pArr[i].calcNetForceExertedByX(pArr);
				yForces[i] = pArr[i].calcNetForceExertedByY(pArr);
				/**
				 *  Call update on each of the planets.
				 *  This will update each planet’s position, velocity, and acceleration.
				 */
				pArr[i].update(dt, xForces[i], yForces[i]);
			}

			/* Draw the background image. */
			drawBackground(radius, background);

			/* Draw all of the planets. */
			drawAllPlanets(pArr);

			/* Show the offscreen buffer. */
			StdDraw.show();

			/* Pause the animation for 10 milliseconds */
			StdDraw.pause(10);
		}

		/**
		 *  Print out the final state of the universe in the same format as the input
		 */
		StdOut.printf("%d\n", pArr.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < pArr.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
				pArr[i].xxPos, pArr[i].yyPos, pArr[i].xxVel,
				pArr[i].yyVel, pArr[i].mass, pArr[i].imgFileName);   
		}
	}
}
