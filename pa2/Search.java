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
// 4. Sort the string array that contains all the
//    words using a mergesort algorithm
// 5. Use binary sort to see if the target words are
//    in the sorted string array
// 6. If binary search is not successful it will return
//    -1, so just check if that value to see if the target
//    was found
// 7. To be continued...
// --------------------------------------------------

class Search {
   public static void main(String[] args) {
      Scanner in = new Scanner(new File(args[0]));
      // makes sure that the end of the file
      // character isn't included in string
      in.useDelimiter("\\Z");
      // read in whole file as a single String
      String s = in.next();
      // split s into individual lines
      String[] string_array = s.split("\n");
      in.close();
      String[] target_array = ["apple","banana"];

      mergeSort(string_array, 0, string_array.length-1);

      System.out.println(Arrays.toString(string_array));

   }

   public static void mergeSort(int[] A, int start, int end){
     if(start < end) {
       int mid = (start+end)/2;
       // System.out.println(p+" "+q+" "+r);
       mergeSort(A, start, mid);
       mergeSort(A, mid+1, end);
       merge(A, start, mid, end);
     }
   }

   public static void merge(int[] A, int start, int mid, int end){
     // Calculates the size of the left side and creates
     // a properly sized array
     int left_size = mid-start+1;
     int[] left = new int[left_size];

     // Calculates the size of the right side and creates
     // a properly sized array
     int right_size= end-mid;
     int[] right = new int[right_size];

     int i = 0, j = 0;

     // Copies the data on the left side into the left array
     for(i=0; i<left_size; i++){
       left[i] = A[start+i];
     }

     // Copies the data on the right side into the right array
     for(j=0; j<right_size; j++){
       right[j] = A[mid+j+1];
     }

     // Loop that runs through the entire length of the array
     for(int k=start; k<=end; k++){
       if( i<left_size && j<right_size ){
         if( L[i]<R[j] ){
           A[k] = L[i];
           i++;
         }else{
           A[k] = R[j];
           j++;
         }
       }
       else if( i<left_size ){
         A[k] = L[i];
         i++;
       }
       else{ // j<right_size
         A[k] = R[j];
         j++;
       }
     }
   }

}
