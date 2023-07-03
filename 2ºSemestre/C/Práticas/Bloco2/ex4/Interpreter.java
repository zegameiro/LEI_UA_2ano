@SuppressWarnings("CheckReturnValue")
public class Interpreter extends PreffixCalculatorBaseVisitor<Double> {

   @Override public Double visitProgram(PreffixCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(PreffixCalculatorParser.StatContext ctx) {
      Double res = null;

      if(ctx.expr() != null) {
         res = visit(ctx.expr());
         if(res != null) {
            System.out.println("result = " + res);
         }
      }

      return res;
   }

   @Override public Double visitExprPreffix(PreffixCalculatorParser.ExprPreffixContext ctx) {
      Double num1 = visit(ctx.expr(0));
      Double num2 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      Double res = null;

      switch(op) {
         case "*":
            res = num1 * num2;
            break;

         case "/":
            if(num2 != 0) {
               res = num1 / num2;
               break;
            } else {
               System.err.println("ERROR: can't divide by 0");
               break;
            }

         case "+":
            res = num1 + num2;
            break;
         
         case "-":
            res = num1 - num2;
            break;
         
         default:
            System.err.println("ERROR: invalid operator");
            break;
      }

      return res;
   }

   @Override public Double visitExprNumber(PreffixCalculatorParser.ExprNumberContext ctx) {
      return Double.parseDouble(ctx.Number().getText());
   }
}
