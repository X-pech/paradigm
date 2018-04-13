package expression.types;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluatingException;

public class ParsingLong extends AbstractParsingType<Long> {

  public Long parse(String s) throws NumberFormatException {
    return Long.parseLong(s);
  }

  public Long fromInt(Integer v) {
    return (long) v;
  }

  public Long add(Long left, Long right) {
    return left + right;
  }

  public Long sub(Long left, Long right) {
    return left - right;
  }

  public Long mul(Long left, Long right) {
    return left * right;
  }

  public Long div(Long left, Long right) throws EvaluatingException {
    if (right == 0) {
      throw new DivisionByZeroException();
    }

    return left / right;
  }

  public Long neg(Long left) {
    return -left;
  }

  public Long count(Long v) {
    return (long) Long.bitCount(v);
  }

  public Long min(Long left, Long right) {
    if (left <= right) {
      return left;
    } else {
      return right;
    }
  }

  public Long max(Long left, Long right) {
    if (left >= right) {
      return left;
    } else {
      return right;
    }
  }

  public Long evaluate(Long v) {
    return v;
  }

  public Long zero() {
    return (long) 0;
  }
}
