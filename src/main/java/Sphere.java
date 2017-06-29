import java.awt.*;

/**
 * Created by Niklas on 25.06.2017.
 */
public class Sphere extends Primitive {
	private final double radius;
	private final Vector origin;
	private final int color;

	Sphere(double radius, Vector origin, int color) {
		this.radius = radius;
		this.origin = origin;
		this.color = color;
	}

	double getRadius() {
		return radius;
	}

	Vector getOrigin() {
		return origin;
	}

	int getColor() {
		return color;
	}

	double intersection(Ray ray) {
		Vector dir = ray.getDirection().normalize();
		Vector om = origin.sub(ray.getOrigin());//strecke origin -> center
		double lom = om.length();//TODO: ohne wurzel für effizienz
		double ln = om.dot_prod(dir);
		Vector intersection;
		if (lom < radius) {//ray innerhalb kugel
			double h2 = radius * radius + ln * ln - lom * lom;
			intersection = ray.getOrigin().add(dir.scale(ln - Math.sqrt(h2)));
		} else {//ray außerhalb kugel
			if (ln < 0) {//ray entfernt sich von kugel
				return 0;
			}
			double h2 = radius * radius + ln * ln - lom * lom;
			if (h2 < 0) {//ray verfehlt
				return 0;
			} else {//ray trifft
				intersection = ray.getOrigin().add(dir.scale(ln + Math.sqrt(h2)));
			}
		}
		//System.out.println("intersection at: " + intersection);
		return ray.getOrigin().sub(intersection).length();
	}

}
