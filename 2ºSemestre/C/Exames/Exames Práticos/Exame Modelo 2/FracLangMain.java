import java.io.IOException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class FracLangMain {
   public static void main(String[] args) {
      for(String filename : args) {
         try {
            // create a CharStream that reads from standard input:
            CharStream input = CharStreams.fromFileName(System.in);
            // create a lexer that feeds off of input CharStream:
            FracLangLexer lexer = new FracLangLexer(input);
            // create a buffer of tokens pulled from the lexer:
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // create a parser that feeds off the tokens buffer:
            FracLangParser parser = new FracLangParser(tokens);
            // replace error listener:
            //parser.removeErrorListeners(); // remove ConsoleErrorListener
            //parser.addErrorListener(new ErrorHandlingListener());
            // begin parsing at program rule:
            ParseTree tree = parser.program();
            if (parser.getNumberOfSyntaxErrors() == 0) {
               // print LISP-style tree:
               // System.out.println(tree.toStringTree(parser));
               Execute visitor0 = new Execute();
               visitor0.visit(tree);
            }
         }
         catch(IOException e) {
            System.err.println(filename);
         }
         catch(RecognitionException e) {
            e.printStackTrace();
            System.exit(1);
         }
   
      }
   }
}
