package com.domain.filter;

import com.domain.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RunWith(MockitoJUnitRunner.class)
public class AbstractFilterTest {

    @Mock
    private Item mockItem;

    @Test
    public void testFilterPrice() throws Exception {

        //Given, When
        List<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        AbstractFilter abstractFilter = Mockito.mock(AbstractFilter.class, Mockito.CALLS_REAL_METHODS);
        threadSafeItemList.add(mockItem);

        //Then
        assert abstractFilter.filterPrice(threadSafeItemList) == 0.0d;
    }

}