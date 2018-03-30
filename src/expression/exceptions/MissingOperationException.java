package expression.exceptions;

public class MissingOperationException extends ParsingException {
  public MissingOperationException(String exp, int index) {
    super("Missing operation at position: " + index + '\n' + exp + '\n' + emphasize(index, 1));
  }
}
