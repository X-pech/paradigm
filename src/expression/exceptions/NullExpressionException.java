package expression.exceptions;

public class NullExpressionException extends EvaluatingException {
  public NullExpressionException(boolean bin) {
    super("Null can't be a part of " + (bin ? "binary" : "unary") + " expression");
  }
}
