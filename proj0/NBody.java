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
}