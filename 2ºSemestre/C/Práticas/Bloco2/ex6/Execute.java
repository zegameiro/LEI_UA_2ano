import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Execute extends VariablesCalculatorBaseVisitor<Integer> {



   @Override public Integer visitProgram(VariablesCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Integer visitStat(VariablesCalculatorParser.StatContext ctx) {
      Integer res = null;
      if(ctx.expr() != null) {
         res = visit(ctx.expr());
         System.out.println("result = " + visit(ctx.expr()));
      } else if(ctx.assignment() != null)
         res = visit(ctx.assignment());
         System.out.println("result = " + visit(ctx.assignment()));

      return res;
   }

   @Override public Integer visitAssignment(VariablesCalculatorParser.AssignmentContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Integer visitExprAddSub(VariablesCalculatorParser.ExprAddSubContext ctx) {
      Integer value1 = visit(ctx.expr(0));
      Integer value2 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      Integer res = 0;

      switch (op) {
         case "+":
            res = value1 + value2;
            break;
         case "-":
            res = value1 - value2;
            break;
         default:
            System.err.println("ERROR: Unknown operator: " + op);
            System.exit(1);
      }

      return res;
   }

   @Override public Integer visitExprParens(VariablesCalculatorParser.ExprParensContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Integer visitExprInteger(VariablesCalculatorParser.ExprIntegerContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Integer visitExprId(VariablesCalculatorParser.ExprIdContext ctx) {
      Integer res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Integer visitExprMultDivMod(VariablesCalculatorParser.ExprMultDivModContext ctx) {
      Integer value1 = visit(ctx.expr(0));
      Integer value2 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      Integer res = 0;

      switch(op) {
         case "*":
            res = value1 * value2;
            break;
         case "/":
            res = value1 / value2;
            break;
         case "%":
            res = value1 % value2;
            break;
         default:
            System.err.println("ERROR: Unknown operator: " + op);
            System.exit(1);
      }
      return res;
   }
}
