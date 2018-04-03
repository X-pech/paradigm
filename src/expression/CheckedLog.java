package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.LogarithmBaseEqualsOneException;
import expression.exceptions.LogarithmOperandIsNotPositiveException;

public class CheckedLog extends BinaryOperation {
  public CheckedLog(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  private void check(int left, int right) throws EvaluatingException {
    if (right == 1) {
      throw new LogarithmBaseEqualsOneException();
    }
    if (right <= 0 || left <= 0) {
      throw new LogarithmOperandIsNotPositiveException(right);
    }
  }

  protected int execute(int left, int right) throws EvaluatingException {
    check(left, right);
    int res = 0;
    while (left >= right) {
      left /= right;
      res++;
    }
    return res;
  }
}
