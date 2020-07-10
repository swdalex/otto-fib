package com.swdalex.otto.fib;

public interface FibService {

  long calculateFibonacciNumber(int index);

  long getNextCalculation(int index, int totalNumbers);

}
