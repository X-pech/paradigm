package expression;

public class Divide extends BinaryOperation {

  public Divide(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  @Override
  protected int execute(int left, int right) {
    return left / right;
  }

}
