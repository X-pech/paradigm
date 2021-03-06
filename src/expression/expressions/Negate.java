package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public class Negate<T> extends UnaryOperationExpression<T> {

  public Negate(TripleExpression<T> expression, ParsingType<T> parsingType) throws NullExpressionException {
    super(expression, parsingType);
  }

  @Override
  protected T execute(T expRes) throws EvaluatingException {
    return parsingType.neg(expRes);
  }

}
