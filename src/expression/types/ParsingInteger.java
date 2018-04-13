package expression.types;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluatingException;
import expression.exceptions.EvaluatingOverflowException;

public class ParsingInteger extends AbstractParsingType<Integer> {
  public Integer parse(String s) throws NumberFormatException {
    return Integer.parseInt(s);
  }

  @Override
  public Integer fromInt(Integer v) {
    return v;
  }

  public Integer add(Integer left, Integer right) throws EvaluatingException {
    if (right > 0 && Integer.MAX_VALUE - right < left || right < 0 && Integer.MIN_VALUE - right > left) {
      throw new EvaluatingOverflowException(false, "+", left, right);
    }
    return left + right;
  }

  public Integer sub(Integer left, Integer right) throws EvaluatingException {
    if (right > 0 && Integer.MIN_VALUE + right > left || right < 0 && Integer.MAX_VALUE + right < left) {
      throw new EvaluatingOverflowException(false, "-", left, right);
    }
    return left - right;
  }

  public Integer mul(Integer left, Integer right) throws EvaluatingException {
    if ((right < 0 && left < 0 && Integer.MAX_VALUE / right > left) ||
        (right > 0 && left > 0 && Integer.MAX_VALUE / right < left) ||
        (right > 0 && left < 0 && Integer.MIN_VALUE / right > left) ||
        (right < 0 && left > 0 && Integer.MIN_VALUE / left > right)) {
      throw new EvaluatingOverflowException(false, "*", left, right);

    }
    return left * right;
  }

  public Integer div(Integer left, Integer right) throws EvaluatingException {
    if (right == 0) {
      throw new DivisionByZeroException();
    }

    if (left == Integer.MIN_VALUE && right == -1) {
      throw new EvaluatingOverflowException(false, "/", left, right);
    }

    return left / right;
  }

  public Integer neg(Integer left) throws EvaluatingException {
    if (left == Integer.MIN_VALUE) {
      throw new EvaluatingOverflowException(true, "-", left, 0);
    }
    return -left;
  }

  public Integer count(Integer v) throws EvaluatingException {
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
