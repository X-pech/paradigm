package expression.exceptions;

public class LogarithmBaseEqualsOneException extends EvaluatingException {
  public LogarithmBaseEqualsOneException() {
    super("Logarithm base cannot be 1.");
  }
}
