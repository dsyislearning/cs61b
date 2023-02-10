public class TestPlanet {
	public static void main(String[] args) {
		String img = "planet.gif";
		Planet a = new Planet(1, 2, 3, 4, 5, img);
		Planet b = new Planet(5, 4, 3, 2, 1, img);
		System.out.println("Planet a");
		System.out.format(" x-force of a exerted by b: %f\n", a.calcForceExertedByX(b));
		System.out.format(" y-force of a exerted by b: %f\n", a.calcForceExertedByY(b));
		System.out.println("Planet b");
		System.out.format(" x-force of b exerted by a: %f\n", b.calcForceExertedByX(a));
		System.out.format(" y-force of b exerted by a: %f\n", b.calcForceExertedByY(a));
	}
}