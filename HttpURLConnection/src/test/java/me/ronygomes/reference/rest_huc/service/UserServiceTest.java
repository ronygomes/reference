package me.ronygomes.reference.rest_huc.service;

import me.ronygomes.reference.rest_huc.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    void testFetchUserById() {
        User user = UserService.fetchUserById(1);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Leanne Graham", user.getFullName());
        Assertions.assertEquals("Bret", user.getUsername());
        Assertions.assertEquals("Sincere@april.biz", user.getEmail());

        Assertions.assertNull(UserService.fetchUserById(11));
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setEmail("a@b.com");
        user.setFullName("A B");
        user.setUsername("ab");

        User returnedUser = UserService.createUser(user);

        Assertions.assertNotNull(returnedUser);
        Assertions.assertEquals(11, returnedUser.getId());
        Assertions.assertEquals("a@b.com", returnedUser.getEmail());
        Assertions.assertEquals("A B", returnedUser.getFullName());
        Assertions.assertEquals("ab", returnedUser.getUsername());
    }
}
