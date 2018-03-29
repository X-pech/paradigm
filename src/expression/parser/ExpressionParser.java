package expression.parser;

import expression.*;

import java.util.Arrays;
import java.util.List;

public class ExpressionParser implements Parser {

  private String expression;
  private int index = 0;
  private int constValue;
  private String varName;
  private List<String> variableNames = Arrays.asList("x", "y", "z");

  private enum Token {
    MUL,
    DIV,
    ADD,
    SUB,
    NEG,
    INT,
    OBR,
    CBR,
    VAR,
    ERR,
    NEU,
    AND,
    XOR,
    OR,
    NOT,
    BTC
  }

  private Token token = Token.ERR;

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

  private int parseCharToInt(char ch) {
    return ch - '0';
  }

  private void parseToken() {
    skipSpaces();
    if (index >= expression.length()) {
      token = Token.NEU;
      return;
    }
    char ch = expression.charAt(index);

    if (ch == '*') {
      token = Token.MUL;
    } else if (ch == '/') {
      token = Token.DIV;
    } else if (ch == '+') {
      token = Token.ADD;
    } else if (ch == '-') {
      if (token == Token.VAR || token == Token.INT || token == Token.CBR) {
        token = Token.SUB;
      } else {
        token = Token.NEG;
      }
    } else if (ch == '(') {
      token = Token.OBR;
    } else if (ch == ')') {
      token = Token.CBR;
    } else if (ch == '&') {
      token = Token.AND;
    } else if (ch == '^') {
      token = Token.XOR;
    } else if (ch == '|') {
      token = Token.OR;
    } else if (ch == '~') {
      token = Token.NOT;
    } else if (Character.isDigit(ch)) {
      constValue = 0;
      while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
        constValue = constValue * 10 + parseCharToInt(expression.charAt(index));
        index++;
      }
      token = Token.INT;
      index--;
    } else if (Character.isAlphabetic(ch)) {
      StringBuilder tokenSB = new StringBuilder();
      while (index < expression.length() && Character.isAlphabetic(expression.charAt(index))) {
        tokenSB.append(expression.charAt(index));
        index++;
      }
      String tokenStr = tokenSB.toString();
      if (tokenStr.equals("count")) {
        token = Token.BTC;
      } else if (checkVariableName(tokenStr)) {
        token = Token.VAR;
        varName = tokenStr;
      } else {
        token = Token.ERR;
      }
      index--;
    }
    index++;
  }

  private TripleExpression lowestPriority() {
    return or();
  }

  private TripleExpression unary() {
    parseToken();
    TripleExpression res;
    switch (token) {
      case INT:
        res = new Const(constValue);
        parseToken();
        break;
      case VAR:
        res = new Variable(varName);
        parseToken();
        break;
      case NEG:
        res = new Neg(unary());
        break;
      case NOT:
        res = new Not(unary());
        break;
      case BTC:
        res = new Count(unary());
        break;
      case OBR:
        res = lowestPriority();
        parseToken();
        break;
      default:
        return new Const(0);
    }
    return res;
  }

  private TripleExpression binMul() {
    TripleExpression res = unary();
    while (true) {
      switch (token) {
        case MUL:
          res = new Multiply(res, unary());
          break;
        case DIV:
          res = new Divide(res, unary());
          break;
        default:
          return res;
      }
    }
  }

  private TripleExpression binAdd() {
    TripleExpression res = binMul();
    while (true) {
      switch (token) {
        case ADD:
          res = new Add(res, binMul());
          break;
        case SUB:
          res = new Subtract(res, binMul());
          break;
        default:
          return res;
      }
    }
  }

  private TripleExpression and() {
    TripleExpression res = binAdd();
    while (true) {
      switch (token) {
        case AND:
          res = new And(res, binAdd());
          break;
        default:
          return res;
      }
    }
  }

  private TripleExpression xor() {
    TripleExpression res = and();
    while (true) {
      switch (token) {
        case XOR:
          res = new Xor(res, and());
          break;
        default:
          return res;
      }
    }
  }

  private TripleExpression or() {
    TripleExpression res = xor();
    while (true) {
      switch (token) {
        case OR:
          res = new Or(res, xor());
          break;
        default:
          return res;
      }
    }
  }

  public TripleExpression parse(String expression) {
    index = 0;
    this.expression = expression;
    token = Token.NEU;
    return lowestPriority();
  }


}
