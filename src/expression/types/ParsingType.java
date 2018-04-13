package expression.types;

import expression.exceptions.EvaluatingException;

import java.lang.reflect.Type;

public interface ParsingType<T> {
  T parse(String s) throws NumberFormatException;

  T fromInt(Integer v);

  T evaluate(T v) throws EvaluatingException;

  T add(T left, T right) throws EvaluatingException;

  T sub(T left, T right) throws EvaluatingException;

  T mul(T left, T right) throws EvaluatingException;

  T div(T left, T right) throws EvaluatingException;

  T neg(T left) throws EvaluatingException;

  T count(T v) throws EvaluatingException;

  T min(T left, T right) throws EvaluatingException;

  T max(T left, T right) throws EvaluatingException;

  Type getType();

  T zero();
}
