package expression.types;

public class ParsingDouble extends AbstractParsingType<Double> {

  public Double parse(String s) throws NumberFormatException {
    return Double.parseDouble(s);
  }

  public Double fromInt(Integer v) {
    return (double) v;
  }

  public Double add(Double left, Double right) {
    return left + right;
  }

  public Double sub(Double left, Double right) {
    return left - right;
  }

  public Double mul(Double left, Double right) {
    return left * right;
  }

  public Double div(Double left, Double right) {
    return left / right;
  }

  public Double neg(Double left) {
    return -left;
  }

  public Double count(Double v) {
    return (double) (Long.bitCount(Double.doubleToLongBits(v)));
  }

  public Double min(Double left, Double right) {
    if (left - right < 0) {
      return left;
    } else {
      return right;
    }
  }

  public Double max(Double left, Double right) {
    if (left - right > 0) {
      return left;
    } else {
      return right;
    }
  }

  public Double evaluate(Double v) {
    return v;
  }

  public Double zero() {
    return 0.0;
  }
}
