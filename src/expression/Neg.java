package expression;

public class Neg extends UnaryOperation {

  public Neg(TripleExpression expression) {
    super(expression);
  }

  @Override
  protected int execute(int expRes) {
    return -1 * expRes;
  }

}
