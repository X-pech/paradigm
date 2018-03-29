package expression;

public class Not extends UnaryOperation {

  public Not(TripleExpression expression) {
    super(expression);
  }

  public int execute(int expRes) {
    return ~expRes;
  }

}
