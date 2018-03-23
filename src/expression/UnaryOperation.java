package expression;

public abstract class UnaryOperation implements TripleExpression {
  private final TripleExpression expression;

  public UnaryOperation(TripleExpression expression) {
    assert expression != null;
    this.expression = expression;
  }

  protected abstract int execute(int expRes);

  public int evaluate(int x, int y, int z) {
    return execute(expression.evaluate(x, y, z));
  }
}
