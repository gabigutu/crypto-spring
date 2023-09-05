package com.crypto.exchange;

import com.crypto.exchange.entities.User;
import com.crypto.exchange.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertNull;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void givenVasileAsUsernameCheckIfItsInTheDatabase() {
        User user = this.userRepository.findByUsername("vasile");
        assertNotNull("Vasile nu exista, dar ar trebui sa existe", user);
    }

    @Test
    public void testGigelNuExista() {
        User user = this.userRepository.findByUsername("gigel");
        assertNull("Gigel exista, dar nu ar trebui sa existe", user);
    }
}
