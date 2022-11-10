package jpabook.jpashop.service

import jpabook.jpashop.domain.item.Item
import jpabook.jpashop.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService {
    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Transactional
    fun saveItem(item: Item?) {
        if (item != null) {
            itemRepository.save(item)
        }
    }

    fun findItems(): List<Item?>? {
        return itemRepository.findAll()
    }

    fun findOne(itemId: Long?): Item? {
        return itemRepository.findOne(itemId)
    }


}