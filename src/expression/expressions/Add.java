package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public class Add<T> extends BinaryOperationExpression<T> {

  public Add(TripleExpression<T> left, TripleExpression<T> right, ParsingType<T> operation) throws NullExpressionException {
    super(left, right, operation);
  }

  @Override
  protected T execute(T left, T right) throws EvaluatingException {
    return operation.add(left, right);
  }

}
