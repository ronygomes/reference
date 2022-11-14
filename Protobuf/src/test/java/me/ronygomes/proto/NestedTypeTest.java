package me.ronygomes.proto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NestedTypeTest {

    @Test
    void testNestedType() {
        NestedType.OneB oneB = NestedType.OneB.newBuilder().setOne(10).build();
        NestedType.OneA.OneATwoA oneATwoA = NestedType.OneA.OneATwoA.newBuilder().setTwo(20).build();
        NestedType.OneA.OneATwoB oneATwoB = NestedType.OneA.OneATwoB.newBuilder().build();
        NestedType.OneB.OneBTwoA oneBOneBTwoA = NestedType.OneB.OneBTwoA.newBuilder().build();
        NestedType.OneA.OneATwoA.OneATwoAThreeA oneATwoAThreeA = NestedType.OneA.OneATwoA.OneATwoAThreeA.newBuilder().setThree(30).build();


        NestedType.OneA o = NestedType.OneA.newBuilder()
                .setOneB(oneB)
                .setOneATwoA(oneATwoA)
                .setOneATwoB(oneATwoB)
                .setOneBOneBTwoA(oneBOneBTwoA)
                .setOneATwoAThreeA(oneATwoAThreeA)
                .build();


        Assertions.assertEquals(10, o.getOneB().getOne());
        Assertions.assertEquals(20, o.getOneATwoA().getTwo());
        Assertions.assertEquals(30, o.getOneATwoAThreeA().getThree());
    }
}
