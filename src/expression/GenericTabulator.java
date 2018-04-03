package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;
import expression.expressions.TripleExpression;
import expression.parser.ExpressionParser;
import expression.types.ParsingBigInteger;
import expression.types.ParsingDouble;
import expression.types.ParsingInteger;
import expression.types.ParsingType;

public class GenericTabulator implements Tabulator {
  public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
    return tabulate(getParsingType(mode), expression, x1, x2, y1, y2, z1, z2);
  }

  ParsingType<?> getParsingType(String mode) {
    switch (mode) {
      case "i":
        return new ParsingInteger();
      case "d":
        return new ParsingDouble();
      case "bi":
        return new ParsingBigInteger();
      default:
        return null;
    }
  }

  private <T> Object[][][] tabulate(ParsingType<T> pt, String exp, int x1, int x2, int y1, int y2, int z1, int z2) {
    Object[][][] answer = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];

    ExpressionParser<T> parser = new ExpressionParser<>(pt);
    TripleExpression<T> expression;
    try {
      expression = parser.parse(exp);
    } catch (ParsingException e) {
      return answer;
    }

    for (int x = 0; x <= x2 - x1; x++) {
      for (int y = 0; y <= y2 - y1; y++) {
        for (int z = 0; z <= z2 - z1; z++) {
          try {
            answer[x][y][z] = expression.evaluate(pt.fromInt(x1 + x), pt.fromInt(y1 + y), pt.fromInt(z1 + z));
          } catch (EvaluatingException e) {
            answer[x][y][z] = null;
          }
        }
      }
    }

    return answer;
  }

}
