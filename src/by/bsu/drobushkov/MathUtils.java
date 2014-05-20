/*
* Copyright 2014 Vasya Drobushkov
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package by.bsu.drobushkov;

/**
 * 
 * @author Vasya Drobushkov <vasya.drobushkov@gmail.com>
 * 
 *         Basic math operations on matrix and vectors such as: addition,
 *         subtraction, multiplying, dividing. Matrix and vectors operations
 *         such as: first and second norms, transposing, identity matrix.
 *         WARNING: All matrices should be squared matrices!
 */
public class MathUtils {

	/**
	 * sum of two vectors
	 * 
	 * @param a
	 *            vector
	 * @param b
	 *            vector
	 * @return new vector = a + b
	 */
	public static double[] sum(double[] a, double[] b) {
		double[] result = new double[a.length];

		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] + b[i];
		}

		return result;
	}

	/**
	 * sum of two matrices
	 * 
	 * @param a
	 *            matrix
	 * @param b
	 *            matrix
	 * @return new matrix = a + b
	 */
	public static double[][] sum(double[][] a, double[][] b) {
		double[][] result = new double[a.length][a[0].length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				result[i][j] = a[i][j] + b[i][j];
			}
		}

		return result;
	}

	/**
	 * difference of two vectors
	 * 
	 * @param a
	 *            vector
	 * @param b
	 *            vector
	 * @return new vector = a - b
	 */
	public static double[] sub(double[] a, double[] b) {
		double[] result = new double[a.length];

		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] - b[i];
		}

		return result;
	}

	/**
	 * difference of two matrices
	 * 
	 * @param a
	 *            matrix
	 * @param b
	 *            matrix
	 * @return new matrix = a - b
	 */
	public static double[][] sub(double[][] a, double[][] b) {
		double[][] result = new double[a.length][a[0].length];

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = a[i][j] - b[i][j];
			}
		}

		return result;
	}

	/**
	 * product of matrix and number
	 * 
	 * @param a
	 *            matrix
	 * @param b
	 *            number
	 * @return new matrix = a * b
	 */
	public static double[][] mul(double[][] a, double b) {
		double[][] result = new double[a.length][a[0].length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				result[i][j] = a[i][j] * b;
			}
		}

		return result;
	}

	/**
	 * product of vector and number
	 * 
	 * @param a
	 *            vector
	 * @param b
	 *            number
	 * @return new vector = a * b
	 */
	public static double[] mul(double[] a, double b) {
		double[] result = new double[a.length];

		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] * b;
		}

		return result;
	}

	/**
	 * product of matrix and vector
	 * 
	 * @param a
	 *            matrix
	 * @param b
	 *            vector
	 * @return new vector = a * b
	 */
	public static double[] mul(double[][] a, double[] b) {
		double[] result = new double[b.length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				result[i] += a[i][j] * b[j];
			}
		}

		return result;
	}

	/**
	 * product of two matrices
	 * 
	 * @param a
	 *            matrix
	 * @param b
	 *            matrix
	 * @return new matrix = a * b
	 */
	public static double[][] mul(double[][] a, double[][] b) {
		double n = a.length;
		double[][] result = new double[b.length][b.length];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}

		return result;
	}

	/**
	 * quotient of vector and number
	 * 
	 * @param a
	 *            vector
	 * @param b
	 *            number
	 * @return new vector = a / b
	 */
	public static double[] div(double[] a, double b) {
		double[] result = new double[a.length];

		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] / b;
		}

		return result;
	}

	/**
	 * First norm of vector: abs(max(vector))
	 * 
	 * @param b
	 *            vector
	 * @return first norm of vector
	 */
	public static double normI(double[] b) {
		double result = 0;

		for (int i = 0; i < b.length; i++) {
			if (Math.abs(b[i]) > result) {
				result = Math.abs(b[i]);
			}
		}

		return result;
	}

	/**
	 * Second norm of vector: sum(abs(vector))
	 * 
	 * @param b
	 *            vector
	 * @return second norm of vector
	 */
	public static double normII(double[] b) {
		double result = 0;

		for (int i = 0; i < b.length; i++) {
			result += Math.abs(b[i]);
		}

		return result;
	}

	/**
	 * First norm of matrix: max of sum of abs elements in a column
	 * 
	 * @param a
	 *            matrix
	 * @return first norm of matrix
	 */
	public static double normI(double[][] a) {
		double result = 0;

		for (int j = 0; j < a.length; j++) {
			double t = 0;
			for (int i = 0; i < a[j].length; i++) {
				t += Math.abs(a[i][j]);
			}
			if (t > result) {
				result = t;
			}
		}

		return result;
	}

	/**
	 * Second norm of matrix: max of sum of abs elements in a row
	 * 
	 * @param a
	 *            matrix
	 * @return second norm of matrix
	 */
	public static double normII(double[][] a) {
		double result = 0;

		for (int i = 0; i < a.length; i++) {
			double t = 0;
			for (int j = 0; j < a[i].length; j++) {
				t += Math.abs(a[i][j]);
			}
			if (t > result) {
				result = t;
			}
		}

		return result;
	}

	/**
	 * Transpose matrix
	 * 
	 * @param a
	 *            matrix
	 * @return new matrix - transposed a
	 */
	public static double[][] transpose(double[][] a) {
		double[][] result = new double[a.length][a[0].length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				result[i][j] = a[j][i];
			}
		}

		return result;
	}

	/**
	 * Gives identity matrix of power n
	 * 
	 * @param n
	 *            power of matrix
	 * @return identity matrix
	 */
	public static double[][] identityMatrix(int n) {
		double[][] result = new double[n][n];

		for (int i = 0; i < n; i++) {
			result[i][i] = 1;
		}

		return result;
	}

	/**
	 * Sets all elements of matrix which is less than precision to zero
	 * 
	 * @param a
	 *            matrix
	 * @param precision
	 * @return modified matrix a
	 */
	public static double[][] chop(double[][] a, double precision) {
		int n = a.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (Math.abs(a[i][j]) < precision) {
					a[i][j] = 0;
				}
			}
		}

		return a;
	}

	/**
	 * Swap two elements in the double vector
	 * 
	 * @param a
	 *            vector
	 * @param i
	 *            position of first element to swap
	 * @param j
	 *            position of second element to swap
	 * @return modified vector a
	 */
	public static double[] swap(double[] a, int i, int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;

		return a;
	}

	/**
	 * Swap two elements in the int vector
	 * 
	 * @param a
	 *            vector
	 * @param i
	 *            position of first element to swap
	 * @param j
	 *            position of second element to swap
	 * @return modified vector a
	 */
	public static int[] swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;

		return a;
	}

	/**
	 * Swap two rows in the matrix
	 * 
	 * @param a
	 *            matrix
	 * @param i
	 *            position of first row to swap
	 * @param j
	 *            position of second row to swap
	 * @return modified matrix a
	 */
	public static double[][] swapRows(double[][] a, int i, int j) {
		double[] temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}

	/**
	 * Swap two columns in the matrix
	 * 
	 * @param a
	 *            matrix
	 * @param i
	 *            position of first column to swap
	 * @param j
	 *            position of second column to swap
	 * @return modified matrix a
	 */
	public static double[][] swapColumns(double[][] a, int i, int j) {
		for (int k = 0; k < a.length; k++) {
			double temp = a[k][i];
			a[k][i] = a[k][j];
			a[k][j] = temp;
		}
		return a;
	}
}
