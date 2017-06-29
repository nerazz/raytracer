/**
 * Created by Niklas on 26.06.2017.
 */
public class Vector {
	private double x;
	private double y;
	private double z;

	Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	Vector add(Vector v) {
		double vx = x + v.getX();
		double vy = y + v.getY();
		double vz = z + v.getZ();
		return new Vector(vx, vy, vz);
	}

	Vector sub(Vector v) {
		double vx = x - v.getX();
		double vy = y - v.getY();
		double vz = z - v.getZ();
		return new Vector(vx, vy, vz);
	}

	double length() {
		return Math.sqrt(x * x + y * y + z * z);
	}//vielleicht mit math.abs() schneller? absx + absy + absz

	double dot_prod(Vector v) {
		return (x * v.getX() + y * v.getY() + z * v.getZ());
	}

	Vector cross(Vector v) {
		double vx = y * v.getZ() - z * v.getY();
		double vy = z * v.getX() - x * v.getZ();
		double vz = x * v.getY() - y * v.getX();
		return new Vector(vx, vy, vz);
	}

	Vector normalize() {
		double len = length();
		if(len != 0) {
			return new Vector(x / len, y / len, z / len);
		} else {//return null? oder ehemaligen vektor?
			return this;
			//return new Vector(0.0, 0.0, 0.0);
		}
	}

	Vector scale(double t) {
		if (t <= 0) {
			return this;
			//throw new IllegalArgumentException("t has to be greater 0!");
		}
		double vx = x * t;
		double vy = y * t;
		double vz = z * t;
		return new Vector(vx, vy, vz);
	}

	double getX() {
		return x;
	}

	double getY() {
		return y;
	}

	double getZ() {
		return z;
	}

	@Override
	public String toString() {
		return "(" + x + "; " + y + "; " + z + ")";
	}
}
