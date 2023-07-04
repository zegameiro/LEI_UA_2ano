import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class HelloMain {
   public static void main(String[] args) throws Exception {
      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(System.in);
      // create a lexer that feeds off of input CharStream:
      HelloLexer lexer = new HelloLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      HelloParser parser = new HelloParser(tokens);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at r rule:
      ParseTree tree = parser.r();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         ParseTreeWalker walker = new ParseTreeWalker();
         MyListener listener0 = new MyListener();
         walker.walk(listener0, tree);
      }
   }
}
