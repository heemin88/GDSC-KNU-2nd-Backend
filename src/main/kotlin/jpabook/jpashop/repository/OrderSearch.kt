package jpabook.jpashop.repository

import jpabook.jpashop.domain.OrderStatus




class OrderSearch {
   val memberName //회원 이름
            : String? = null
    val orderStatus //주문 상태[ORDER, CANCEL]
            : OrderStatus? = null

}
