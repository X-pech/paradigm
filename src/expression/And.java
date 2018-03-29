package expression;

public class And extends BinaryOperation {

  public And(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  public int execute(int left, int right) {
    return left & right;
  }

}
