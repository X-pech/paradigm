package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public class Min<T> extends BinaryOperationExpression<T> {

  public Min(TripleExpression<T> left, TripleExpression<T> right, ParsingType<T> parsingType) throws NullExpressionException {
    super(left, right, parsingType);
  }

  protected T execute(T left, T right) throws EvaluatingException {
    return parsingType.min(left, right);
  }
}
