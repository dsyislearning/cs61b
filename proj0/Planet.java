/**
 *  Decleration of the Planet class
 */
public class Planet {
	static final double G = 6.67e-11d; // the gravitational constant

	public double xxPos; // Its current x postion
	public double yyPos; // Its current y postion
	public double xxVel; // Its current velocity in the x direction
	public double yyVel; // Its current velocity in the y direction
	public double mass;  // Its mass
	public String imgFileName; /* The name of the file that corresponds 
	to the image that depicts the body (for example, jupiter.gif) */

	/**
	 *  Constructor 1: Initialize from properties
	 *
	 *  @param   xP   x postion
	 *  @param   yP   y postion
	 *  @param   xV   velocity in the x direction
	 *  @param   yV   velocity in the y direction
	 *  @param   m    mass
	 *  @param   img  The name of the image
	 *  
	 *  @return       [description]
	 */
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/**
	 *  Constructor 2: Initialize an identical Planet object
	 *
	 *  @param   p  an identical Planet object
	 *
	 *  @return     [description]
	 */
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/**
	 *  Calculates the distance between two Planets
	 *
	 *  @param   p  the other planet
	 *
	 *  @return     the distance between p and this
	 */
	public double calcDistance(Planet p) {
		double xxDiff = this.xxPos - p.xxPos;
		double yyDiff = this.yyPos - p.yyPos;
		return Math.sqrt(xxDiff * xxDiff + yyDiff * yyDiff);
	}

	/**
	 *  Calculates the force exerted on this planet by the given planet
	 *
	 *  @param   p  the given planet
	 *
	 *  @return     the force exerted on this planet
	 */
	public double calcForceExertedBy(Planet p) {
		double m1 = this.mass, m2 = p.mass;
		double r = this.calcDistance(p);
		return (G * m1 * m2) / (r * r);
	}

	/**
	 *  Calculates the force exerted in the X direction
	 *
	 *  @param   p  the given planet
	 *
	 *  @return     the force exerted in the X direction
	 */
	public double calcForceExertedByX(Planet p) {
		double netForce = this.calcForceExertedBy(p);
		double dx = p.xxPos - this.xxPos;
		double r = this.calcDistance(p);
		return netForce * dx / r;
	}

	/**
	 *  Calculates the force exerted in the Y direction
	 *
	 *  @param   p  the given planet
	 *
	 *  @return     the force exerted in the Y direction
	 */
	public double calcForceExertedByY(Planet p) {
		double netForce = this.calcForceExertedBy(p);
		double dy = p.yyPos - this.yyPos;
		double r = this.calcDistance(p);
		return netForce * dy / r;
	}
}
