package expression.expressions;

import expression.types.ParsingType;

public abstract class Operand<T> implements TripleExpression<T> {
  protected final ParsingType<T> parsingType;

  public Operand(ParsingType<T> parsingType) {
    this.parsingType = parsingType;
  }
}
