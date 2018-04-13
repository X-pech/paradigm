package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public abstract class UnaryOperationExpression<T> implements TripleExpression<T> {
  private final TripleExpression<T> expression;
  protected final ParsingType<T> parsingType;

  public UnaryOperationExpression(TripleExpression<T> expression, ParsingType<T> parsingType)
      throws NullExpressionException {
    if (expression == null) {
      throw new NullExpressionException(false);
    }
    this.expression = expression;
    this.parsingType = parsingType;
  }

  public TripleExpression<T> getExpression() {
    return expression;
  }

  protected abstract T execute(T expRes) throws EvaluatingException;

  public T evaluate(T x, T y, T z) throws EvaluatingException {
    return execute(expression.evaluate(x, y, z));
  }
}
