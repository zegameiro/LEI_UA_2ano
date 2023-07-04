import java.util.Scanner;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends StrLangBaseVisitor<String> {

   private HashMap<String, String> symbolTable = new HashMap<String, String>();
   private Scanner sc = new Scanner(System.in);

   @Override public String visitStat(StrLangParser.StatContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitAssign(StrLangParser.AssignContext ctx) {
      String var = ctx.VAR().getText();
      String value = visit(ctx.expr());

      symbolTable.put(var, value);
      
      return null;
   }

   @Override public String visitPrint(StrLangParser.PrintContext ctx) {
      String res = visit(ctx.expr());

      if(res != null) {
         System.out.println(res);
      }

      return null;
   }

   @Override public String visitExprVAR(StrLangParser.ExprVARContext ctx) {
      String res = ctx.VAR().getText();
      String aux = "";
      if(symbolTable.containsKey(res)) {
         aux = symbolTable.get(res);
      } else {
         System.err.println("ERROR: variable " + res + " is not defined");
         System.exit(0);
      }

      return aux;
   }

   @Override public String visitExprAddText(StrLangParser.ExprAddTextContext ctx) {
      String res = "";
      String str1 = visit(ctx.expr(0));
      String str2 = visit(ctx.expr(1));

      res = str1 + str2;

      return res;
   }

   @Override public String visitExprParent(StrLangParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprTrimText(StrLangParser.ExprTrimTextContext ctx) {
      String res = visit(ctx.expr());
      String aux = res.trim();

      return aux;
   }

   @Override public String visitExprRemoveText(StrLangParser.ExprRemoveTextContext ctx) {
      String op1 = visit(ctx.expr(0));
      String op2 = visit(ctx.expr(1));
      String res = "";

      if(op1.contains(op2)) {
         res = op1.replace(op2, "");
      } else {
         System.err.println("ERROR: could not remove " + op2 + " from " + op1 + " because " + op1 + " doesn't contains it");
         System.exit(0);
      }

      return res;
   }

   @Override public String visitExprReplaceText(StrLangParser.ExprReplaceTextContext ctx) {
      String originalStr = visit(ctx.expr(0));
      String replaceStr = visit(ctx.expr(1));
      String replacer = visit(ctx.expr(2));
      String res = "";

      res = originalStr.replace(replaceStr, replacer);

      return res;
   }

   @Override public String visitExprInput(StrLangParser.ExprInputContext ctx) {
      String res = visit(ctx.expr());
      System.out.println(res);
      String aux = sc.nextLine();
      return aux;
   }

   @Override public String visitExprSTR(StrLangParser.ExprSTRContext ctx) {
      String res = ctx.STR().getText();
      String aux = res.substring(1, res.length() - 1);
      return aux;
   }
}
