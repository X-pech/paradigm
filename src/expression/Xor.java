package expression;

public class Xor extends BinaryOperation {

  public Xor(TripleExpression left, TripleExpression right) {
    super(left, right);
  }

  public int execute(int left, int right) {
    return left ^ right;
  }

}
