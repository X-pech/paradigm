package expression;

public class Subtract extends BinaryOperation {

  public Subtract(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  @Override
  protected int execute(int left, int right) {
    return left - right;
  }

}
