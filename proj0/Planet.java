/**
 *  Decleration of the Planet class
 */
public class Planet {

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

	public double calcDistance(Planet p) {
		double xxDiff = this.xxPos - p.xxPos;
		double yyDiff = this.yyPos - p.yyPos;
		return Math.sqrt(xxDiff * xxDiff + yyDiff * yyDiff);
	}
}
