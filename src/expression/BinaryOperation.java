package expression;

import expression.exceptions.EvaluatingException;

public abstract class BinaryOperation implements TripleExpression {
  private final TripleExpression left;
  private final TripleExpression right;

  public BinaryOperation(TripleExpression left, TripleExpression right) {
    this.left = left;
    this.right = right;
  }

  protected abstract int execute(int left, int right) throws EvaluatingException;

  public int evaluate(int x, int y, int z) throws EvaluatingException {
    return execute(left.evaluate(x, y, z), right.evaluate(x, y, z));
  }

}
