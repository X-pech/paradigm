package expression_5;

public class Variable implements UnitedExpression {

  private String name;

  public Variable(String name) {
    assert name != null;
    assert name.equals("x") || name.equals("y") || name.equals("z");
    this.name = name;
  }

  public int evaluate(int x) {
    return x;
  }

  public double evaluate(double x) {
    return x;
  }

  public int evaluate(int x, int y, int z) {

    switch (name) {
      case "x":
        return x;
      case "y":
        return y;
      case "z":
        return z;
      default:
        return 0;
    }
  }
}
