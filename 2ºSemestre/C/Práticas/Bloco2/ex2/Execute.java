@SuppressWarnings("CheckReturnValue")
public class Execute extends SuffixCalculatorBaseVisitor<Double> {

   @Override public Double visitProgram(SuffixCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(SuffixCalculatorParser.StatContext ctx) {
      Double res = visit(ctx.expr());

      if(res != null)
         System.out.println("result= " + res);

      return res;
   }

   @Override public Double visitExprNumber(SuffixCalculatorParser.ExprNumberContext ctx) {
      Double res = Double.parseDouble(ctx.getText());
      return res;
   }

   @Override public Double visitExprSuffix(SuffixCalculatorParser.ExprSuffixContext ctx) {
      double num1 = visit(ctx.expr(0));
      double num2 = visit(ctx.expr(1));
      double result = 0;
      String operator = ctx.op.getText();

      switch(operator) {
         case "+":
            result = num1 + num2;
            break;
         case "-":
            result = num1 - num2;
            break;
         case "*":
            result = num1 * num2;
            break;
         case "/":
            if(num2 != 0)
               result = num2 / num1;
            break;
         default:
            result = 0;
            break;
      }

      return result;
   }
}
