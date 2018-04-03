package expression.expressions;

import expression.types.ParsingType;

public abstract class Operand<T> implements TripleExpression<T> {
  protected final ParsingType<T> operation;

  public Operand(ParsingType<T> operation) {
    this.operation = operation;
  }
}
