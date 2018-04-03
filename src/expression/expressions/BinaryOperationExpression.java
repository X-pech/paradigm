package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public abstract class BinaryOperationExpression<T> implements TripleExpression<T> {
  private final TripleExpression<T> left;
  private final TripleExpression<T> right;
  protected final ParsingType<T> operation;

  public BinaryOperationExpression(TripleExpression<T> left, TripleExpression<T> right, ParsingType<T> operation)
      throws NullExpressionException {
    if (left == null || right == null) {
      throw new NullExpressionException(true);
    }
    this.left = left;
    this.right = right;
    this.operation = operation;
  }

  protected abstract T execute(T left, T right) throws EvaluatingException;

  public T evaluate(T x, T y, T z) throws EvaluatingException {
    return execute(left.evaluate(x, y, z), right.evaluate(x, y, z));
  }

}
