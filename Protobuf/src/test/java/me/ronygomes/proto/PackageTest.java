package me.ronygomes.proto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PackageTest {

    @Test
    void testPackage() {
        Package.PackageA packageA = Package.PackageA.newBuilder()
                .build();

        Assertions.assertSame(Packed.PackageA.class, packageA.getIPackageA().getClass());
        Assertions.assertSame(Packed.PackageB.class, packageA.getIPackageB().getClass());
        Assertions.assertSame(Package.PackageA.class, packageA.getPackageA().getClass());
        Assertions.assertSame(Package.PackageB.class, packageA.getPackageB().getClass());
    }
}
