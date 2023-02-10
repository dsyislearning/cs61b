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

	/**
	 *  Calculates the net X force exerted by all planets in the given Planet array
	 *
	 *  @param   pArr  the given Planet array
	 *
	 *  @return        the net X force
	 */
	public double calcNetForceExertedByX(Planet[] pArr) {
		double netForceX = 0;
		for (Planet p : pArr) {
			if (!this.equals(p)) {
				netForceX += this.calcForceExertedByX(p);
			}
		}
		return netForceX;
	}

	/**
	 *  Calculates the net Y force exerted by all planets in the given Planet array
	 *
	 *  @param   pArr  the given Planet array
	 *
	 *  @return        the net Y force
	 */
	public double calcNetForceExertedByY(Planet[] pArr) {
		double netForceY = 0;
		for (Planet p : pArr) {
			if (!this.equals(p)) {
				netForceY += this.calcForceExertedByY(p);
			}
		}
		return netForceY;
	}

	/**
	 *  Compares two planets
	 *
	 *  @param   p  the given planet
	 *
	 *  @return     the comparison result
	 */
	public Boolean equals(Planet p) {
		return this == p;
	}

	/**
	 *  1. Calculate the acceleration using the provided x- and y-forces.
	 *  2. Calculate the new velocity by using the acceleration and current velocity. 
	 *     Recall that acceleration describes the change in velocity per unit time,
	 *     so the new velocity is (vx + dt * ax, vy + dt * ay).
	 *  3. Calculate the new position by using the velocity computed in step 2
	 *     and the current position. The new position is (px + dt * vx, py + dt * vy).
	 *  
	 *  Uses the steps above to update the planet’s position and velocity instance variables
	 *
	 *  @param  dt  a small period of time
	 *  @param  fX  x-force
	 *  @param  fY  y-force
	 */
	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}
}
