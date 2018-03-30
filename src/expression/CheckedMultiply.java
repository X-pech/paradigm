package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.EvaluatingOverflowException;

public class CheckedMultiply extends BinaryOperation {

  public CheckedMultiply(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  private void check(int left, int right) throws EvaluatingException {
    if ((right < 0 && left < 0 && Integer.MAX_VALUE / right > left) ||
        (right > 0 && left > 0 && Integer.MAX_VALUE / right < left) ||
        (right > 0 && left < 0 && Integer.MIN_VALUE / right > left) ||
        (right < 0 && left > 0 && Integer.MIN_VALUE / left > right)) {
      throw new EvaluatingOverflowException(false, '*', left, right);
    }
  }

  @Override
  protected int execute(int left, int right) throws EvaluatingException {
    check(left, right);
    return left * right;
  }

}
