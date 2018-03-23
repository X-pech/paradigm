package expression_5;

public class Multiply extends BinaryOperation {

  public Multiply(UnitedExpression left, UnitedExpression right) {
    super(left, right);
  }

  @Override
  protected int execute(int left, int right) {
    return left * right;
  }

  @Override
  protected double execute(double left, double right) {
    return left * right;
  }
}
