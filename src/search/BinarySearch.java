package search;

public class BinarySearch {

  public static void main(String[] args) {
    int x = Integer.parseInt(args[0]);
    int arraySize = args.length - 1;
    int[] a = new int[arraySize];
    for (int i = 0; i < arraySize; i++) {
      a[i] = Integer.parseInt(args[i + 1]);
    }
    int iterativeResult = IterativeAlgorithm(x, a);
    int recursiveResult = RecursiveAlgorithm(x, a, -1, arraySize);
    if (iterativeResult == recursiveResult)
      System.out.println(iterativeResult);
    else
      System.out.println(String.format("it:%d rec:%d ERROR", iterativeResult, recursiveResult));
  }


  // PRE: i = 0..(size - 1) ^ a[i] >= a[i - 1]
  // POST: R = 1..size - 1 ^ R = i ^ a[i - 1] > x >= a[i]
  // POST: R = 0 ^ R = i ^ x >= a[i]
  // POST: R = size ^ R = i ^ a[i - 1] > x
  private static int IterativeAlgorithm(int x, int[] a) {
    int l = -1, r = a.length, m;

    // INV: a[0]..a[l] > x >= a[r]..a[size - 1]
    while (r - l > 1) {
      // INV ^ r - l > 1
      m = (l + r) / 2;
      // l < m < r;
      if (a[m] <= x) {
        // INV ^ a[m] <= x
        // a[0]..a[l] > x >= a[m]..a[size - 1]
        // m - l < r - l
        r = m;
        // INV
      } else {
        // INV ^ a[m] > x
        // a[0]..a[m] > x >= a[r]..a[size - 1]
        // r - m < r - l
        l = m;
        // INV
      }
    }
    // PS: INV ^ a[r - 1] > x
    // R = r
    return r;
  }

  // PRE: i = 0..(size - 1) ^ a[i] >= a[i - 1]
  // PRE: l <- [-1..size - 1] ^ r <- [0..size]
  // INV: a[0]..a[l] > x >= a[r]..a[size - 1]
  // POST: R = 1..size - 1 ^ R = i ^ a[i - 1] > x >= a[i]
  // POST: R = 0 ^ R = i ^ x >= a[i]
  // POST: R = size ^ R = i ^ a[i - 1] > x
  private static int RecursiveAlgorithm(int x, int[] a, int l, int r) {

    // if r - l == 1 => a[r] <= x, a[r - 1] > x, R = r
    if (r - l == 1)
      return r;

    // INV ^ r - l > 1
    int m = (l + r) / 2;
    // l < m < r
    if (a[m] <= x)
      // INV ^ a[m] <= x
      // a[0]..a[m] > x >= a[r]..a[size - 1]
      // m - l < r - l
      return RecursiveAlgorithm(x, a, l, m);
    else
      // INV ^ a[m] > x
      // a[0]..a[m] > x >= a[r]..a[size - 1]
      // r - m < r - l
      return RecursiveAlgorithm(x, a, m, r);
  }

}
