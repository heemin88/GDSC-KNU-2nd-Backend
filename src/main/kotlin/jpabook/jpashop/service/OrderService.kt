package jpabook.jpashop.service

import jpabook.jpashop.domain.*
import jpabook.jpashop.domain.item.Item
import jpabook.jpashop.repository.ItemRepository
import jpabook.jpashop.repository.MemberRepository
import jpabook.jpashop.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class OrderService {
    @Autowired
    private val orderRepository : OrderRepository?= null
    @Autowired
    private val memberRepository: MemberRepository ?= null
    @Autowired
    private val itemRepository: ItemRepository? = null

    // 주문
   fun order(memberId: Long?, itemId: Long?, count: Int): Long? {

        //엔티티 조회
        val member: Member? = memberRepository!!.findOne(memberId)
        val item: Item? = itemRepository!!.findOne(itemId)

        //배송정보 생성
        val delivery = Delivery()
        if (member != null) {
            delivery.address = member.address
        }
        delivery.status = DeliveryStatus.READY

        //주문상품 생성

        val orderItem: OrderItem? = item?.let { item.price?.let { it1 -> OrderItem.createOrderItem(it, it1, count) } }

        //주문 생성
        val order: Order? = Order.createOrder(member, delivery, orderItem)

        //주문 저장
        orderRepository!!.save(order)
        if (order != null) {
            return order.id
        }
        return 0
    }
    /**
     * 주문 취소
     */
    @Transactional
    fun cancelOrder(orderId: Long?) {
        //주문 엔티티 조회
        val order: Order = orderRepository!!.findOne(orderId)
        //주문 취소
        order.cancel()
    }

    //검색
//    fun findOrders(orderSearch: OrderSearch?): List<Order?>? {
//        return orderRepository.findAllByString(orderSearch)
//    }
}