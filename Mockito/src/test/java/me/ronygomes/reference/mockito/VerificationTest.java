package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;

public class VerificationTest {

    @Test
    void testBasicVerification() {
        List<String> fruits = Mockito.mock(List.class);
        fruits.add("Apple");

         Mockito.verify(fruits).add("Apple");
         Mockito.verify(fruits, Mockito.times(1)).add("Apple");
         Mockito.verify(fruits, Mockito.never()).clear();
         Mockito.verify(fruits, Mockito.atLeast(1)).add("Apple");
         Mockito.verify(fruits, Mockito.atMost(5)).add("Apple");
    }

    @Test
    void testVerifyNoMoreInteractions() {
        List<String> fruits = Mockito.mock(List.class);
        fruits.add("Apple");

        // If this line is removed, then will fail verifyNoMoreInteractions(), fruit.add() is called
        Mockito.verify(fruits).add("Apple");

        // Following line will return true if all invoked methods are verified and nothing else is called
        Mockito.verifyNoMoreInteractions(fruits);
    }

    @Test
    void testVerifyNoMoreInteractionsNoStub() {
        List<String> fruits = Mockito.mock(List.class);
        Mockito.when(fruits.add("Apple")).thenReturn(true);

        fruits.add("Apple");

        // If Mockito.ignoreStubs() is omitted need to write following line
        // Mockito.verify(fruits).add("Apple");
        // As this is stubbed methods, can ignore all at once
        Mockito.verifyNoMoreInteractions(Mockito.ignoreStubs(fruits));
    }

    @Test
    void testVerifyNoInteractions() {
        List<String> fruits = Mockito.mock(List.class);
        Mockito.verifyNoInteractions(fruits);
    }

    @Test
    void testInorderInteractions() {
        List<String> fruits = Mockito.mock(List.class);
        fruits.add("Apple");
        fruits.add("Banana");

        InOrder order = Mockito.inOrder(fruits);
        order.verify(fruits).add("Apple");
        order.verify(fruits).add("Banana");
    }
}
