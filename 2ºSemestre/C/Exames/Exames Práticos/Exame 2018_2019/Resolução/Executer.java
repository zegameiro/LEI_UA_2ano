import java.util.HashMap;

import static java.lang.System.*;

@SuppressWarnings("CheckReturnValue")
public class Executer extends VectorBaseVisitor<Vector> {

   HashMap<String, Vector> variables = new HashMap<>();

   @Override
   public Vector visitShow(VectorParser.ShowContext ctx) {
      Vector res = visit(ctx.expression());

      if (res != null) {
         System.out.println(res);
      }
      return res;
   }

   @Override
   public Vector visitAssign(VectorParser.AssignContext ctx) {
      String variable = ctx.ID().getText();
      Vector data = visit(ctx.expression());

      if (data != null) {
         variables.put(variable, data);
         return data;
      }
      return null;
   }

   @Override
   public Vector visitExprUnary(VectorParser.ExprUnaryContext ctx) {
      Vector res = visit(ctx.expression());

      if (ctx.op.getText().equals("-")) {
         if (res.isvector()) {
            res = new Vector(-res.getX());
         }
         res = new Vector(-res.getX(), -res.getX());
      }
      return res;
   }

   @Override
   public Vector visitExprMults(VectorParser.ExprMultsContext ctx) {
      Vector res = null;
      Vector num1 = visit(ctx.expression(0));
      Vector num2 = visit(ctx.expression(1));

      if (num1 != null && num2 != null) {
         if (num1.isvector() && num2.isvector()) {
            switch (ctx.op.getText()) {
               case "*":
                  res = new Vector(num1.getX() * num2.getX());
                  break;
               default:
                  System.out.println("Operação não suportada com escalares");
                  return null;
            }
         } else {
            switch (ctx.op.getText()) {
               case "*":
                  if (num1.isvector()) {
                     res = new Vector(num1.getX() * num2.getX(), num1.getX() * num2.getY());
                  } else if (num2.isvector()) {
                     res = new Vector(num1.getX() * num2.getX(), num1.getY() * num2.getX());
                  } else {
                     System.out.println("Operação não suportada");
                     return null;
                  }
                  break;
               case ".":
                  res = new Vector((num1.getX() * num2.getX()) + (num1.getY() * num2.getY()));
                  break;
            }
         }
      }
      return res;
   }

   @Override
   public Vector visitExprAddSub(VectorParser.ExprAddSubContext ctx) {
      Vector res = null;
      Vector num1 = visit(ctx.expression(0));
      Vector num2 = visit(ctx.expression(1));

      if (num1 != null && num2 != null) {
         if (num1.isvector() && num2.isvector()) {
            switch (ctx.op.getText()) {
               case "+":
                  res = new Vector(num1.getX() + num2.getX());
                  break;
               case "-":
                  res = new Vector(num1.getX() - num2.getX());
                  break;
            }
         } else {
            switch (ctx.op.getText()) {
               case "+":
                  res = new Vector(num1.getX() + num2.getX(), num1.getY() + num2.getY());
                  break;
               case "-":
                  res = new Vector(num1.getX() - num2.getX(), num1.getY() - num2.getY());
                  break;
            }
         }

      }
      return res;
   }

   @Override
   public Vector visitExprParenteses(VectorParser.ExprParentesesContext ctx) {
      return visit(ctx.expression());
   }

   @Override
   public Vector visitExprVector(VectorParser.ExprVectorContext ctx) {
      String coiso = ctx.VECTOR().getText();
      Vector res = new Vector(coiso);
      /*
       * if (res.error()) {
       * System.err.println("ERROR: invalid vector");
       * return null;
       * }
       */
      return res;
   }

   @Override
   public Vector visitExprNumber(VectorParser.ExprNumberContext ctx) {
      Vector res = new Vector(Double.parseDouble(ctx.getText()));
      return res;
   }

   @Override
   public Vector visitExprID(VectorParser.ExprIDContext ctx) {
      Vector res = null;
      String variable = ctx.getText();

      if (!variables.containsKey(variable)) {
         System.out.println("Variable " + variable + " not found");
         return null;
      }
      return variables.get(variable);
   }
}
