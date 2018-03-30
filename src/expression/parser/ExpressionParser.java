package expression.parser;

import expression.*;
import expression.exceptions.*;

import java.util.Arrays;
import java.util.List;

public class ExpressionParser implements Parser {

  private String expression;
  private int index = 0;
  private int balance = 0;
  private int constValue;
  private String varName;
  private boolean negUp;
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
    AND,
    XOR,
    OR,
    NOT,
    BTC,
    OWF
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

  private boolean checkBinary(Token t) {
    return (t == Token.ADD || t == Token.SUB || t == Token.MUL || t == Token.DIV || t == Token.AND || t == Token.XOR
        || t == Token.OR);
  }

  private boolean checkUnary(Token t) {
    return (t == Token.BTC || t == Token.NOT || t == Token.NEG);
  }

  private boolean checkOperand(Token t) {
    return (t == Token.VAR || t == Token.CST);
  }

  private void setToken(Token t) throws ParsingException {
    if ((checkBinary(t) || t == Token.CBR || t == Token.NEU) && !(checkOperand(token) || token == Token.CBR)) {
      throw new MissingOperandException(expression, index);
    }

    if (checkOperand(t) && checkOperand(token)) {
      throw new MissingOperationException(expression, index);
    }

    if (t == Token.CBR && balance == 0) {
      throw new MissingOpeningBracketException(expression, index);
    }

    if (t == Token.OBR && (token == Token.CBR || checkOperand(token))) {
      throw new IncorrectOpeningBracketException(expression, index);
    }

    if (t == Token.NEU && balance > 0) {
      throw new MissingClosingBracketException(expression, balance);
    }

    if (t == Token.INV) {
      throw new IncorrectSymbolException(expression, index);
    }

    if (t == Token.OWF) {
      throw new ParsingOverflowException(expression, index);
    }

    token = t;
  }

  private void parseToken() throws ParsingException {
    skipSpaces();
    if (index >= expression.length()) {
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
    } else if (ch == '-') {
      if (token == Token.VAR || token == Token.CST || token == Token.CBR) {
        setToken(Token.SUB);
      } else {
        setToken(Token.NEG);
      }
    } else if (ch == '(') {
      setToken(Token.OBR);
      balance++;
    } else if (ch == ')') {
      setToken(Token.CBR);
      balance--;
    } else if (ch == '&') {
      setToken(Token.AND);
    } else if (ch == '^') {
      setToken(Token.XOR);
    } else if (ch == '|') {
      setToken(Token.OR);
    } else if (ch == '~') {
      setToken(Token.NOT);
    } else if (Character.isDigit(ch)) {
      StringBuilder constSB = new StringBuilder();
      if (token == Token.NEG) {
        constSB.append('-');
        negUp = true;
      }
      while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
        constSB.append(expression.charAt(index));
        index++;
      }
      constValue = 0;
      try {
        constValue = Integer.parseInt(constSB.toString());
        setToken(Token.CST);
      } catch (NumberFormatException e) {
        setToken(Token.OWF);
      }
      index--;
    } else if (Character.isAlphabetic(ch)) {
      StringBuilder tokenSB = new StringBuilder();
      while (index < expression.length() && Character.isAlphabetic(expression.charAt(index))) {
        tokenSB.append(expression.charAt(index));
        index++;
      }
      String tokenStr = tokenSB.toString();
      if (tokenStr.equals("count")) {
        setToken(Token.BTC);
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

  private TripleExpression lowestPriority() throws ParsingException {
    return or();
  }

  private TripleExpression unary() throws ParsingException {
    parseToken();
    TripleExpression res;
    switch (token) {
      case CST:
        res = new Const(constValue);
        parseToken();
        break;
      case VAR:
        res = new Variable(varName);
        parseToken();
        break;
      case NEG:
        res = unary();
        if (negUp && res instanceof Const) {
          negUp = false;
        } else if (res instanceof CheckedNegate) {
          res = ((CheckedNegate) res).getExpression();
        } else {
          res = new CheckedNegate(res);
        }
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

  private TripleExpression binMul() throws ParsingException {
    TripleExpression res = unary();
    while (true) {
      switch (token) {
        case MUL:
          res = new CheckedMultiply(res, unary());
          break;
        case DIV:
          res = new CheckedDivide(res, unary());
          break;
        default:
          return res;
      }
    }
  }

  private TripleExpression binAdd() throws ParsingException {
    TripleExpression res = binMul();
    while (true) {
      switch (token) {
        case ADD:
          res = new CheckedAdd(res, binMul());
          break;
        case SUB:
          res = new CheckedSubtract(res, binMul());
          break;
        default:
          return res;
      }
    }
  }

  private TripleExpression and() throws ParsingException {
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

  private TripleExpression xor() throws ParsingException {
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

  private TripleExpression or() throws ParsingException {
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

  public TripleExpression parse(String expression) throws ParsingException {
    index = balance = 0;
    negUp = false;
    this.expression = expression;
    token = Token.NEU;
    return lowestPriority();
  }


}
