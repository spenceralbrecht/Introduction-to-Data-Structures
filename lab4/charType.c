#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

// function prototype
void extract_chars(char* s, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]){
	int count = 1;
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
			// call the extract chars that will appropriately separate the chars
      			extract_chars(line, character, digit, punctuation, space);
			// print the output that shows the count of each line
			fprintf(out, "line %d contains: ", count);
			fprintf(out, "\n%d alphabetic %s: ", strlen(character), (strlen(character)==1) ? "character": "characters");
			fprintf(out, "%s", character);
			fprintf(out, "\n%d numeric %s: ", strlen(digit), (strlen(digit)==1) ? "character": "characters");
			fprintf(out, "%s", digit);
			fprintf(out, "\n%d punctuation %s: ", strlen(punctuation), (strlen(punctuation)==1) ? "character": "characters");
			fprintf(out, "%s", punctuation);
			fprintf(out, "\n%d whitespace %s: ", strlen(space), (strlen(space)==1) ? "character": "characters");
			fprintf(out, "%s", space);
			count++;
			fprintf(out, "\n");
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

	return EXIT_SUCCESS;
	}
}

void extract_chars(char* s, char* a, char* d, char* p, char* w) {
	int i = 0; 
	int alpha_counter = 0;
	int digit_counter = 0;
	int punct_counter = 0;
	int space_counter = 0;
	while(s[i]!='\0' && i<MAX_STRING_LENGTH) {
		if(isalpha(s[i])) {
			a[alpha_counter++]=s[i];
		}
		else if(isdigit(s[i])) {
			d[digit_counter++]=s[i];
		}
		else if(ispunct(s[i])) {
			p[punct_counter++]=s[i];
		}
		else if(isspace(s[i])) {
			w[space_counter++]=s[i];
		}
		i++;
	}
	a[alpha_counter]='\0';
	d[digit_counter]='\0';
	p[punct_counter]='\0';
	w[space_counter]='\0';
}

