package expression;

public class Or extends BinaryOperation {

  public Or(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  public int execute(int left, int right) {
    return left | right;
  }

}
