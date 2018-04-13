package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public class Max<T> extends BinaryOperationExpression<T> {

  public Max(TripleExpression<T> left, TripleExpression<T> right, ParsingType<T> parsingType) throws NullExpressionException {
    super(left, right, parsingType);
  }

  protected T execute(T left, T right) throws EvaluatingException {
    return parsingType.max(left, right);
  }
}
