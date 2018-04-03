package expression.expressions;

import expression.exceptions.EvaluatingException;
import expression.types.ParsingType;

public class Variable<T> extends Operand<T> {

  private String name;

  public Variable(String name, ParsingType<T> operation) {
    super(operation);
    this.name = name;
  }

  public T evaluate(T x, T y, T z) throws EvaluatingException {
    switch (name) {
      case "x":
        return operation.evaluate(x);
      case "y":
        return operation.evaluate(y);
      case "z":
        return operation.evaluate(z);
      default:
        return null;
    }
  }
}
