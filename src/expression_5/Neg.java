package expression_5;

public class Neg extends UnaryOperation {

  public Neg(UnitedExpression expression) {
    super(expression);
  }

  @Override
  protected int execute(int expRes) {
    return -1 * expRes;
  }

  @Override
  protected double execute(double expRes) {
    return -1.0 * expRes;
  }

}
