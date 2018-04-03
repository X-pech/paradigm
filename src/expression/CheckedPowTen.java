package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.EvaluatingOverflowException;
import expression.exceptions.NegativePowerException;

public class CheckedPowTen extends UnaryOperation {
  public CheckedPowTen(TripleExpression expression) {
    super(expression);
  }

  private void check(int expRes) throws EvaluatingException {
    if (expRes >= Integer.toString(Integer.MAX_VALUE).length()) {
      throw new EvaluatingOverflowException(false, "pow10", expRes, 0);
    }

    if (expRes < 0) {
      throw new NegativePowerException(expRes);
    }
  }

  @Override
  protected int execute(int expRes) throws EvaluatingException {
    check(expRes);
    int res = 1;
    while (expRes > 0) {
      res *= 10;
      expRes--;
    }
    return res;
  }
}
