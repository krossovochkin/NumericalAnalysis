package by.bsu.drobushkov;

/**
 * 
 * @author Vasya Drobushkov <vasya.drobushkov@gmail.com>
 * 
 *         Count determinant of matrix and its inverse matrix
 * 
 */
public class RunnerDetAndInverse {

	private static final double[][] A = { { 1, 2, 3, 4 }, { 3, 4, 5, 6 },
			{ 3, 2, 1, 3 }, { 5, 4, 3, 2 } };

	public static void main(String[] args) {

		Utils.print(det(A));
	}

	/**
	 * Count determinant and inverse matrix (inverse matrix will be printed in
	 * this function)
	 * 
	 * @param A
	 * @return determinant
	 */
	private static double det(double[][] A) {
		double[][] E = MathUtils.identityMatrix(A.length);
		double result = 1;
		int n = A.length;

		// main loop
		for (int step = 0; step < n; step++) {

			result *= A[step][step];
			{
				double value = A[step][step];
				E[step] = MathUtils.div(E[step], value);
				A[step] = MathUtils.div(A[step], value);
			}
			{
				for (int i = step + 1; i < n; i++) {
					double value = A[i][step];
					E[i] = MathUtils.sub(E[i], MathUtils.mul(E[step], value));
					A[i] = MathUtils.sub(A[i], MathUtils.mul(A[step], value));
				}
			}

		}

		for (int step = n - 1; step >= 1; step--) {
			for (int i = step - 1; i >= 0; i--) {
				double value = A[i][step];
				E[i] = MathUtils.sub(E[i], MathUtils.mul(E[step], value));
				A[i] = MathUtils.sub(A[i], MathUtils.mul(A[step], value));
			}
		}

		Utils.print(E);
		Utils.print();
		return result;
	}
}
