package expression;

public class Add extends BinaryOperation {

  public Add(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  @Override
  protected int execute(int left, int right) {
    return left + right;
  }

}
