package expression.exceptions;

public class IncorrectOpeningBracketException extends ParsingException {
  public IncorrectOpeningBracketException(String exp, int index) {
    super("Incorrect opening bracket at position: " + index + "\n" + exp + "\n" + emphasize(index, 0));
  }
}
