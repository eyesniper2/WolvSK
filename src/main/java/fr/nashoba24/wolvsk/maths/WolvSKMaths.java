package fr.nashoba24.wolvsk.maths;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class WolvSKMaths {

	public static void register() {
		Skript.registerExpression(ExprMathsAbsoluteValue.class, Double.class, ExpressionType.PROPERTY, "abs[olute] [value of ]%number%");
		Skript.registerExpression(ExprMathsCubeRoot.class, Double.class, ExpressionType.PROPERTY, "cube root of %number%", "cbrt[ of] %number%");
		Skript.registerExpression(ExprMathsRadToDegrees.class, Double.class, ExpressionType.PROPERTY, "%number% rad[ian[s]][ converted] (to|in) deg[ree[s]]");
		Skript.registerExpression(ExprMathsDegreesToRad.class, Double.class, ExpressionType.PROPERTY, "%number% deg[ree[s]][ converted] (to|in) rad[ian[s]]");
		Skript.registerExpression(ExprMathsExp.class, Double.class, ExpressionType.PROPERTY, "exp[onential][ of] %number%");
		Skript.registerExpression(ExprMathsExpBase.class, Double.class, ExpressionType.PROPERTY, "exp[onential] %number% in base %number%", "exp[onential] in base %number% of %number%");
		Skript.registerExpression(ExprMathsLogBase.class, Double.class, ExpressionType.PROPERTY, "log[arithm] %number% in base %number%", "log[arithm] in base %number% of %number%");
		Skript.registerExpression(ExprMathsPi.class, Double.class, ExpressionType.PROPERTY, "pi");
		Skript.registerExpression(ExprMathsStatsAverage.class, Double.class, ExpressionType.PROPERTY, "average of %numbers%");
		Skript.registerExpression(ExprMathsStatsMedian.class, Double.class, ExpressionType.PROPERTY, "median of %numbers%");
		Skript.registerExpression(ExprMathsPi.class, Double.class, ExpressionType.PROPERTY, "speed of[ the] light", "light['s] speed");
	}
	
}
