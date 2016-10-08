// ----------------------------------------------------------------------
// FileReverse.java
// Spencer Albrecht
// salbrecht
// 12M
// Reverses the input of one file and outputs it to another FileReverse
// ----------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class FileReverse {

  public static void main(String[] args) throws IOException {

    // Makes sure that there are two files
    if(args.length < 2){
      System.out.println("Usage: FileCopy <input file> <output file>");
      System.exit(1);
    }

    else {
      // Creates scanner and printwriter for file IO
      Scanner file_in = new Scanner(new File(args[0]));
      PrintWriter file_out = new PrintWriter(new FileWriter(args[1]));

      // Loop that takes strings and tokenizes them
      while( file_in.hasNextLine() ){
        String line = file_in.nextLine().trim() + " ";
        String[] token = line.split("\\s+");

        int n = token.length;

        // Prints out the reversed string to the new file
        for(int i=0; i<n; i++){
          file_out.println(" "+stringReverse(token[i],token[i].length()));
        }

      }
      // Closes the files
      file_in.close();
      file_out.close();
    }

  }

  public static String stringReverse(String s, int n) {
    if (n==1) {
      return String.valueOf(s.charAt(0));
    }
    else {
      return String.valueOf(s.charAt(n-1))+stringReverse(s.substring(0,n-1),n-1);
    }
  }

}
