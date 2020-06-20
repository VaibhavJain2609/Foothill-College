package math;

public class discrete {
	public static void main(String[] args) {
	//	int[] arr = {1,2,3,4,5,6};
	//	int[] arr2 = new int[arr.length];
	//	System.out.print(toString(option1(arr)));
	//	System.out.println();
	//	System.out.print(toString(option2(arr)));
	//	System.out.println();
	//	System.out.print(toString(option3(arr)));
	System.out.println(probability(9999, 10));	
	}
	public static int[] option1(int[] arr) {
		int[] arr1 = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			int n = arr[i];
			int y = (int)Math.pow((-Math.floor((n+2)/(n+1))), (n+1) %3) * (2*(n-1));
			arr1[i] = y;
		}
		return arr1;
	}
	public static int[] option2(int[] arr) {
		int[] arr1 = new int[arr.length];
		for(int i = 0; i<arr.length; i++) {
			int n = arr[i];
			int y = (int)Math.pow(-1, (n-1) %3)*(2*n-2);
			arr1[i] = y;
		}
		return arr1;
	}
	public static int[] option3(int[] arr) {
		int[] arr1 = new int[arr.length];
		for(int i = 0; i<arr.length; i++) {
			int n = arr[i];
			int y = (int)Math.pow(-1, (n+1) %3)*(2*n-2);
			arr1[i] = y;
		}
		return arr1;
	}
	public static int probability(int n, int m) {
		double b = 1000;
		int[] arr = new int[200];
		int count = 0;
		for(int i = 1000; i<n; i++) {
			if(i %m == 7) {
				System.out.println(i + " ");
			}
		}
		return count;
	}
	public static String toString(int[] arr) {
		String str = "";
		for(int i = 0; i<arr.length; i++) {
			str += arr[i] + ", ";
		}
		return str;
	}
}
