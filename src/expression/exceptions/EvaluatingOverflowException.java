package expression.exceptions;

public class EvaluatingOverflowException extends EvaluatingException {
  public EvaluatingOverflowException(boolean isUnary, String op, int left, int right) {
    super("ParsingType causes overflow: " + (isUnary ? "" : left) + " " + op + " " + (isUnary ? left : right));
  }
}
