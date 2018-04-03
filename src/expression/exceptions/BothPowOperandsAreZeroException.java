package expression.exceptions;

public class BothPowOperandsAreZeroException extends EvaluatingException {
  public BothPowOperandsAreZeroException() {
    super("Both pow operand cannot be zeroes.");
  }
}
