package expression.parser;

import expression.expressions.TripleExpression;
import expression.exceptions.ParsingException;
import expression.types.ParsingType;

public interface Parser<T> {
  TripleExpression<T> parse(String expression) throws ParsingException;
}
