package expression;

public abstract class BinaryOperation implements TripleExpression {
  private final TripleExpression left;
  private final TripleExpression right;

  public BinaryOperation(TripleExpression left, TripleExpression right) {
    assert left != null && right != null;
    this.left = left;
    this.right = right;
  }

  protected abstract int execute(int left, int right);

  public int evaluate(int x, int y, int z) {
    return execute(left.evaluate(x, y, z), right.evaluate(x, y, z));
  }

}
