package expression_5;

public abstract class BinaryOperation implements UnitedExpression {
  private final UnitedExpression left;
  private final UnitedExpression right;

  public BinaryOperation(UnitedExpression left, UnitedExpression right) {
    assert left != null && right != null;
    this.left = left;
    this.right = right;
  }

  protected abstract int execute(int left, int right);

  protected abstract double execute(double left, double right);

  public int evaluate(int x) {
    return execute(left.evaluate(x), right.evaluate(x));
  }

  public double evaluate(double x) {
    return execute(left.evaluate(x), right.evaluate(x));
  }

  public int evaluate(int x, int y, int z) {
    return execute(left.evaluate(x, y, z), right.evaluate(x, y, z));
  }

}
