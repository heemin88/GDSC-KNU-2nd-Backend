package jpabook.jpashop.domain

import jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
open class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
     var id : Long ?= null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="item_id")
     lateinit var item: Item
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
     lateinit var order : Order

     var orderPrice : Int ?= null
     var count : Int ?= null

    protected constructor()
    //==생성 메서드==//
    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem? {
            val orderItem = OrderItem()
            orderItem.item = item
            orderItem.orderPrice = orderPrice
            orderItem.count = count
            item.removeStock(count)
            return orderItem
        }
    }

    //==비즈니스 로직==//
    /** 주문 취소  */
    fun cancel() {
        count?.let { item.addStock(it) }
    }
    //==조회 로직==//
    /** 주문상품 전체 가격 조회  */
    fun getTotalPrice(): Int {
        return orderPrice!! * count!!
    }



}
