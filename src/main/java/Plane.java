/**
 * Created by Niklas on 28.06.2017.
 */
public class Plane extends Primitive {
	private Vector n;//normal vector
	private Vector q;//point

	Plane(Vector a, Vector b, Vector q) {
		n = a.cross(b);
		this.q = q;
	}

	double intersection(Ray ray) {
		double d = n.dot_prod(q.sub(ray.getOrigin())) / n.dot_prod(ray.getDirection());
		if (d >= 0) {//intersection
			if(n.dot_prod(ray.getDirection()) == 0) {//parallel
				return 0;
			}
			return ray.getDirection().scale(d).length();
			//return ray.getOrigin().sub(ray.getDirection().normalize().scale(d).add(ray.getOrigin())).length();
		}
		return 0;
	}

	int getColor() {
		return 1220022033;//TODO: schachbrett returnen
	}
}
