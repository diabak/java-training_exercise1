package matrix_zadanka_KIMI;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class matrix_main {

	public static void main(String[] args) {
		System.out.println("wejscie: ");
		Scanner console_start = new Scanner(System.in).useDelimiter(" *");
		int n = console_start.nextInt();
		int k = console_start.nextInt();
		int [][] m_matrix = new int [n][n];
		int [][] k_matrix = new int [k][4];
		for (int i=0; i < n; i++) {
			Scanner console_m_matrix = new Scanner(System.in).useDelimiter(" *");
			for (int j = 0; j<n; j++) {
				m_matrix[i][j] = console_m_matrix.nextInt();
			}
		}
		for (int i=0; i < k; i++) {
			Scanner console_k_matrix = new Scanner(System.in).useDelimiter(" *");
			for (int j = 0; j<4; j++) {
				k_matrix[i][j] = console_k_matrix.nextInt();
			}
		}
		System.out.println("dupa1:");
		int[][] sums_matrix = summMatrix(n, m_matrix, k, k_matrix);
		System.out.println("dupa2:");
//		ArrayList<Integer> list_m_matrix = list_matrix(n, m_matrix, k, k_matrix);
		int first_result = 0; // ilosc elementow sums_matrix bez duplikatow
		int second_result = 0; // ilosc powtarzajacych sie najwiecej razy elementow sums_matrix
//		int third_list_result = 0;
//		for(Integer d : list_m_matrix)
//			third_list_result += d;
//		third_list_result = third_list_result/k;
//		int third_result = IntStream.of(sums_matrix).sum()/k;
		System.out.println("wyjscie:");
//		System.out.println(first_result + " " + second_result + " " + third_result);
	}
	
	public static int[][] summMatrix (int i_n, int[][] i_m_matrix, int i_k, int[][] i_k_matrix) {
		int[][] sum_m_matrix = new int [i_k][2];
		for (int i=0; i<i_k; i++) {
			int i_helper = 0;
			for (int row = i_k_matrix[i][0]; row <= i_k_matrix[i][2]; row++ ) {
				for (int column = i_k_matrix[i][1]; column <= i_k_matrix[i][3]; column++) {
					i_helper = i_helper + i_m_matrix[row][column];
				}
			}
			sum_m_matrix[i][0] = i_helper;
			sum_m_matrix[i][1] = 0;
		}
		for (int i = 0; i < i_k; i++) {
			for (int j = i_k-1; i < j; j--) {
				if (sum_m_matrix[i][0] == sum_m_matrix[j][0]) 
					sum_m_matrix[i][1] = sum_m_matrix[i][1] + 1;
			}
			System.out.println("test: " + sum_m_matrix[i][0] + " test2: " + sum_m_matrix[i][1] );
		}
		return sum_m_matrix;
	}
}
