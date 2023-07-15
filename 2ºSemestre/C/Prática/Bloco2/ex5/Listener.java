import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")

public class Listener extends NumberTranslatorBaseListener {

   protected Map<String,Integer> numToWordMap = new HashMap<>();

    //Checks if given key is in the map
    public boolean exists(String key){
        assert key != null;
        return numToWordMap.containsKey(key);
    } 

    //Checks if given key is in the map
    public int returnValue(String key){
        assert key != null;
        assert exists(key);
        return numToWordMap.get(key);
    } 


   @Override public void exitLine(NumberTranslatorParser.LineContext ctx) {
      String key = ctx.WORD().getText();
      int value = Integer.parseInt(ctx.NUM().getText()); //Fetch the value

      if(exists(key)){  //If the key had previously been declared
         System.out.println("Error: Can't insert repeated key!");
         System.exit(1);
      }

      numToWordMap.put(key, value);
   }

}
