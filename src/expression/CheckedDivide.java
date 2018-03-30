package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluatingException;
import expression.exceptions.EvaluatingOverflowException;

public class CheckedDivide extends BinaryOperation {

  public CheckedDivide(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  private void check(int left, int right) throws EvaluatingException {
    if (right == 0) {
      throw new DivisionByZeroException();
    }

    if (left == Integer.MIN_VALUE && right == -1) {
      throw new EvaluatingOverflowException(false, '/', left, right);
    }
  }

  @Override
  protected int execute(int left, int right) throws EvaluatingException {
    check(left, right);
    return left / right;
  }

}
