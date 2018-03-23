package expression_5;

public abstract class UnaryOperation implements UnitedExpression {
  private final UnitedExpression expression;

  public UnaryOperation(UnitedExpression expression) {
    assert expression != null;
    this.expression = expression;
  }

  protected abstract int execute(int expRes);

  protected abstract double execute(double expRes);

  public int evaluate(int x) {
    return execute(expression.evaluate(x));
  }

  public double evaluate(double x) {
    return execute(expression.evaluate(x));
  }

  public int evaluate(int x, int y, int z) {
    return execute(expression.evaluate(x, y, z));
  }
}
