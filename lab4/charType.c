#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

// function prototype
void extract_chars(char* s, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]){
	char ch;
	int i, j, count;
	if( argc>1 ) {
		FILE* in;
		FILE* out;
		char* line;
		char* character;
		char* punctuation;
	 	char* digit;
		char* space;

		
		// open input file for reading 
		if( (in=fopen(argv[1], "r"))==NULL ){
     			printf("Unable to read from file %s\n", argv[1]);
      			exit(EXIT_FAILURE);
   		}

   		// open output file for writing 
   		if( (out=fopen(argv[2], "w"))==NULL ){
      			printf("Unable to write to file %s\n", argv[2]);
      			exit(EXIT_FAILURE);
   		}
			
		// allocate string line that will contain the original file line 
   		line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
		// allocation for character, punctuation, digit, and space string
   		character = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   		punctuation = calloc(MAX_STRING_LENGTH+1, sizeof(char));
		digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
		space = calloc(MAX_STRING_LENGTH+1, sizeof(char));
		assert( line!=NULL && character!=NULL && punctuation!=NULL && digit!=NULL && space!=NULL );

   		// read each line in input file, extract alpha-numeric characters 
  		while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
			fprintf(out, "Line %d contains:\n", count);
      			extract_chars(line, character, digit, punctuation, space);
   		}
		
		// free heap memory 
	    	free(line);
  	 	free(character);
		free(punctuation);
		free(digit);
		free(space);

  		// close input and output files 
  	 	fclose(in);
   		fclose(out);	 

	   /*
			ch = argv[i][0];
			count = j = 0;
			while( ch!='\0' ) {
			if(isalnum((int)ch)) {
				count++;
			}
			ch = argv[i][++j];
			}
			printf("%s contains %d alphanumeric and ", argv[i], count);
			printf("%d non-alphanumeric characters\n", strlen(argv[i])-count);
	
     	*/
	return EXIT_SUCCESS;
}

void extract_chars(char* s, char* a, char* d, char* p, char* w) {
}
