package expression.parser;

import expression.exceptions.ParsingException;
import expression.expressions.TripleExpression;

public interface Parser<T> {
  TripleExpression<T> parse(String expression) throws ParsingException;
}
