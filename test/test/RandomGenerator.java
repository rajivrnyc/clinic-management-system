package test;

import java.util.Random;

/**
 * A random number generator to test temperature in our test cases.
 */
public class RandomGenerator {
  private final Random random;
  private final int[] mockValues;
  private int mockIndex = 0;

  public RandomGenerator() {
    this.random = new Random();
    this.mockValues = null;
  }

  public RandomGenerator(int... values) {
    this.random = new Random();
    this.mockValues = values;
  }

  /**
   * A random number generator to test mock values for our test class.
   * 
   * @param range sets the number of random numbers created
   * @return an array of mock values
   */
  public int nextInt(int range) {
    if (mockValues != null) {
      if (mockIndex < mockValues.length) {
        return mockValues[mockIndex++];
      } else {
        throw new IndexOutOfBoundsException("No more mock values available");
      }
    } else {
      return random.nextInt(range);
    }
  }
}
