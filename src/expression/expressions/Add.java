package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.exceptions.NullExpressionException;
import expression.types.ParsingType;

public class Add<T> extends BinaryOperationExpression<T> {

  public Add(TripleExpression<T> left, TripleExpression<T> right, ParsingType<T> parsingType) throws NullExpressionException {
    super(left, right, parsingType);
  }

  @Override
  protected T execute(T left, T right) throws EvaluatingException {
    return parsingType.add(left, right);
  }

}
