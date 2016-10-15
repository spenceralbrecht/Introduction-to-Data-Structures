// ----------------------------------------------
// Search.java
// Spencer Albrecht
// salbrech
// pa2
// Searches through lines of a file and
// prints if target words are found and
// what line number
// ----------------------------------------------

import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

class Search {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner in = new Scanner(new File(args[0]));
      // makes sure that the end of the file
      // character isn't included in string
      in.useDelimiter("\\Z");
      // read in whole file as a single String
      String s = in.next();
      // split s into individual lines
      String[] string_array = s.split("(\r\n)|\n");

      int[] line_number = new int[string_array.length];
      for (int i = 0; i < line_number.length; i++) {
        line_number[i] = i+1;
      }

      // closes the scanner
      in.close();

      // System.out.println("unsorted string is");
      // System.out.println(Arrays.toString(string_array));

      mergeSort(string_array,line_number, 0, string_array.length-1);

      // System.out.println("Sorted string is");
      // System.out.println(Arrays.toString(string_array));
      //
      // System.out.println("line number is");
      // System.out.println(Arrays.toString(line_number));



      for (int i = 1; i < args.length; i++) {
        int return_val = binarySearch(string_array, 0, string_array.length-1, args[i]);
        if (return_val < 0 ) {
          System.out.println(args[i]+" not found");
        }
        else {
          System.out.println(args[i]+" found on line "+line_number[return_val]);
        }
      }

   }

   public static void mergeSort(String[] array, int[] line_number, int start, int end){
     if(start < end) {
       int mid = (start+end)/2;
       // System.out.println(p+" "+q+" "+r);
       mergeSort(array, line_number, start, mid);
       mergeSort(array, line_number, mid+1, end);
       merge(array, line_number, start, mid, end);
     }
   }

   public static void merge(String[] A, int[] line_number, int start, int mid, int end){
     // Calculates the size of the left side and creates
     // a properly sized array
     int left_size = mid-start+1;
     int[] left_ln = new int[left_size];
     String[] left = new String[left_size];

     // Calculates the size of the right side and creates
     // a properly sized array
     int right_size= end-mid;
     int[] right_ln = new int[right_size];
     String[] right = new String[right_size];

     int i,j;

     // Copies the data on the left side into the left array
     for(i=0; i<left_size; i++){
       left[i] = A[start+i];
       left_ln[i] = line_number[start+i];
      //  System.out.println("left array is ");
      //  System.out.println(Arrays.toString(left));
     }

     // Copies the data on the right side into the right array
     for(j=0; j<right_size; j++){
       right[j] = A[mid+j+1];
       right_ln[j] = line_number[mid+j+1];
      //  System.out.println("right array is ");
      //  System.out.println(Arrays.toString(right));
     }

    //  System.out.println("Start is "+start);
    //  System.out.println("End is "+end);

     i = 0;
     j = 0;
     // Loop that runs through the entire length of the array
     for(int k=start; k<=end; k++){
       if( i<left_size && j<right_size ){
         // Takes the smaller element of the two arrays
         // and stores it in the main array
         if( left[i].compareTo(right[j])<0 ){
           A[k] = left[i];
           line_number[k] = left_ln[i];
           i++;
         }else{
           A[k] = right[j];
           line_number[k] = right_ln[j];
           j++;
         }
       }
       // Stores the value of any leftover objects in the right or left
       // arrays into the main array
       else if( i<left_size ){
         A[k] = left[i];
         line_number[k] = left_ln[i];
         i++;
       }
       else{ // j<right_size
         A[k] = right[j];
         line_number[k] = left_ln[j];
         j++;
       }
     }
   }

   public static int binarySearch(String[] A, int start, int end,  String target){
      int mid;
      if(start > end) {
         return -1;
      }else{
         mid = (start+end)/2;
         if(target.equals(A[mid])){
            return mid;
         }else if(target.compareTo(A[mid])<0){
            return binarySearch(A, start, mid-1, target);
         }else{ // target > A[q]
            return binarySearch(A, mid+1, end, target);
         }
      }
   }

}
