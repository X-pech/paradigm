package expression_5;

public class Add extends BinaryOperation {

  public Add(UnitedExpression left, UnitedExpression right) {
    super(left, right);
  }

  @Override
  protected int execute(int left, int right) {
    return left + right;
  }

  @Override
  protected double execute(double left, double right) {
    return left + right;
  }

}
