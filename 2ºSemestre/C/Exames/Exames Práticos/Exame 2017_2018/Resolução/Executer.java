import java.util.*;

@SuppressWarnings("CheckReturnValue")
public class Executer extends CalComplexBaseVisitor<Complex> {

   HashMap<String, Complex> variables = new HashMap<>();

   @Override public Complex visitProgram(CalComplexParser.ProgramContext ctx) {
      Complex res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Complex visitStat(CalComplexParser.StatContext ctx) {
      Complex res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Complex visitOutput(CalComplexParser.OutputContext ctx) {
      Complex res = visit(ctx.expr());
      if (res != null){
         System.out.println(res);
      }
      return res;
   }

   @Override public Complex visitAssign(CalComplexParser.AssignContext ctx) {
      String id = ctx.ID().getText();
      Complex res = visit(ctx.expr());

      if (id != null && res != null){
         variables.put(id, res);
         return res;
      }
      return null;
   }

   @Override public Complex visitExprUnary(CalComplexParser.ExprUnaryContext ctx) {
    Complex c1 = visit(ctx.expr());
    String op = ctx.op.getText();
    return new Complex(
        op.equals("-") ? -c1.getReal() : c1.getReal(),
        op.equals("-") ? -c1.getImaginary() : c1.getImaginary()
    );
}


   @Override public Complex visitExprAddSub(CalComplexParser.ExprAddSubContext ctx) {
      Complex c1 = visit(ctx.expr(0));
      Complex c2 = visit(ctx.expr(1));
      Complex res = null;
      
      switch (ctx.op.getText()) {
         case "+":
            res = c1.add(c2);
            break;
         case "-":
            res = c1.subtract(c2);
            break;
      }
      return res;
   }

   @Override public Complex visitExprMultDiv(CalComplexParser.ExprMultDivContext ctx) {
      Complex c1 = visit(ctx.expr(0));
      Complex c2 = visit(ctx.expr(1));
      Complex res = null;
      
      switch (ctx.op.getText()) {
         case "*":
            res = c1.multiply(c2);
            break;
         case ":":
            res = c1.divide(c2);
            break;
         default:
            System.out.println("Action not supported");
            break;
      }
      return res;
   }

   @Override public Complex visitExprParenteses(CalComplexParser.ExprParentesesContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Complex visitExprComplex(CalComplexParser.ExprComplexContext ctx) {
      Complex res = new Complex(ctx.getText());
      return res;
   }

   @Override public Complex visitExprNumber(CalComplexParser.ExprNumberContext ctx) {
      Complex res = new Complex(ctx.getText());
      return res;
   }

   @Override public Complex visitExprID(CalComplexParser.ExprIDContext ctx) {
      String id = ctx.getText();

      if (!variables.containsKey(id)){
         System.out.println("Variable " + id + " not found");
         return null;
      }
      return variables.get(id);
   }
}
