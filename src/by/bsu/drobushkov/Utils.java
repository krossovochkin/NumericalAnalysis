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
 *         More useful for writing functions for printing result (matrices,
 *         vectors, etc.)
 */
public class Utils {

	/**
	 * Print blank line
	 */
	public static void print() {
		System.out.println();
	}

	/**
	 * Print text
	 * 
	 * @param text
	 */
	public static void print(String text) {
		System.out.println(text);
	}

	/**
	 * Print vector of double
	 * 
	 * @param array
	 */
	public static void print(double[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		print();
	}

	/**
	 * Print vector of int
	 * 
	 * @param array
	 */
	public static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		print();
	}

	/**
	 * Print system of equations representing by matrix and vector
	 * 
	 * @param array
	 *            matrix of system of equations
	 * @param vector
	 *            vector of system of equations
	 */
	public static void print(double[][] array, double[] vector) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j] + ", ");
			}
			System.out.print(" = ");
			System.out.println(vector[i]);
		}
		print();
	}

	/**
	 * Print matrix
	 * 
	 * @param array
	 *            matrix
	 */
	public static void print(double[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + ", ");
			}
			System.out.println();
		}
	}

	/**
	 * Print double number
	 * 
	 * @param n
	 *            number
	 */
	public static void print(double n) {
		System.out.println(n);
	}

}
