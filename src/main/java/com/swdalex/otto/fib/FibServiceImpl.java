package com.swdalex.otto.fib;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class FibServiceImpl implements FibService {

  private final RestOperations restOperations;

  public FibServiceImpl(RestOperations restOperations) {
    this.restOperations = restOperations;
  }

  @Override
  public long calculateFibonacciNumber(int index) {
    if (index < 2) {
      return index;
    }

    int prePreviousNumber = 0, previousNumber = 1, currentNumber = 1;

    for (int i = 2; i <= index; i++) {
      currentNumber = prePreviousNumber + previousNumber;
      prePreviousNumber = previousNumber;
      previousNumber = currentNumber;
    }

    return currentNumber;
  }

  @Override
  public long getNextCalculation(int index, int totalNumbers) {
    Long fibonacciNumber = restOperations.getForObject("/calculate/{index}/{totalNumbers}", Long.class, index, totalNumbers);
    if (fibonacciNumber == null) {
      throw new IllegalStateException("The calculation for index " + index + " returned NULL.");
    }
    return fibonacciNumber;
  }
}
