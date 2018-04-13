package expression.types;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluatingException;

public class ParsingShort extends AbstractParsingType<Short> {
  public Short parse(String s) throws NumberFormatException {
    return Short.parseShort(s);
  }

  public Short fromInt(Integer v) {
    return (short) (int) (v);
  }

  public Short evaluate(Short v) {
    return v;
  }

  public Short add(Short left, Short right) {
    return (short) (left + right);
  }

  public Short sub(Short left, Short right) {
    return (short) (left - right);
  }

  public Short mul(Short left, Short right) {
    return (short) (left * right);
  }

  public Short div(Short left, Short right) throws EvaluatingException {
    if (right == 0) {
      throw new DivisionByZeroException();
    }

    return (short) (left / right);
  }

  public Short neg(Short left) {
    return (short) (-left);
  }

  public Short count(Short v) {
    return (short) Integer.bitCount(Short.toUnsignedInt(v));
  }

  public Short min(Short left, Short right) {
    if (left <= right) {
      return left;
    } else {
      return right;
    }
  }

  public Short max(Short left, Short right) {
    if (left >= right) {
      return left;
    } else {
      return right;
    }
  }

  public Short zero() {
    return 0;
  }
}
