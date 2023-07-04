// Generated from FracLang.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FracLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FracLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FracLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(FracLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link FracLangParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(FracLangParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link FracLangParser#display}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplay(FracLangParser.DisplayContext ctx);
	/**
	 * Visit a parse tree produced by {@link FracLangParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(FracLangParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprRead}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRead(FracLangParser.ExprReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprSumSub}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSumSub(FracLangParser.ExprSumSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParent(FracLangParser.ExprParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnary(FracLangParser.ExprUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultDiv(FracLangParser.ExprMultDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprReduce}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprReduce(FracLangParser.ExprReduceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprID(FracLangParser.ExprIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprFraction}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprFraction(FracLangParser.ExprFractionContext ctx);
}