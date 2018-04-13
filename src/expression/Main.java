package expression;

import java.math.BigInteger;

public class Main {

  private static void out(String mode, Object res) {
    if (mode.equals("i") || mode.equals("ui") || mode.equals("s")) {
      System.out.print((int) res);
    } else if (mode.equals("d")) {
      System.out.print((double) res);
    } else if (mode.equals("l")) {
      System.out.print((long) res);
    } else if (mode.equals("bi")) {
      System.out.print((BigInteger) res);
    }
  }

  public static void main(String args[]) {
    assert args.length >= 8;

    String mode = args[0];
    String expressioin = args[1];
    int x1, x2, y1, y2, z1, z2;
    try {
      x1 = Integer.parseInt(args[2]);
      x2 = Integer.parseInt(args[3]);
      y1 = Integer.parseInt(args[4]);
      y2 = Integer.parseInt(args[5]);
      z1 = Integer.parseInt(args[6]);
      z2 = Integer.parseInt(args[7]);
    } catch (NumberFormatException nfe) {
      System.out.println("Incorrect number format");
      return;
    }

    Object[][][] res = null;
    try {
      res = new GenericTabulator().tabulate(mode, expressioin, x1, x2, y1, y2, z1, z2);
    } catch (Exception e) {
      System.out.println("Something went wrong!");
      return;
    }

    for (int i = 0; i <= x2 - x1; i++) {
      for (int j = 0; j <= y2 - y1; j++) {
        for (int z = 0; z <= z2 - z1; z++) {
          out(mode, res[i][j][z]);
        }
        System.out.println();
      }
      System.out.println("=====");
    }

  }
}
