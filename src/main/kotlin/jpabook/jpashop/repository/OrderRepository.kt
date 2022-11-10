package jpabook.jpashop.repository

import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.*


@Repository
class OrderRepository {
    @Autowired
    private val em: EntityManager? = null
    fun save(order: Order?) {
        em!!.persist(order)
    }

    fun findOne(id: Long?): Order {
        return em!!.find(Order::class.java, id)
    }
//    fun findAllByCriteria(orderSearch: OrderSearch): List<Order>? {
//        val cb = em!!.criteriaBuilder
//        val cq: CriteriaQuery<Order> = cb.createQuery(Order::class.java)
//        val o: Root<Order> = cq.from(Order::class.java)
//        val m: Join<Order, Member> = o.join("member", JoinType.INNER) //회원과 조인
//        val criteria: MutableList<Predicate> = ArrayList<Predicate>()
//        //주문 상태 검색
//        if (orderSearch.orderStatus != null) {
//            val status: Predicate = cb.equal(
//                o.get("status"),
//                orderSearch.orderStatus
//            )
//            criteria.add(status)
//        }
//        //회원 이름 검색
//        if (StringUtils.hasText(orderSearch.memberName)) {
//            val name: Predicate = cb.like(
//                m.< String > get < kotlin . String ? > "name", "%" +
//                        orderSearch.memberName + "%"
//            )
//            criteria.add(name)
//        }
//        cq.where(cb.and(criteria.toArray(arrayOfNulls<Predicate>(criteria.size))))
//        val query: TypedQuery<Order> = em.createQuery<Any>(cq).setMaxResults(1000) //최대 1000건
//        return query.resultList
//    }
//    fun findAll(orderSearch : OrderSearch) : List<Order>{
//
//    }
}