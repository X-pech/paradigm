package expression.types;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluatingException;

public class ParsingUInt extends AbstractParsingType<Integer> {

  public Integer parse(String s) throws NumberFormatException {
    return Integer.parseInt(s);
  }

  public Integer fromInt(Integer v) {
    return v;
  }

  public Integer add(Integer left, Integer right) {
    return left + right;
  }

  public Integer sub(Integer left, Integer right) {
    return left - right;
  }

  public Integer mul(Integer left, Integer right) {
    return left * right;
  }

  public Integer div(Integer left, Integer right) throws EvaluatingException {
    if (right == 0) {
      throw new DivisionByZeroException();
    }

    return left / right;
  }

  public Integer neg(Integer left) {
    return -left;
  }

  public Integer count(Integer v) {
    return Integer.bitCount(v);
  }

  public Integer min(Integer left, Integer right) {
    if (left <= right) {
      return left;
    } else {
      return right;
    }
  }

  public Integer max(Integer left, Integer right) {
    if (left >= right) {
      return left;
    } else {
      return right;
    }
  }

  public Integer evaluate(Integer v) {
    return v;
  }

  public Integer zero() {
    return 0;
  }

}
