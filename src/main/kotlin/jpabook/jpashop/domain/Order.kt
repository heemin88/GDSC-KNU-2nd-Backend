package jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders") //order은 이미 있으므로 orders로 바꿔줘야함.
class Order {
    @Id @GeneratedValue
    @Column(name="order_id")
    var id : Long ?= null
    //의존관계 주인 : memberid변경시 둘중 변경할 객체를 고르는 것. foreign key하나만 변경하면 되는 order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id") // foreign key id : member_id
    private lateinit var member : Member

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems : MutableList<OrderItem> ?= null


    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    private lateinit var delivery : Delivery

     lateinit var orderDate : LocalDateTime //주문시간
    @Enumerated(EnumType.STRING)
     lateinit var status: OrderStatus //주문상태[ORDER,CANCEL]
    // 연관관계 메서드
    open fun setMember(member: Member) {
        this.member = member
        member.orders.add(this)
    }

        fun addOrderItem(orderItem: OrderItem) {
            orderItems?.add(orderItem)
            orderItem.order = this
        }

        fun setDelivery(delivery: Delivery) {
            this.delivery = delivery
            delivery.order = this
        }
    //==생성 메서드 ==//
    companion object {
        fun createOrder(
        member: Member?, delivery: Delivery?, vararg orderItems: OrderItem?
        ): Order? {
            val order = Order()
            if (member != null) {
                order.member = member
            }
            if (delivery != null) {
                order.delivery = delivery
            }
            for (orderItem in orderItems) {
                if (orderItem != null) {
                    order.addOrderItem(orderItem)
                }
            }
            order.status = OrderStatus.ORDER
            order.orderDate = LocalDateTime.now()
            return order
        }
    }


    //== 비즈니스 로직 == //
    /** 주문취소 **/
    open fun cancel(){
        if (delivery.status == DeliveryStatus.COMP){
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }
        this.status = OrderStatus.CANCEL
        for (orderItem :OrderItem in orderItems!!){
            orderItem.cancel()
        }

    }
    //==조회 로직==//
    /**전체 주문 가격 조회 */
    fun getTotalPrice(): Int {
        var totalPrice = 0
        for (orderItem in orderItems!!) {
            totalPrice += orderItem.getTotalPrice()
        }
        return totalPrice
    }

}


