package expression.exceptions;

public class LogarithmOperandIsNotPositiveException extends EvaluatingException {
  public LogarithmOperandIsNotPositiveException(int value) {
    super("Logarithms cannot be calculated from non-positive value: " + value);
  }
}
