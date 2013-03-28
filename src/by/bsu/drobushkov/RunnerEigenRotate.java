package by.bsu.drobushkov;

/**
 * 
 * @author Vasya Drobushkov <vasya.drobushkov@gmail.com>
 * 
 *         Find all eigen values of matrix with a method of rotation.
 */
public class RunnerEigenRotate {

	private static final double[][] A = { { 1, 2, 3, 4 }, { 3, 4, 5, 6 },
			{ 3, 2, 1, 3 }, { 5, 4, 3, 2 } };

	public static void main(String[] args) {

		eigenValuesRotateMethod(A);
	}

	/**
	 * Print all eigen values of matrix A with a method of rotation.
	 * 
	 * @param A
	 *            matrix
	 */
	private static void eigenValuesRotateMethod(double[][] A) {

		double e = 0.01;

		while (nondiagonalSquared(A) >= e) {
			double max = 0;
			int maxi = 0;
			int maxj = 0;

			for (int i = 0; i < A.length; i++) {
				for (int j = 0; j < A[i].length; j++) {
					if (i != j && Math.abs(A[i][j]) > max) {
						max = Math.abs(A[i][j]);
						maxi = i;
						maxj = j;
					}
				}
			}

			double m = 2 * max / (A[maxi][maxi] - A[maxj][maxj]);

			double[][] T = MathUtils.identityMatrix(A.length);
			T[maxi][maxi] = cosf(m);
			T[maxj][maxj] = cosf(m);
			T[maxi][maxj] = -sinf(m);
			T[maxj][maxi] = sinf(m);

			A = MathUtils.mul(MathUtils.mul(MathUtils.transpose(T), A), T);
		}

		Utils.print("Result À");
		Utils.print(A);
		Utils.print();

		Utils.print("Eigen values of matrix À");
		for (int i = 0; i < A.length; i++) {
			Utils.print(A[i][i]);
		}
	}

	/**
	 * Count sum of squared nondiagonal elements of matrix
	 * 
	 * @param A
	 *            matrix
	 * @return sum of squared nondiagonal elements of matrix A
	 */
	private static double nondiagonalSquared(double[][] A) {
		double result = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if (i != j) {
					result += Math.pow(A[i][j], 2);
				}
			}
		}
		return result;
	}

	/**
	 * Count cosf for rotation matrix T
	 * 
	 * @param m
	 * @return cosf
	 */
	private static double cosf(double m) {
		return Math.sqrt((1 + 1 / Math.sqrt(1 + Math.pow(m, 2))) / 2);
	}

	/**
	 * Count sinf for rotation matrix T
	 * 
	 * @param m
	 * @return sinf
	 */
	private static double sinf(double m) {
		return Math.signum(m)
				* Math.sqrt((1 - 1 / Math.sqrt(1 + Math.pow(m, 2))) / 2);
	}
}
