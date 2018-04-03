package expression.exceptions;

public class NegativePowerException extends EvaluatingException {
  public NegativePowerException(int value) {
    super("Integer powers cannot be negative: " + value);
  }
}
