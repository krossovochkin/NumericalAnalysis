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
 *         Solving system of equations represented by matrix and vector with the
 *         method of Gauss.
 */
public class RunnerGaussMethod {

	private enum NumberOfSolutions {
		ONE, NONE, INFINITE
	}

	private static NumberOfSolutions numberOfSolutions = NumberOfSolutions.ONE;

	private static final double[][] A = { { 1, 2, 3, 4 }, { 3, 4, 5, 6 },
			{ 3, 2, 1, 3 }, { 5, 4, 3, 2 } };
	private static final double[] f = { 1, 2, 1, 2 };

	public static void main(String[] args) {
		Utils.print(gauss(A, f));
	}

	/**
	 * Solve system of equations with Gauss method
	 * 
	 * @param A
	 *            matrix
	 * @param f
	 *            vector
	 * @return solution vector if solution is unique, null otherwise
	 */
	private static double[] gauss(double[][] A, double[] f) {
		double[] solution = new double[f.length];

		// order of variables in the column x
		int[] mask = new int[f.length];
		for (int i = 0; i < mask.length; i++) {
			mask[i] = i;
		}

		// main loop
		for (int step = f.length; step > 0; step--) {

			// find max element and its position
			int maximumi = 0;
			int maximumj = 0;
			double maxelement = A[maximumi][maximumj];

			for (int i = 0; i < step; i++) {
				for (int j = 0; j < step; j++) {
					if (Math.abs(maxelement) < Math.abs(A[i][j])) {
						maxelement = A[i][j];
						maximumi = i;
						maximumj = j;
					}
				}
			}

			// check whether unique solution exists
			if (maxelement == 0) {
				Utils.print(f);
				numberOfSolutions = NumberOfSolutions.INFINITE;
				for (int i = 0; i < step; i++) {
					if (f[i] != 0) {
						numberOfSolutions = NumberOfSolutions.NONE;
						break;
					}
				}
			} else {

				// divide row with maxelement by its value
				A[maximumi] = MathUtils.div(A[maximumi], maxelement);
				f[maximumi] = f[maximumi] / maxelement;

				Utils.print("divide:");
				Utils.print(A, f);
				Utils.print();

				// creating zeros above and below the 1
				for (int i = 0; i < step; i++) {
					if (i != maximumi) {
						f[i] -= f[maximumi] * A[i][maximumj];
						A[i] = MathUtils.sub(A[i],
								MathUtils.mul(A[maximumi], A[i][maximumj]));
					}
				}

				// swap row with 1 to the bottom
				A = MathUtils.swapRows(A, step - 1, maximumi);
				f = MathUtils.swap(f, step - 1, maximumi);

				// swap column with 1 to the right
				A = MathUtils.swapColumns(A, step - 1, maximumj);
				mask = MathUtils.swap(mask, step - 1, maximumj);
			}
		}

		switch (numberOfSolutions) {
		case ONE:
			Utils.print("triangle matrix: ");
			Utils.print(A, f);
			Utils.print(mask);
			Utils.print();

			// get solution from triangle matrix
			for (int i = 0; i < solution.length; i++) {
				solution[i] = solve(A[i], solution, f[i], i);
			}

			// sort solution with respect to the mask
			solution = sort(solution, mask);
			return solution;
		case NONE:
			Utils.print("No solution");
			return null;
		case INFINITE:
			Utils.print("Infinite number of solutions");
			return null;
		default:
			return null;
		}

	}

	/**
	 * Solve linear equation (a * var = f), find var[j]
	 * 
	 * @param a
	 *            vector of coefficients
	 * @param var
	 *            vector of known values of variables
	 * @param f
	 *            free term
	 * @param j
	 *            position of variable we want to know
	 * @return var[j] - solution
	 */
	private static double solve(double[] a, double[] var, double f, int j) {
		double result = f;
		for (int i = 0; i < a.length; i++) {
			if (i != j) {
				result -= var[i] * a[i];
			}
		}
		return result;
	}

	/**
	 * Sort vector a with respect to the mask
	 * 
	 * @param a
	 *            vector
	 * @param mask
	 * @return sorted vector a
	 */
	private static double[] sort(double[] a, int[] mask) {
		for (int i = 0; i < mask.length; i++) {
			for (int j = i; j < mask.length; j++) {
				if (mask[i] > mask[j]) {
					int temp = mask[i];
					mask[i] = mask[j];
					mask[j] = temp;

					double tempA = a[i];
					a[i] = a[j];
					a[j] = tempA;
				}
			}
		}
		return a;
	}
}
