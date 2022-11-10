package jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Delivery {
    @Id @GeneratedValue
    @Column(name ="delivery_id")
    var id : Long ?= null

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    lateinit var order:Order

    @Embedded
    lateinit var address: Address

    @Enumerated(EnumType.STRING) // 중요 !!! Ordinal은 숫자로 enum을 분류
    lateinit var status :DeliveryStatus //READY, COMP


}
