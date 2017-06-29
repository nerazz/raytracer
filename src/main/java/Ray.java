/**
 * Created by Niklas on 25.06.2017.
 */
public class Ray {
	private final Vector origin;
	private Vector direction;

	Ray(Vector origin, Vector direction) {
		this.origin = origin;
		this.direction = direction;
	}

	Vector getOrigin() {
		return origin;
	}

	Vector getDirection() {
		return direction;
	}

	@Override
	public String toString() {
		return "origin: " + origin.toString() + "\n" + "direction: " + direction.toString();
	}
}
