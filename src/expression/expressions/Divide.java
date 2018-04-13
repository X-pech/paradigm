package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public class Divide<T> extends BinaryOperationExpression<T> {

  public Divide(TripleExpression<T> left, TripleExpression<T> right, ParsingType<T> parsingType) throws NullExpressionException {
    super(left, right, parsingType);
  }

  @Override
  protected T execute(T left, T right) throws EvaluatingException {
    return parsingType.div(left, right);
  }

}
