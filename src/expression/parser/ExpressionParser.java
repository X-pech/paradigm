package expression.parser;

import expression.exceptions.*;
import expression.expressions.*;
import expression.types.ParsingType;

import java.util.Arrays;
import java.util.List;


public class ExpressionParser<T> implements Parser<T> {

  private ParsingType<T> parsingType;
  private String expression;
  private int index = 0;
  private int balance = 0;
  private T constValue;
  private String varName;
  private List<String> variableNames = Arrays.asList("x", "y", "z");

  private enum Token {
    MUL,
    DIV,
    ADD,
    SUB,
    NEG,
    CST,
    OBR,
    CBR,
    VAR,
    INV,
    NEU,
    CNT,
    MIN,
    MAX
  }

  private Token token = Token.INV;

  private boolean checkVariableName(String token) {
    boolean res = false;
    for (String s : variableNames) {
      if (s.equals(token)) {
        res = true;
        break;
      }
    }
    return res;
  }

  private void skipSpaces() {
    while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
      index++;
    }
  }

  private void setToken(Token t) {
    token = t;
  }

  private void parseConst(boolean negate) throws ParsingException {
    StringBuilder constSB = new StringBuilder();
    if (negate) {
      constSB.append('-');
    }
    while (index < expression.length() && (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')) {
      constSB.append(expression.charAt(index));
      index++;
    }
    constValue = null;
    try {
      constValue = parsingType.parse(constSB.toString());
      setToken(Token.CST);
    } catch (NumberFormatException e) {
      throw new ParsingOverflowException(expression, index);
    }
    index--;
  }


  private void parseToken() throws ParsingException {
    skipSpaces();
    if (index >= expression.length()) {
      if (token != Token.NEU)
        setToken(Token.NEU);
      return;
    }
    char ch = expression.charAt(index);

    if (ch == '*') {
      setToken(Token.MUL);
    } else if (ch == '/') {
      setToken(Token.DIV);
    } else if (ch == '+') {
      setToken(Token.ADD);
    } else if (ch == '(') {
      setToken(Token.OBR);
    } else if (ch == ')') {
      setToken(Token.CBR);
    } else if (ch == '-') {
      if (token == Token.VAR || token == Token.CST || token == Token.CBR) {
        setToken(Token.SUB);
      } else {
        index++;
        skipSpaces();
        if (index < expression.length() && Character.isDigit(expression.charAt(index))) {
          parseConst(true);
        } else {
          setToken(Token.NEG);
          index--;
        }
      }
    } else if (Character.isDigit(ch)) {
      parseConst(false);
    } else if (Character.isAlphabetic(ch)) {
      StringBuilder tokenSB = new StringBuilder();
      while (index < expression.length() && Character.isLetterOrDigit(expression.charAt(index))) {
        tokenSB.append(expression.charAt(index));
        index++;
      }
      String tokenStr = tokenSB.toString();
      if (tokenStr.equals("count")) {
        setToken(Token.CNT);
      } else if (tokenStr.equals("min")) {
        setToken(Token.MIN);
      } else if (tokenStr.equals("max")) {
        setToken(Token.MAX);
      } else if (checkVariableName(tokenStr)) {
        setToken(Token.VAR);
        varName = tokenStr;
      } else {
        setToken(Token.INV);
      }
      index--;
    } else {
      setToken(Token.INV);
    }
    index++;
  }

  private boolean isOperation(Token t) {
    return (t == Token.ADD || t == Token.SUB || t == Token.MUL || t == Token.DIV || t == Token.NEG || t == Token.MIN
        || token == Token.MAX || token == Token.CNT);
  }

  private boolean isNeutral(Token t) {
    return (t == Token.NEU);
  }

  private boolean isOpeningParethesis(Token t) {
    return (t == Token.OBR);
  }

  private boolean checkLowest() {
    return (isOpeningParethesis(token) || isOperation(token) || isNeutral(token));
  }

  private TripleExpression<T> lowestPriority() throws ParsingException {
    if (checkLowest()) {
      return minMax();
    } else {
      throw new MissingOperationException(expression, index);
    }
  }

  private TripleExpression<T> unary() throws ParsingException {
    parseToken();
    TripleExpression<T> res;
    switch (token) {
      case CST:
        res = new Const<>(constValue, parsingType);
        parseToken();
        break;
      case VAR:
        res = new Variable<>(varName, parsingType);
        parseToken();
        break;
      case NEG:
        try {
          res = new Negate<>(unary(), parsingType);
        } catch (NullExpressionException e) {
          throw new MissingOperandException(expression, index);
        }
        break;
      case CNT:
        try {
          res = new Count<>(unary(), parsingType);
        } catch (NullExpressionException e) {
          throw new MissingOperationException(expression, index);
        }
        break;
      case OBR:
        balance++;
        res = lowestPriority();
        if (token != Token.CBR) {
          throw new MissingClosingBracketException(expression, index);
        }
        parseToken();
        break;
      case CBR:
        if (balance == 0) {
          throw new MissingOpeningBracketException(expression, index);
        }
        balance--;
        res = null;
        break;
      case INV:
        throw new IncorrectSymbolException(expression, index);
      default:
        res = null;
    }
    return res;
  }

  private TripleExpression<T> binMul() throws ParsingException {
    TripleExpression<T> res = unary();
    while (true) {
      try {
        switch (token) {
          case MUL:
            res = new Multiply<>(res, unary(), parsingType);
            break;
          case DIV:
            res = new Divide<>(res, unary(), parsingType);
            break;
          default:
            return res;
        }
      } catch (NullExpressionException e) {
        throw new MissingOperandException(expression, index);
      }
    }
  }

  private TripleExpression<T> binAdd() throws ParsingException {
    TripleExpression<T> res = binMul();
    while (true) {
      try {
        switch (token) {
          case ADD:
            res = new Add<>(res, binMul(), parsingType);
            break;
          case SUB:
            res = new Subtract<>(res, binMul(), parsingType);
            break;
          default:
            return res;
        }
      } catch (NullExpressionException e) {
        throw new MissingOperandException(expression, index);
      }
    }
  }

  private TripleExpression<T> minMax() throws ParsingException {
    TripleExpression<T> res = binAdd();
    while (true) {
      try {
        switch (token) {
          case MIN:
            res = new Min<>(res, binAdd(), parsingType);
            break;
          case MAX:
            res = new Max<>(res, binAdd(), parsingType);
            break;
          default:
            return res;
        }
      } catch (NullExpressionException e) {
        throw new MissingOperandException(expression, index);
      }

    }
  }

  public TripleExpression<T> parse(String expression) throws ParsingException {
    index = balance = 0;
    this.expression = expression;
    token = Token.NEU;
    return lowestPriority();
  }

  public ExpressionParser(ParsingType<T> parsingType) {
    this.parsingType = parsingType;
  }

}
