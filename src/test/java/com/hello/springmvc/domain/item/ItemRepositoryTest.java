package com.hello.springmvc.domain.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = Item.of("itemA", 100000, 10);
        // when
        Item saveItem = itemRepository.save(item);
        // then
        Optional<Item> findItem = itemRepository.findById(saveItem.getId());

        assertTrue(findItem.isPresent());
        assertEquals(findItem.get(), saveItem);
    }


    @Test
    @DisplayName("findAll")
    void findAll() throws Exception {
        // given
        Item item1 = Item.of("item1", 10000, 10);
        Item item2 = Item.of("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);
        // when
        List<Item> result = itemRepository.findAll();
        // then
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("updateItem")
    void test_case_1() throws Exception {
        // given
        Item item1 = Item.of("item1", 10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();
        // when
        Item item2 = Item.of("item2", 20000, 20);
        itemRepository.update(itemId, item2);
        // then
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        assertTrue(optionalItem.isPresent());
        Item findItem = optionalItem.get();

        assertEquals(findItem.getItemName(), item2.getItemName());
        assertEquals(findItem.getPrice(), item2.getPrice());
        assertEquals(findItem.getQuantity(), item2.getQuantity());
    }
}