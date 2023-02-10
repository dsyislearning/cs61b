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
}