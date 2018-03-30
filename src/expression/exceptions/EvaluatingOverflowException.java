package expression.exceptions;

public class EvaluatingOverflowException extends EvaluatingException {
  public EvaluatingOverflowException(boolean isUnary, char op, int left, int right) {
    super("Operation causes overflow: " + (isUnary ? "" : left) + " " + op + " " + (isUnary ? left : right));
  }
}
