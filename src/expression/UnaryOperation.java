package expression;

import expression.exceptions.EvaluatingException;

public abstract class UnaryOperation implements TripleExpression {
  private final TripleExpression expression;

  public UnaryOperation(TripleExpression expression) {
    assert expression != null;
    this.expression = expression;
  }

  public TripleExpression getExpression() {
    return expression;
  }

  protected abstract int execute(int expRes) throws EvaluatingException;

  public int evaluate(int x, int y, int z) throws EvaluatingException {
    return execute(expression.evaluate(x, y, z));
  }
}
