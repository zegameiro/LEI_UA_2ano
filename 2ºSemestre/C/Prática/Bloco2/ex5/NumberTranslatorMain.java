import java.io.IOException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.Scanner;

public class NumberTranslatorMain {
   public static void main(String[] args) {

      InputStream inStream = null; 
      try {
         inStream = new FileInputStream(new File("numbers.txt"));
      } catch(FileNotFoundException e) {
         System.out.println("Error: File not found");
         System.exit(1);
      }
      try {
         // create a CharStream that reads from standard input:
         CharStream input = CharStreams.fromStream(System.in);
         // create a lexer that feeds off of input CharStream:
         NumberTranslatorLexer lexer = new NumberTranslatorLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         NumberTranslatorParser parser = new NumberTranslatorParser(tokens);
         // replace error listener:
         //parser.removeErrorListeners(); // remove ConsoleErrorListener
         //parser.addErrorListener(new ErrorHandlingListener());
         // begin parsing at file rule:
         ParseTree tree = parser.file();
         if (parser.getNumberOfSyntaxErrors() == 0) {
            // print LISP-style tree:
            // System.out.println(tree.toStringTree(parser));
            ParseTreeWalker walker = new ParseTreeWalker();
            Listener mappings = new Listener();
            walker.walk(mappings, tree);

            Scanner userInput = new Scanner(System.in);
            while(userInput.hasNextLine()){
               String line = userInput.nextLine();

               String[] words = line.replace('-',' ').toLowerCase().split(" "); //Get an array of the users input

               for(int i = 0 ; i < words.length ; i++) { 
                  if(i != 0)
                     System.out.print(" ");
                  if(mappings.exists(words[i]))
                     System.out.print(mappings.returnValue(words[i])); 
                  else 
                     System.out.print(words[i]);
               }

               System.out.println("\nContinue? Y/N");
               String response = userInput.nextLine();

               if(response.equals("Y") || response.equals("y"))
                  System.out.println("");
               else
                  break;
            }
         }
      } catch(IOException e) {
         e.printStackTrace();
         System.exit(1);
      } catch(RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}