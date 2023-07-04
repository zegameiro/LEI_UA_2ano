// Generated from Vector.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link VectorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface VectorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link VectorParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(VectorParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link VectorParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(VectorParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link VectorParser#show}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow(VectorParser.ShowContext ctx);
	/**
	 * Visit a parse tree produced by {@link VectorParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(VectorParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprVector}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprVector(VectorParser.ExprVectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(VectorParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnary(VectorParser.ExprUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumber(VectorParser.ExprNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMults}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMults(VectorParser.ExprMultsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParenteses}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParenteses(VectorParser.ExprParentesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprID(VectorParser.ExprIDContext ctx);
}