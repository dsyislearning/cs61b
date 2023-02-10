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
		/** Sets up the universe so it goes from 
		  * -radius, -radius up to radius, radius */
		StdDraw.setScale(-radius, radius);

		StdDraw.picture(0, 0, img);
	}

	public static void drawAllPlanets(Planet[] pArr) {
		for (Planet p : pArr) {
			p.draw();
		}
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] pArr = readPlanets(filename);

		String background = "./images/starfield.jpg";
		drawBackground(radius, background);

		drawAllPlanets(pArr);
	}
}
