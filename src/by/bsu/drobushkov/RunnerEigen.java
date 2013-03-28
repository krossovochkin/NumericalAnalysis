package by.bsu.drobushkov;

/**
 * 
 * @author Vasya Drobushkov <vasya.drobushkov@gmail.com>
 * 
 *         Find 1 eigen value of matrix and corresponded to it eigen vector with
 *         Danilevsky method.
 * 
 */
public class RunnerEigen {

	private static final double[][] A = { { 1, 2, 3, 4 }, { 3, 4, 5, 6 },
			{ 3, 2, 1, 3 }, { 5, 4, 3, 2 } };

	public static void main(String[] args) {

		eigenValues(A);
	}

	/**
	 * Print eigen value and corresponded to it eigen vector.
	 * 
	 * @param A
	 *            matrix
	 */
	private static void eigenValues(double[][] A) {
		double e = 0.001;
		int n = A.length;

		double[][] S = MathUtils.identityMatrix(n);
		double[][] M = MathUtils.identityMatrix(n);
		double[][] MI = MathUtils.identityMatrix(n);

		for (int i = n - 1; i > 0; i--) {
			for (int j = 0; j < n; j++) {
				if (A[i][i - 1] != 0) {
					M[i - 1][j] = -A[i][j] / A[i][i - 1];
					MI[i - 1][j] = A[i][j];
				} else {
					System.out.println("Ошибка! Деление на нуль!");
					return;
				}
			}
			M[i - 1][i - 1] = 1 / A[i][i - 1];
			A = MathUtils.chop(MathUtils.mul(MathUtils.mul(MI, A), M), e);
			S = MathUtils.mul(S, M);

			Utils.print("M");
			Utils.print(M);
			Utils.print();
			Utils.print("MI");
			Utils.print(MI);
			Utils.print();
			Utils.print("A");
			Utils.print(A);
			Utils.print();

			M = MathUtils.identityMatrix(n);
			MI = MathUtils.identityMatrix(n);

		}

		double eigenValue = solveEigenEquation(A[0], 3, 10, e);
		Utils.print("lambda");
		Utils.print(eigenValue);
		Utils.print();

		double[] eigenVector = new double[n];
		for (int i = 0; i < n; i++) {
			eigenVector[i] = Math.pow(eigenValue, n - i - 1);
		}
		Utils.print("y");
		Utils.print(eigenVector);
		Utils.print();

		eigenVector = MathUtils.mul(S, eigenVector);
		Utils.print("x");
		Utils.print(eigenVector);
		Utils.print();

		double norm = 0;
		for (int i = 0; i < eigenVector.length; i++) {
			norm += eigenVector[i] * eigenVector[i];
		}
		eigenVector = MathUtils.div(eigenVector, Math.sqrt(norm));
		Utils.print("normalized x");
		Utils.print(eigenVector);

	}

	/**
	 * Character polynom
	 * 
	 * @param p
	 *            vector of coefficients
	 * @param lambda
	 *            eigen value
	 * @return value of character polynom in the lambda
	 */
	private static double eigenFunction(double[] p, double lambda) {
		double result = Math.pow(lambda, p.length);
		for (int i = 0; i < p.length; i++) {
			result -= p[i] * Math.pow(lambda, p.length - i - 1);
		}
		return result;
	}

	/**
	 * Solve character equation
	 * 
	 * @param p
	 *            vector of coefficients
	 * @param a
	 *            left side of
	 * @param b
	 *            right side of
	 * @param e
	 *            precision
	 * @return solution of character equation
	 */
	private static double solveEigenEquation(double[] p, double a, double b,
			double e) {
		Utils.print("start finding solution");
		while (b - a >= e) {
			double result = (a + b) / 2;
			Utils.print("a = " + a + "; b= " + b + "; c = " + result
					+ "; (b - a) = " + (b - a));
			if (Math.signum(eigenFunction(p, a)) != Math.signum(eigenFunction(
					p, result))) {
				b = result;
			} else {
				a = result;
			}
		}
		Utils.print("a = " + a + "; b= " + b + "; c = " + (a + b) / 2
				+ "; (b - a) = " + (b - a));
		Utils.print();
		return (a + b) / 2;
	}
}
