package expression;

public class Const implements TripleExpression {
  private double value;

  public Const(int x) {
    value = x;
  }

  public int evaluate(int x, int y, int z) {
    return (int) value;
  }

}
