package expression;

import expression.exceptions.BothPowOperandsAreZeroException;
import expression.exceptions.EvaluatingException;
import expression.exceptions.NegativePowerException;

public class CheckedPow extends BinaryOperation {
  public CheckedPow(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  private void check(int left, int right) throws EvaluatingException {
    if (right < 0) {
      throw new NegativePowerException(right);
    }

    if (left == 0 && right == 0) {
      throw new BothPowOperandsAreZeroException();
    }
  }

  private int powerRecursive(int left, int right) throws EvaluatingException {
    if (right == 1)
      return left;
    if (right == 0)
      return 1;

    int res = powerRecursive(left, right / 2);
    CheckedMultiply cnt = new CheckedMultiply(new Const(0), new Const(0));
    if (right % 2 == 0) {
      res = cnt.execute(res, res);
    } else {
      res = cnt.execute(cnt.execute(res, res), left);
    }
    return res;
  }

  protected int execute(int left, int right) throws EvaluatingException {
    check(left, right);
    return powerRecursive(left, right);
  }
}
