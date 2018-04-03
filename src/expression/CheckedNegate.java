package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.EvaluatingOverflowException;

public class CheckedNegate extends UnaryOperation {

  public CheckedNegate(TripleExpression expression) {
    super(expression);
  }

  private void check(int expRes) throws EvaluatingException {
    if (expRes == Integer.MIN_VALUE) {
      throw new EvaluatingOverflowException(true, "-", expRes, 0);
    }
  }

  @Override
  protected int execute(int expRes) throws EvaluatingException {
    check(expRes);
    return -1 * expRes;
  }

}
