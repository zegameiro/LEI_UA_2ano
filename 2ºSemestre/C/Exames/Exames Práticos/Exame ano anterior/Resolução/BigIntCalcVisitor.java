// Generated from BigIntCalc.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BigIntCalcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BigIntCalcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BigIntCalcParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(BigIntCalcParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(BigIntCalcParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigIntCalcParser#show}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow(BigIntCalcParser.ShowContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigIntCalcParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(BigIntCalcParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(BigIntCalcParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnary(BigIntCalcParser.ExprUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumber(BigIntCalcParser.ExprNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultDiv(BigIntCalcParser.ExprMultDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParenteses}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParenteses(BigIntCalcParser.ExprParentesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprID(BigIntCalcParser.ExprIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMod}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMod(BigIntCalcParser.ExprModContext ctx);
}