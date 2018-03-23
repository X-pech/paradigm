package expression;

public class Multiply extends BinaryOperation {

  public Multiply(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  @Override
  protected int execute(int left, int right) {
    return left * right;
  }

}
