package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.types.ParsingType;

public class Variable<T> extends Operand<T> {

  private String name;

  public Variable(String name, ParsingType<T> parsingType) {
    super(parsingType);
    this.name = name;
  }

  public T evaluate(T x, T y, T z) throws EvaluatingException {
    switch (name) {
      case "x":
        return parsingType.evaluate(x);
      case "y":
        return parsingType.evaluate(y);
      case "z":
        return parsingType.evaluate(z);
      default:
        return null;
    }
  }
}
