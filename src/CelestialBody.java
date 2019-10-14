

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */

	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){

		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}

	public double getX() {
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {

		return myXVel;
	}

	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		// TODO: complete method
		double dx = (myXPos - b.myXPos) * (myXPos - b.myXPos);
		double dy = (myYPos - b.myYPos) * (myYPos - b.myYPos);
		double r2 = dx + dy;
		double distance = Math.sqrt(r2);
		return distance;
	}

	public double calcForceExertedBy(CelestialBody b) {
		//
		double gConstant = (6.67 * 1e-11);
		double fullMass = (myMass * b.myMass);
		double distance = calcDistance(b);
		double force = gConstant * (fullMass / (Math.pow(distance, 2)));
		return force;
	}

	public double calcForceExertedByX(CelestialBody b) {
		// TODO: complete method
		//Fx = (calcForceExertedBy * distance between x) / distance
		double distance = calcDistance(b);
		double dx = b.myXPos - myXPos;
		double force = calcForceExertedBy(b);
		double exertX = force * (dx/distance);
		return exertX;
	}
	public double calcForceExertedByY(CelestialBody b) {
		// TODO: complete method
		// Fx = (calcForceExertedBy * distance between y) / distance
		double distance = calcDistance(b);
		double dy = b.myYPos - myYPos;
		double force = calcForceExertedBy(b);
		double exertY = force * (dy/distance);
		return exertY;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method
		double totalx = 0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				totalx += calcForceExertedByX(b);
			}
		}
		return totalx;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		// TODO: complete method
		double totaly = 0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				totaly += calcForceExertedByY(b);
			}
		}
		return totaly;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
		double xaccel = xforce/myMass;
		double yaccel = yforce/myMass;
		double nvx = myXVel + (deltaT*xaccel);
		double nvy = myYVel + (deltaT*yaccel);
		double nx = myXPos + (deltaT*nvx);
		double ny = myYPos + (deltaT*nvy);
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}

	public void draw() {
		// TODO: complete method
	}
}