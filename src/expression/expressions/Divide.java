package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public class Divide<T> extends BinaryOperationExpression<T> {

  public Divide(TripleExpression<T> left, TripleExpression<T> right, ParsingType<T> operation) throws NullExpressionException {
    super(left, right, operation);
  }

  @Override
  protected T execute(T left, T right) throws EvaluatingException {
    return operation.div(left, right);
  }

}
