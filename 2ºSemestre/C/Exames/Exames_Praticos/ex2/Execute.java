import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Execute extends FracLangBaseVisitor<Fraction> {

   private HashMap<String, Fraction> symboltable = new HashMap<String, Fraction>();
   private static Scanner in = new Scanner(System.in);

   @Override public Fraction visitCommand(FracLangParser.CommandContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitDisplay(FracLangParser.DisplayContext ctx) {
      Fraction res = visit(ctx.expr());
      if(res != null) {
         System.out.println(res);
      }
      return res;
   }

   @Override public Fraction visitAssignment(FracLangParser.AssignmentContext ctx) {
      Fraction res = visit(ctx.expr());
      String id = ctx.ID().getText();
      if(res != null) {
         symboltable.put(id, res);
      }
      return res; 
   }

   @Override public Fraction visitExprUnary(FracLangParser.ExprUnaryContext ctx) {
      Fraction res = visit(ctx.expr());
      if(res.error()) {
         if("-".equals(ctx.op.getText()))
            res = new Fraction(-res.num(), res.den());
      }
      return res;
   }

   @Override public Fraction visitExprMultDiv(FracLangParser.ExprMultDivContext ctx) {
      Fraction res = null;
      Fraction e1 = visit(ctx.expr(0));
      Fraction e2 = visit(ctx.expr(1));
      if(e1 != null && e2 != null) {
         switch(ctx.op.getText()) {
            case "*":
               res = new Fraction(e1.num() * e2.num(), e1.den() * e2.den());
               break;
            case "/":
               res = new Fraction(e1.num() * e2.den(), e1.den() * e2.num());
               break;
         }
      }

      return res;
   }

   @Override public Fraction visitExprSumSub(FracLangParser.ExprSumSubContext ctx) {
      Fraction res = null;
      Fraction e1 = visit(ctx.expr(0));
      Fraction e2 = visit(ctx.expr(1));
      if(e1 != null && e2 != null) {
         switch(ctx.op.getText()) {
            case "+":
               res = new Fraction(e1.num() * e2.num() + e2.num() * e1.den(), e1.den() * e2.den());
               break;
            case "-":
               res = new Fraction(e1.num() * e2.den(), e1.den() * e2.num());
               break;
         }
      }

      return res;
   }

   @Override public Fraction visitExprParent(FracLangParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Fraction visitExprRead(FracLangParser.ExprReadContext ctx) {
      String prompt = ctx.String().getText();
      prompt = prompt.substring(1, prompt.length() - 1);
      System.out.println(prompt + ": ");
      String frac = in.nextLine();
      Fraction res = new Fraction(frac);
      if(res.error()) {
         System.err.println("ERROR: invalid literal fraction \"" + frac + "\"!");
         res = null;
      }

      return null;
   }

   @Override public Fraction visitExprFraction(FracLangParser.ExprFractionContext ctx) {
      String frac = ctx.Fraction().getText();
      Fraction res = new Fraction(frac);
      if(res.error()) {
         System.err.println("ERROR: invalid literal fraction \"" + frac + "\"!");
         res = null;
      }

      return res;
   }

   @Override public Fraction visitExprID(FracLangParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();
      Fraction res = null;
      if(!symboltable.containsKey(res)) {
         System.err.println("ERROR: invalid literal fraction \"" + id + "\"!");
         res = null;
      } else

      return res;
   }
}
