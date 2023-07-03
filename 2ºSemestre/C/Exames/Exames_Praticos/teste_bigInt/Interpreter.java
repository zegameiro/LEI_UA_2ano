import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends BigIntegerLangBaseVisitor<Integer> {

   HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();

   @Override public Integer visitShow(BigIntegerLangParser.ShowContext ctx) {
      Integer res = visit(ctx.expr());
      if(res != null) 
         System.out.println(res);

      return res;
   }

   @Override public Integer visitAssign(BigIntegerLangParser.AssignContext ctx) {
      String var = ctx.VAR()-getText();
      Integer res = visit(ctx.expr());

      symbolTable.put(var, res);

      return null;
   }

   @Override public Integer visitCondition(BigIntegerLangParser.ConditionContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Integer visitExprVAR(BigIntegerLangParser.ExprVARContext ctx) {
      Integer res = ctx.VAR().getText();
   }

   @Override public Integer visitExprSumSub(BigIntegerLangParser.ExprSumSubContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Integer visitExprParent(BigIntegerLangParser.ExprParentContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Integer visitExprUnary(BigIntegerLangParser.ExprUnaryContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Integer visitExprINT(BigIntegerLangParser.ExprINTContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Integer visitExprMultDivMod(BigIntegerLangParser.ExprMultDivModContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }
}
