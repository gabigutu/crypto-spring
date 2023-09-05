package com.crypto.exchange;

import com.crypto.exchange.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class MockTests {

    @Mock
    UserRepository mockUserRepository;

    @Autowired
    UserRepository actualUserRepository;

    @Mock
    List<String> mockedList;

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        Mockito.when(mockUserRepository.count()).thenReturn(1L);

        long count = actualUserRepository.count();
        Assert.assertEquals(mockUserRepository.count(), count);

        Mockito.verify(mockUserRepository).count();
    }

    @Test
    public void mockedList() {
        assertEquals(0, mockedList.size());

        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        Mockito.when(mockedList.size()).thenReturn(1);

        assertEquals(1, mockedList.size());
    }
}
