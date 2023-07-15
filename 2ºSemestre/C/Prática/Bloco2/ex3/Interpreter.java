@SuppressWarnings("CheckReturnValue")
public class Interpreter extends CalculatorBaseVisitor<Integer> {

   @Override public Integer visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Integer visitStat(CalculatorParser.StatContext ctx) {
      try {
         System.out.println("result = " + visit(ctx.expr()));
      } catch (NullPointerException e) {
         System.err.println("ERROR: insert a operation");
      }

      return null;
   }

   @Override public Integer visitExprMulDivMod(CalculatorParser.ExprMulDivModContext ctx) {
      Integer val1 = visit(ctx.expr(0));
      Integer val2 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      Integer res = null;
      
      switch(op) {
         case "*":
            res = val1 * val2;
            break;

         case "/":
            if(val2 != 0) {
               res = val1 / val2;
               break;
            
            } else {
               System.err.println("ERROR: can't divide by 0");
               break;
            }

         case "%":
            if(val2 != 0) {
               res = val1 / val2;
               break;

            } else {
               System.err.println("ERROR: can't divide by 0");
               break;
            }
         
         default:
            System.err.println("ERROR: invalid operator");
            break;
      }
      return res;
   }

   @Override public Integer visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      Integer val1 = visit(ctx.expr(0));
      Integer val2 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      Integer res = null;

      switch(op) {
         case "+":
            res = val1 + val2;
            break;

         case "-":
            res = val1 - val2;
            break;
         
         default:
            System.err.println("ERROR: invalid operator");
            break;
      }

      return res;
   }

   @Override public Integer visitExprParent(CalculatorParser.ExprParentContext ctx) {
      String signal;

      try {
         signal = ctx.signal.getText();
      } catch(NullPointerException e) {
         signal = "+";
      }

      Integer res = visit(ctx.expr());

      if (signal.equals("+")) {
         return res;
      } else {
         return res * -1; 
      }
   }

   @Override public Integer visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      String signal;
      
      try {
         signal = ctx.signal.getText();
      } catch (NullPointerException e) {
         signal = "+";
      }

      Integer aux = Integer.parseInt(ctx.getText());
      Integer res = aux;

      return res;
   }
}
