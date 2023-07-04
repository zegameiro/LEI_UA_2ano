// Generated from CalComplex.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalComplexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalComplexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalComplexParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CalComplexParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalComplexParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(CalComplexParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalComplexParser#output}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutput(CalComplexParser.OutputContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalComplexParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(CalComplexParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(CalComplexParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnary(CalComplexParser.ExprUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumber(CalComplexParser.ExprNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultDiv(CalComplexParser.ExprMultDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprComplex}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprComplex(CalComplexParser.ExprComplexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParenteses}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParenteses(CalComplexParser.ExprParentesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprID(CalComplexParser.ExprIDContext ctx);
}