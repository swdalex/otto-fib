package com.swdalex.otto.fib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibController {

  @Autowired
  private FibService fibService;

  private static final Logger logger = LogManager.getLogger();

  @GetMapping("/trigger/{totalNumbers}")
  public void triggerFibonacciNumbersCalculation(@PathVariable int totalNumbers) {

    if (totalNumbers < 1) {
      throw new IllegalArgumentException("Number of calculations should be at least 1.");
    }

    logger.info("Triggered calculation for " + totalNumbers + " Fibonacci Numbers");
    fibService.getNextCalculation(1, totalNumbers);
  }

  @GetMapping("/calculate/{index}/{totalNumbers}")
  public long calculateFibonacciNumber(@PathVariable int index, @PathVariable int totalNumbers) {

    if (index < 0) {
      throw new IllegalArgumentException("Index should be 0 or greater.");
    }

    if (totalNumbers < 1) {
      throw new IllegalArgumentException("Number of calculations should be at least 1.");
    }


    long fibonacciNumber = fibService.calculateFibonacciNumber(index);
    logger.info("Fibonacci Number (" + index + ") is " + fibonacciNumber);

    index++;

    if (index <= totalNumbers) {
      fibService.getNextCalculation(index, totalNumbers);
    }

    return fibonacciNumber;
  }

}
