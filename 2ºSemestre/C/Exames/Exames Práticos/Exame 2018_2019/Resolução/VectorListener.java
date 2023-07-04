// Generated from Vector.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link VectorParser}.
 */
public interface VectorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link VectorParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(VectorParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(VectorParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link VectorParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(VectorParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(VectorParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link VectorParser#show}.
	 * @param ctx the parse tree
	 */
	void enterShow(VectorParser.ShowContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#show}.
	 * @param ctx the parse tree
	 */
	void exitShow(VectorParser.ShowContext ctx);
	/**
	 * Enter a parse tree produced by {@link VectorParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(VectorParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(VectorParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprVector}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprVector(VectorParser.ExprVectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprVector}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprVector(VectorParser.ExprVectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(VectorParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(VectorParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprUnary(VectorParser.ExprUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprUnary(VectorParser.ExprUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprNumber(VectorParser.ExprNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprNumber(VectorParser.ExprNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMults}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprMults(VectorParser.ExprMultsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMults}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprMults(VectorParser.ExprMultsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParenteses}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprParenteses(VectorParser.ExprParentesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParenteses}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprParenteses(VectorParser.ExprParentesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprID(VectorParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprID(VectorParser.ExprIDContext ctx);
}