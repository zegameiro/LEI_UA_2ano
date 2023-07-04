// Generated from CalComplex.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalComplexParser}.
 */
public interface CalComplexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalComplexParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CalComplexParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalComplexParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CalComplexParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(CalComplexParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(CalComplexParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalComplexParser#output}.
	 * @param ctx the parse tree
	 */
	void enterOutput(CalComplexParser.OutputContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalComplexParser#output}.
	 * @param ctx the parse tree
	 */
	void exitOutput(CalComplexParser.OutputContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalComplexParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(CalComplexParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalComplexParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(CalComplexParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(CalComplexParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(CalComplexParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprUnary(CalComplexParser.ExprUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprUnary(CalComplexParser.ExprUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNumber(CalComplexParser.ExprNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNumber(CalComplexParser.ExprNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMultDiv(CalComplexParser.ExprMultDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMultDiv(CalComplexParser.ExprMultDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprComplex}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprComplex(CalComplexParser.ExprComplexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprComplex}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprComplex(CalComplexParser.ExprComplexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParenteses}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParenteses(CalComplexParser.ExprParentesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParenteses}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParenteses(CalComplexParser.ExprParentesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprID(CalComplexParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprID(CalComplexParser.ExprIDContext ctx);
}