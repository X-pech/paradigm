package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.LogarithmOperandIsNotPositiveException;

public class CheckedLogTen extends UnaryOperation {
  public CheckedLogTen(TripleExpression expression) {
    super(expression);
  }

  private void check(int expRes) throws EvaluatingException {
    if (expRes <= 0) {
      throw new LogarithmOperandIsNotPositiveException(expRes);
    }
  }

  @Override
  protected int execute(int expRes) throws EvaluatingException {
    check(expRes);
    return Integer.toString(expRes).length() - 1;
  }
}
