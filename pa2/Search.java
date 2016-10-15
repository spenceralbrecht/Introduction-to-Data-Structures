// ----------------------------------------------
// PROGRAM OPERATION
// Program will take a file and target words as
// command line arguments. Then it will search
// through the given file for the target words
// and print to standard out whether each target
// word was found and on what line it was found.
// ----------------------------------------------
//
// ----------------------------------------------
// PLAN
// 1. (DONE) Determine the number of lines in the input file
// 2. (DONE) Create a string array of that length
// 3. (DONE) Scan the file again and store each word in
//    the string array
// 4. (DONE) Sort the string array that contains all the
//    words using a mergesort algorithm
// 5. Use binary sort to see if the target words are
//    in the sorted string array
// 6. If binary search is not successful it will return
//    -1, so just check if that value to see if the target
//    was found
// 7. To be continued...
// --------------------------------------------------
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

      // closes the scanner
      in.close();

      String[] target_array = {"apple","banana"};

      // System.out.println("The string array was created");
      // System.out.println(Arrays.toString(string_array));

      mergeSort(string_array, 0, string_array.length-1);

      System.out.println("Sorted string is");
      System.out.println(Arrays.toString(string_array));

   }

   public static void mergeSort(String[] array, int start, int end){
     if(start < end) {
       int mid = (start+end)/2;
       // System.out.println(p+" "+q+" "+r);
       mergeSort(array, start, mid);
       mergeSort(array, mid+1, end);
       merge(array, start, mid, end);
     }
   }

   public static void merge(String[] A, int start, int mid, int end){
     // Calculates the size of the left side and creates
     // a properly sized array
     int left_size = mid-start+1;
     String[] left = new String[left_size];

     // Calculates the size of the right side and creates
     // a properly sized array
     int right_size= end-mid;
     String[] right = new String[right_size];

     int i,j;

     // Copies the data on the left side into the left array
     for(i=0; i<left_size; i++){
       left[i] = A[start+i];
      //  System.out.println("left array is ");
      //  System.out.println(Arrays.toString(left));
     }

     // Copies the data on the right side into the right array
     for(j=0; j<right_size; j++){
       right[j] = A[mid+j+1];
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
           i++;
         }else{
           A[k] = right[j];
           j++;
         }
       }
       // Stores the value of any leftover objects in the right or left
       // arrays into the main array
       else if( i<left_size ){
         A[k] = left[i];
         i++;
       }
       else{ // j<right_size
         A[k] = right[j];
         j++;
       }
     }
   }

}
