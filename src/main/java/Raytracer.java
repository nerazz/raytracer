import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Niklas on 25.06.2017.
 */
public class Raytracer {
	final static int WIDTH = 720;
	final static int HEIGHT = 405;
	static List<Primitive> Primitives = new ArrayList<>();
	static int checks = 0;

	public static void main(String[] args) {
		long start = System.nanoTime();

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(WIDTH + 50, HEIGHT + 80);
		frame.setTitle("Raytracer?");
		frame.getContentPane().setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);//ohne alpha?
		panel.add(new JLabel(new ImageIcon(image)));
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		Primitives.add(new Sphere(8.0, new Vector(0.0, -9.0, 0.0), Color.BLUE.getRGB()));
		Primitives.add(new Sphere(3.0, new Vector(2.0, -4.0, -2.0), Color.RED.getRGB()));
		Primitives.add(new Sphere(6.0, new Vector(8.0, -21.0, 3.0), Color.ORANGE.getRGB()));
		Primitives.add(new Sphere(8.0, new Vector(-8.0, -13.0, -8.0), Color.GREEN.getRGB()));
		Primitives.add(new Sphere(14.0, new Vector(17.0, -15.0, -15.0), Color.YELLOW.getRGB()));
		Primitives.add(new Sphere(1.5, new Vector(-3.0, -9.0, -25.0), Color.YELLOW.getRGB()));
		Primitives.add(new Plane(new Vector(1.0, 0.0, 0.0), new Vector(0.0, 0.0, 1.0), new Vector(0.0, 0.0, 0.0)));




		double maxdist = Double.POSITIVE_INFINITY;
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				//Ray ray = shoot(x, y);
				image.setRGB(x, y, render(x, y));
				//frame.getContentPane().validate();
			}
			frame.getContentPane().repaint();
		}

		//panel.setBackground(Color.RED);


		System.out.println("elapsed time: " + TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS) + "ms with " + checks + " checks");
	}

	static int render(int x, int y) {
		Vector eye = new Vector(0.0, -10.0, -32.0);//TODO: verzerrt?
		final double z = -16.0;
		Vector dir = new Vector((double)x / (double)WIDTH * 32.0 - 16.0, (double)y / (double)HEIGHT * 18.0 - 9.0 - 10.0, z);//W(x) = {-16, 16; W(y) = {-9, 9}
		dir = dir.sub(eye);
		Ray ray = new Ray(eye, dir);
		return calcColor(ray);
	}

	static int calcColor(Ray ray) {
		double maxDist = Double.MAX_VALUE;
		Primitive nearest = null;
		for (Primitive p : Primitives) {
			double intersection = p.intersection(ray);
			checks++;
			if (intersection != 0) {//existing intersection
				//double dist = ray.getOrigin().sub(intersection).length();

				if (intersection < maxDist) {
					//System.out.println(intersection);
					if (nearest != null) {
						System.out.println(p + "(" + intersection + ") in front of " + nearest + "(" + maxDist + ")");
					}
					maxDist = intersection;
					nearest = p;
				}
			}
		}

		if (nearest != null) {
			//Color c = new Color(nearest.getColor());
			//return c.getRGB();
			//double distMod = maxDist / ray.getOrigin().getZ()
			//c = new Color(Math.max(c.getRed() - (int)(maxDist * 3), 0), Math.max(c.getGreen() - (int)(maxDist * 3), 0), Math.max(c.getBlue() - (int)(maxDist * 3), 0), Math.min((int)(maxDist * 7.0 + 50), 255));//TODO: fixen
			int frac = (int)Math.min(maxDist / 100.0 * 255, 255);
			Color c = new Color(frac, frac, frac);
			return c.getRGB();
		} else {//kein obj wird getroffen
			return Color.ORANGE.getRGB();
		}
	}
}
