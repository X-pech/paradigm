package search;

public class BinarySearchSpan {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Error: not enough arguments");
      return;
    }

    int x = Integer.parseInt(args[0]);
    int arraySize = args.length - 1;
    int[] a = new int[arraySize];
    for (int i = 0; i < arraySize; i++) {
      a[i] = Integer.parseInt(args[i + 1]);
    }
    int resultLeft = iterativeAlgorithm(x, a), resultRight;
    if (resultLeft < arraySize) {
      if (a[resultLeft] == x) {
        resultRight = recursiveAlgorithm(x - 1, a, -1, arraySize);
      } else {
        resultRight = resultLeft;
      }
    } else {
      resultRight = resultLeft;
    }

    System.out.println(String.format("%d %d", resultLeft, resultRight - resultLeft));
  }


  // PRE: i = 0..(size - 1) ^ a[i] >= a[i - 1]
  // POST: R = 1..size - 1 ^ R = i ^ a[i - 1] > x >= a[i] || R = 0 ^ x >= a[0] || R = size ^ a[size - 1] > x
  // POST: i = 0..(size - 1) a[i] = a'[i]
  private static int iterativeAlgorithm(int x, int[] a) {
    // PRE: l = -1..size - 1 ^ r = 0..size
    int l = -1, r = a.length, m;

    // INV: ((l = 0..size - 1 ^ a[0]..a[l] > x) || l = -1) ^ ((r = 0..size - 1 ^ x >= a[r]..a[size - 1]) || r = size) ^ r - l >= 1
    while (r - l > 1) {
      m = (l + r) / 2;
      // l < m < r;
      if (a[m] <= x) {
        // INV ^ a[m] <= x
        // a[0]..a[l] > x >= a[m]..a[size - 1]
        // m - l < r - l
        r = m;
      } else {
        // INV ^ a[m] > x
        // a[0]..a[m] > x >= a[r]..a[size - 1]
        // r - m < r - l
        l = m;
      }
    }
    // POST: INV ^ r - l == 1 => a[r] <= x ^ a[r - 1] > x =>
    // R = r
    return r;
  }


  // PRE: i = 0..(size - 1) ^ a[i] >= a[i - 1] ^ l = -1..size - 1 ^ r = 0..size ^ r - l >= 1
  // POST: R = 1..size - 1 ^ R = i ^ a[i - 1] > x >= a[i] || R = 0 ^ x >= a[0] || R = size ^ a[size - 1] > x
  // POST: i = 0..(size - 1) a[i] = a'[i]
  // INV: ((l = 0..size - 1 ^ a[0]..a[l] > x) || l = -1) ^ ((r = 0..size - 1 ^ x >= a[r]..a[size - 1]) || r = size) ^ r - l >= 1
  private static int recursiveAlgorithm(int x, int[] a, int l, int r) {

    // if INV & r - l == 1, a[r] <= x, a[r - 1] > x =>
    // R = r
    if (r - l == 1) {
        return r;
    }

    int m = (l + r) / 2;
    // l < m < r
    if (a[m] <= x) {
      // INV ^ a[m] <= x
      // a[0]..a[m] > x >= a[r]..a[size - 1]
      // m - l < r - l
      return recursiveAlgorithm(x, a, l, m);
    } else {
      // INV ^ a[m] > x
      // a[0]..a[m] > x >= a[r]..a[size - 1]
      // r - m < r - l
      return recursiveAlgorithm(x, a, m, r);
    }
  }

}
