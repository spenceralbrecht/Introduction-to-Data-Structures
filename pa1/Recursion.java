import java.util.Arrays;

class Recursion {
   public static void main(String[] args) {
      int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
      int[] B = new int[A.length];
      int[] C = new int[A.length];
      int minIndex = minArrayIndex(A, 0, A.length-1);
      int maxIndex = maxArrayIndex(A, 0, A.length-1);

      for(int x: A) System.out.print(x+" ");
      System.out.println();

      System.out.println( "minIndex = " + minIndex );
      System.out.println( "maxIndex = " + maxIndex );

      reverseArray1(A, A.length, B);
      for(int x: B) System.out.print(x+" ");
      System.out.println();

      reverseArray2(A, A.length, C);
      for(int x: C) System.out.print(x+" ");
      System.out.println();

      reverseArray3(A, 0, A.length-1);
      for(int x: A) System.out.print(x+" ");
      System.out.println();
   }

   static void reverseArray1(int[] X, int n, int[] Y) {
      if (n == 0 || n < 0) {
         return;
      }
      else {
         Y[Y.length-n] = X[n-1];
         reverseArray1(X, n-1, Y);
      }
   }

   static void reverseArray2(int[] X, int n, int[] Y) {
      if (n == 0 || n < 0) {
         return;
      }
      else {
         Y[n-1] = X[X.length-n];
         reverseArray2(X, n-1, Y);
      }
   }

   static void reverseArray3(int[] X, int i, int j) {
      if (i+1 == j || i == j) {
         int temp = X[i];
         X[i] = X[j];
         X[j] = temp;
         return;
      }
      else {
         int temp = X[i];
         X[i] = X[j];
         X[j] = temp;
         reverseArray3(X, i+1, j-1);
      }
   }

   static int maxArrayIndex(int[] X, int p, int r) {
      if (p == r || p < r) {
         return p;
      }
      else {
         int q = (p+r)/2;
         int left = maxArrayIndex(X,p,q);
         int right = maxArrayIndex(X,q+1,r);
         if (X[left] > X[right]) {
            return left;
         }
         else {
            return right;
         }
      }
   }

   static int minArrayIndex(int[] X, int p, int r) {
      if (p == r || p < r) {
         return p;
      }
      else {
         int q = (p+r)/2;
         int left = minArrayIndex(X,p,q);
         int right = minArrayIndex(X,q+1,r);
         if (X[left] < X[right]) {
            return left;
         }
         else {
            return right;
         }
      }
   }

}
