package com.swdalex.otto.fib;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FibService.class})
public class FibServiceImplTest {

  private FibService fibService;

  @MockBean
  private RestOperations restOperations;

  @Before
  public void setUp() {
    fibService = new FibServiceImpl(restOperations);
  }

  @Test
  public void testCalculateFibonacciNumber() {
    assertThat(fibService.calculateFibonacciNumber(1), is(1L));
    assertThat(fibService.calculateFibonacciNumber(10), is(55L));
    assertThat(fibService.calculateFibonacciNumber(20), is(6765L));
  }

  @Test
  public void testGetNextCalculation() {
    int index = 1, totalNumbers = 2;

    when(restOperations.getForObject("/calculate/{index}/{totalNumbers}", Long.class, index, totalNumbers)).thenReturn(1L);

    assertThat(fibService.getNextCalculation(index, totalNumbers), is(1L));
  }
}