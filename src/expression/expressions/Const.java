package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.types.ParsingType;

public class Const<T> extends Operand<T> {
  private T value;

  public Const(T x, ParsingType<T> parsingType) {
    super(parsingType);
    value = x;
  }

  public T evaluate(T x, T y, T z) throws EvaluatingException {
    return parsingType.evaluate(value);
  }

}
