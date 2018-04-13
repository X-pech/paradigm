package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public class Count<T> extends UnaryOperationExpression<T> {

  public Count(TripleExpression<T> expression, ParsingType<T> parsingType) throws NullExpressionException {
    super(expression, parsingType);
  }

  protected T execute(T expRes) throws EvaluatingException {
    return parsingType.count(expRes);
  }
}
