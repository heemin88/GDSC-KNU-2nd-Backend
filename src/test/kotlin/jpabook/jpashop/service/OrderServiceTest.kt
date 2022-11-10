package jpabook.jpashop.service

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.domain.item.Item
import jpabook.jpashop.exception.NotEnoughStockException
import jpabook.jpashop.repository.OrderRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.util.AssertionErrors.assertEquals
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    var em: EntityManager? = null

    @Autowired
    var orderService: OrderService? = null

    @Autowired
    var orderRepository: OrderRepository? = null
    @Test
    @Throws(Exception::class)
    fun 상품주문() {
        //given
        val member = createMember()
        val book = createBook("시골 JPA", 10000, 10)
        val orderCount = 2

        //when
        val orderId = orderService!!.order(member.id, book.id, orderCount)

        //then
        val getOrder = orderRepository!!.findOne(orderId)
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.status)
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.orderItems?.size)
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice())
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.stockQuantity)
    }

    @Test
    @Throws(Exception::class)
    fun 상품주문_재고수량초과() {
        //given
        val member = createMember()
        val item: Item = createBook("시골 JPA", 10000, 10)
        val orderCount = 11

        //when
        orderService!!.order(member.id, item.id, orderCount)

        //then
        print("재고 수량 부족 예외가 발행해야 한다.")
    }

    @Test
    @Throws(Exception::class)
    fun 주문취소() {
        //given
        val member = createMember()
        val item = createBook("시골 JPA", 10000, 10)
        val orderCount = 2
        val orderId = orderService!!.order(member.id, item.id, orderCount)

        //when
        orderService!!.cancelOrder(orderId)

        //then
        val getOrder = orderRepository!!.findOne(orderId)
        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.status)
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.stockQuantity)
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        val book = Book()
        book.name = name
        book.price = price
        book.stockQuantity = stockQuantity
        em!!.persist(book)
        return book
    }

    private fun createMember(): Member {
        val member = Member()
        member.name = "회원1"
        member.address = Address("서울", "강가", "123-123")
        em!!.persist(member)
        return member
    }
}