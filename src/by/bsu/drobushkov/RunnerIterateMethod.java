package by.bsu.drobushkov;

/**
 * 
 * @author Vasya Drobushkov <vasya.drobushkov@gmail.com>
 * 
 *         Solve system of equations represented by matrix and vector with
 *         iteration method.
 */
public class RunnerIterateMethod {

	private static final double[][] A = { { 1, 2, 3, 4 }, { 3, 4, 5, 6 },
			{ 3, 2, 1, 3 }, { 5, 4, 3, 2 } };
	private static final double[] f = { 1, 2, 1, 2 };

	public static void main(String[] args) {

		Utils.print(iterate(A, f));
	}

	/**
	 * Solve system of equations with iteration method
	 * 
	 * @param A
	 *            matrix
	 * @param f
	 *            vector
	 * @return solution
	 */
	private static double[] iterate(double[][] A, double[] f) {

		int steps = 0;

		double e = 0.0001;

		double[] b = f;

		Utils.print("b");
		Utils.print(b);
		Utils.print();
		Utils.print("A");
		Utils.print(A);
		Utils.print();
		Utils.print("norm A");
		Utils.print(MathUtils.normI(A));
		Utils.print();

		double[] xs = f;
		double[] Axs = MathUtils.mul(A, xs);
		double[] xn = MathUtils.sum(Axs, b);
		double q = MathUtils.normI(MathUtils.sub(xs, xn));

		Utils.print("begin");
		Utils.print("xs");
		Utils.print(xs);
		Utils.print("Axs");
		Utils.print(Axs);
		Utils.print("xn");
		Utils.print(xn);
		Utils.print("q");
		Utils.print(q);

		while (q >= e) {
			steps++;
			xs = xn;
			Axs = MathUtils.mul(A, xs);
			xn = MathUtils.sum(Axs, b);
			q = MathUtils.normI(MathUtils.sub(xs, xn));

			Utils.print();
			Utils.print("step " + steps);
			Utils.print("xs");
			Utils.print(xs);
			Utils.print("Axs");
			Utils.print(Axs);
			Utils.print("xn");
			Utils.print(xn);
			Utils.print("q");
			Utils.print(q);
			Utils.print();
		}

		Utils.print("steps: " + steps);

		return xn;
	}

}
