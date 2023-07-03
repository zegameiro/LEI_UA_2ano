import java.util.Scanner;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Execute extends FractionsCalculatorBaseVisitor<Fraction> {

   HashMap<String, Fraction> memory = new HashMap<String, Fraction>();

   @Override public Fraction visitProgram(FractionsCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitStat(FractionsCalculatorParser.StatContext ctx) {
      try {
         visit(ctx.expr());
      } catch (NullPointerException e){
         try {
            visit(ctx.print());
         } catch (NullPointerException e0) {
            try {
               visit(ctx.assignment());
            } catch (NullPointerException e1) { }
         }
      }
      return null;
   }

   @Override public Fraction visitAssignment(FractionsCalculatorParser.AssignmentContext ctx) {
      String id = ctx.ID().getText();
      Fraction value = visit(ctx.expr());
      memory.put(id, value);
      return value;
   }

   @Override public Fraction visitPrint(FractionsCalculatorParser.PrintContext ctx) {
      Fraction value = visit(ctx.expr());
      if(value != null)
         System.out.println(">" + value);
      return null;
   }

   @Override public Fraction visitReduce(FractionsCalculatorParser.ReduceContext ctx) {
      Fraction res = visit(ctx.expr());
      return res.reduce();
   }

   @Override public Fraction visitRead(FractionsCalculatorParser.ReadContext ctx) {
      Scanner scanner = new Scanner(System.in);
      System.out.println(ctx.ID().getText() + ": ");
      String input = scanner.nextLine();
      try {
         String[] inputArray = input.split("/");
         int numerator = Integer.parseInt(inputArray[0]);
         int denominator = Integer.parseInt(inputArray[1]);
         return new Fraction(numerator, denominator);
      } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
         System.err.println("ERROR: invalid sintax");
         return null;
      }
   }

   @Override public Fraction visitExprAddSub(FractionsCalculatorParser.ExprAddSubContext ctx) {
      Fraction value0 = visit(ctx.expr(0));
      Fraction value1 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      Fraction res = new Fraction(1, 1);

      switch (op) {
         case "+":
            res = value0.sumFraction(value1);
            break;
         case "-":
            res = value0.subFraction(value1);
            break;
         default:
            System.err.println("ERROR: invalid operator");
            break;
      }
      return res;
   }

   @Override public Fraction visitExprRead(FractionsCalculatorParser.ExprReadContext ctx) {
      return visit(ctx.read());
   }

   @Override public Fraction visitExprParent(FractionsCalculatorParser.ExprParentContext ctx) {
      String signal0 = null;

      try {
         signal0 = ctx.signal0.getText();
      } catch (NullPointerException e) { 
         signal0 = "+";
      }
      Fraction aux = visit(ctx.expr());

      String signal1 = null;

      try {
         signal1 = ctx.signal1.getText();
      } catch (NullPointerException e) { 
         signal1 = "+";
      }

      int exp;

      try {
         exp = Integer.parseInt(ctx.INT().getText());
      } catch (NullPointerException e) { 
         exp = 1;
      }

      if(signal1.equals("-"))
         exp *= -1;

      if(signal0.equals("-")) 
         return new Fraction(aux.getNumerator()*-1, aux.getDenominator()).expFraction(exp);
      else
         return aux.expFraction(exp);
   }

   @Override public Fraction visitExprInt(FractionsCalculatorParser.ExprIntContext ctx) {
      return new Fraction(Integer.parseInt(ctx.INT().getText()), 1);
   }

   @Override public Fraction visitExprMultDiv(FractionsCalculatorParser.ExprMultDivContext ctx) {
      Fraction value0 = visit(ctx.expr(0));
      Fraction value1 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      Fraction res = new Fraction(1, 1);

      switch (op) {
         case "*":
            res = value0.multFraction(value1);
            break;
         case ":":
            res = value0.divideFraction(value1);
            break;
         default:
            System.err.println("ERROR: invalid operator");
            break;
      }

      return res;
   }

   @Override public Fraction visitExprReduce(FractionsCalculatorParser.ExprReduceContext ctx) {
      return visit(ctx.reduce());
   }

   @Override public Fraction visitExprId(FractionsCalculatorParser.ExprIdContext ctx) {
      String id = ctx.ID().getText();

      if (memory.containsKey(id))
         return memory.get(id);

      else {
         System.err.println("ERROR: variable " + id + " not found");
         return null;
      }
   }

   @Override public Fraction visitExprFr(FractionsCalculatorParser.ExprFrContext ctx) {
      String signal;
      try {
         signal = ctx.signal.getText();
      } catch (NullPointerException e) { 
         signal = "+";
      }
      
      int aux = 1;

      if(signal.equals("-"))
         aux = -1;

      return new Fraction(Integer.parseInt(ctx.INT(0).getText())*aux, Integer.parseInt(ctx.INT(1).getText()));
   }
}
