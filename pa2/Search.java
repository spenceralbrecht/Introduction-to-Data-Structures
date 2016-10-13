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
      // matches the end of file character
      in.useDelimiter("\\Z");
      // read in whole file as a single String
      String s = in.next();
      // split s into individual lines
      String[] string_array = s.split("\n");
      in.close();
      String[] target_array = ["apple","banana"];
   }
}
