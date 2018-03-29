package expression;

public class Count extends UnaryOperation {
  public Count(TripleExpression expression) {
    super(expression);
  }

  public int execute(int expRes) {
    return Integer.bitCount(expRes);
  }
}
