package expression.types;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class ParsingBigInteger extends AbstractParsingType<BigInteger> {

  public BigInteger parse(String s) {
    return new BigInteger(s);
  }

  @Override
  public BigInteger fromInt(Integer v) {
    return BigInteger.valueOf(v);
  }

  public BigInteger add(BigInteger left, BigInteger right) {
    return left.add(right);
  }

  public BigInteger sub(BigInteger left, BigInteger right) {
    return left.subtract(right);
  }

  public BigInteger mul(BigInteger left, BigInteger right) {
    return left.multiply(right);
  }

  public BigInteger div(BigInteger left, BigInteger right) throws DivisionByZeroException {
    if (right.equals(BigInteger.ZERO)) {
      throw new DivisionByZeroException();
    }
    return left.divide(right);
  }

  public BigInteger evaluate(BigInteger v) {
    return v;
  }

  public BigInteger neg(BigInteger left) {
    return left.negate();
  }

  public BigInteger count(BigInteger v) {
    return fromInt(v.bitCount());
  }

  public BigInteger min(BigInteger left, BigInteger right) {
    if (left.compareTo(right) <= 0)
      return left;
    else
      return right;
  }

  public BigInteger max(BigInteger left, BigInteger right) {
    if (left.compareTo(right) >= 0) {
      return left;
    } else {
      return right;
    }
  }

  public BigInteger zero() {
    return BigInteger.ZERO;
  }
}
