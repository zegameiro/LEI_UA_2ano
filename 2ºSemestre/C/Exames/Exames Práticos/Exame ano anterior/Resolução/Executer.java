import java.math.BigInteger;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Executer extends BigIntCalcBaseVisitor<BigInteger> {

   HashMap<String, BigInteger> variables = new HashMap<>();

   @Override
   public BigInteger visitProgram(BigIntCalcParser.ProgramContext ctx) {
      BigInteger res = null;
      return visitChildren(ctx);
      // return res;
   }

   @Override
   public BigInteger visitStat(BigIntCalcParser.StatContext ctx) {
      BigInteger res = null;
      return visitChildren(ctx);
      // return res;
   }

   @Override
   public BigInteger visitShow(BigIntCalcParser.ShowContext ctx) {
      BigInteger res = visit(ctx.expr());

      if (res != null) {
         System.out.println(res);
      }

      return res;
   }

   @Override
   public BigInteger visitAssign(BigIntCalcParser.AssignContext ctx) {
      BigInteger res = visit(ctx.expr());
      String id = ctx.ID().getText();

      if (id != null && res != null) {
         variables.put(id, res);
      }
      return res;
   }

   @Override
   public BigInteger visitExprUnary(BigIntCalcParser.ExprUnaryContext ctx) {
      String op = ctx.op.getText();
      BigInteger res = visit(ctx.expr());

      if (op.equals("-")) {
         res = res.negate();
      }
      return res;
   }

   @Override
   public BigInteger visitExprAddSub(BigIntCalcParser.ExprAddSubContext ctx) {
      BigInteger res = null;
      BigInteger left = visit(ctx.expr(0));
      BigInteger right = visit(ctx.expr(1));
      String op = ctx.op.getText();

      if (left != null && right != null) {
         if (op.equals("+")) {
            res = left.add(right);
         } else if (op.equals("-")) {
            res = left.subtract(right);
         }
      }
      return res;
   }

   @Override
   public BigInteger visitExprMultDiv(BigIntCalcParser.ExprMultDivContext ctx) {
      BigInteger res = null;
      BigInteger left = visit(ctx.expr(0));
      BigInteger right = visit(ctx.expr(1));
      String op = ctx.op.getText();

      if (left != null && right != null) {
         if (op.equals("*")) {
            res = left.multiply(right);
         } else if (op.equals("div")) {
            res = left.divide(right);
         }
      }
      return res;
   }

   @Override
   public BigInteger visitExprMod(BigIntCalcParser.ExprModContext ctx) {
      BigInteger res = null;
      BigInteger left = visit(ctx.expr(0));
      BigInteger right = visit(ctx.expr(1));

      if (left != null && right != null) {
         res = left.mod(right);
      }
      return res;
   }

   @Override
   public BigInteger visitExprParenteses(BigIntCalcParser.ExprParentesesContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public BigInteger visitExprNumber(BigIntCalcParser.ExprNumberContext ctx) {
      BigInteger res = new BigInteger(ctx.getText());
      return res;
   }

   @Override
   public BigInteger visitExprID(BigIntCalcParser.ExprIDContext ctx) {
      String variable = ctx.getText();

      if (!variables.containsKey(variable)) {
         System.out.println("Variable " + variable + " not yet assigned!");
         return null;
      }
      return variables.get(variable);
   }
}
