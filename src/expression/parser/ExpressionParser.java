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
    NEU
  }

  private Token token = Token.ERR;

  private boolean checkVariableName() {
    boolean res = false;
    for (String s : variableNames) {
      if (s.equals(varName)) {
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
    } else if (Character.isDigit(ch)) {
      constValue = 0;
      while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
        constValue = constValue * 10 + parseCharToInt(expression.charAt(index));
        index++;
      }
      token = Token.INT;
      index--;
    } else if (Character.isAlphabetic(ch)) {
      StringBuilder varNameSB = new StringBuilder();
      while (index < expression.length() && Character.isAlphabetic(expression.charAt(index))) {
        varNameSB.append(expression.charAt(index));
        index++;
      }
      varName = varNameSB.toString();
      if (checkVariableName()) {
        token = Token.VAR;
      } else {
        token = Token.ERR;
      }
      index--;
    }
    index++;
  }

  private TripleExpression lowestPriority() {
    return binAdd();
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


  public TripleExpression parse(String expression) {
    index = 0;
    this.expression = expression;
    token = Token.NEU;
    return binAdd();
  }


}
