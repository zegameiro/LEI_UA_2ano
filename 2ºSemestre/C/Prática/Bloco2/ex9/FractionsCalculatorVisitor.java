// Generated from FractionsCalculator.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FractionsCalculatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FractionsCalculatorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FractionsCalculatorParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(FractionsCalculatorParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link FractionsCalculatorParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(FractionsCalculatorParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link FractionsCalculatorParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(FractionsCalculatorParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link FractionsCalculatorParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(FractionsCalculatorParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link FractionsCalculatorParser#reduce}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduce(FractionsCalculatorParser.ReduceContext ctx);
	/**
	 * Visit a parse tree produced by {@link FractionsCalculatorParser#read}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(FractionsCalculatorParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(FractionsCalculatorParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprRead}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRead(FractionsCalculatorParser.ExprReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParent(FractionsCalculatorParser.ExprParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInt(FractionsCalculatorParser.ExprIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultDiv(FractionsCalculatorParser.ExprMultDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprReduce}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprReduce(FractionsCalculatorParser.ExprReduceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprId(FractionsCalculatorParser.ExprIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprFr}
	 * labeled alternative in {@link FractionsCalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprFr(FractionsCalculatorParser.ExprFrContext ctx);
}