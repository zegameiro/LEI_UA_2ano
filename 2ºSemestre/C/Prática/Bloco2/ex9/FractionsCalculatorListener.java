// Generated from FractionsCalculator.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FractionsCalculatorParser}.
 */
public interface FractionsCalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FractionsCalculatorParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(FractionsCalculatorParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionsCalculatorParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(FractionsCalculatorParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionsCalculatorParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(FractionsCalculatorParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionsCalculatorParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(FractionsCalculatorParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionsCalculatorParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(FractionsCalculatorParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionsCalculatorParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(FractionsCalculatorParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionsCalculatorParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(FractionsCalculatorParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionsCalculatorParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(FractionsCalculatorParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionsCalculatorParser#reduce}.
	 * @param ctx the parse tree
	 */
	void enterReduce(FractionsCalculatorParser.ReduceContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionsCalculatorParser#reduce}.
	 * @param ctx the parse tree
	 */
	void exitReduce(FractionsCalculatorParser.ReduceContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionsCalculatorParser#read}.
	 * @param ctx the parse tree
	 */
	void enterRead(FractionsCalculatorParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionsCalculatorParser#read}.
	 * @param ctx the parse tree
	 */
	void exitRead(FractionsCalculatorParser.ReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(FractionsCalculatorParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(FractionsCalculatorParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprRead}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprRead(FractionsCalculatorParser.ExprReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprRead}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprRead(FractionsCalculatorParser.ExprReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParent(FractionsCalculatorParser.ExprParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParent(FractionsCalculatorParser.ExprParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInt(FractionsCalculatorParser.ExprIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInt(FractionsCalculatorParser.ExprIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMultDiv(FractionsCalculatorParser.ExprMultDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMultDiv(FractionsCalculatorParser.ExprMultDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprReduce}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprReduce(FractionsCalculatorParser.ExprReduceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprReduce}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprReduce(FractionsCalculatorParser.ExprReduceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprId(FractionsCalculatorParser.ExprIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprId(FractionsCalculatorParser.ExprIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprFr}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprFr(FractionsCalculatorParser.ExprFrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprFr}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprFr(FractionsCalculatorParser.ExprFrContext ctx);
}