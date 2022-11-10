package jpabook.jpashop.domain.item

import jpabook.jpashop.domain.Category
import jpabook.jpashop.exception.NotEnoughStockException
import javax.persistence.*

@Entity
abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    var id: Long? = null

    var name: String? = null
    var price : Int ?=null
    var stockQuantity : Int ?= null

    @ManyToMany(mappedBy = "items")
    lateinit var categories : List<Category>

    //==비즈니스 로직==//
    open fun addStock(quantity: Int) {
        stockQuantity = stockQuantity!! + quantity
    }

    open fun removeStock(quantity: Int) {
        val restStock = stockQuantity!! - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        stockQuantity = restStock
    }
}
