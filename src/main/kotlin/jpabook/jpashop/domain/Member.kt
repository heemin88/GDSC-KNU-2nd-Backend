package jpabook.jpashop.domain

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    var id : Long ?= null

    var name : String = ""

    @Embedded
    lateinit var address :Address

    @OneToMany(mappedBy ="member") //나는 order테이블에 있는 member 필드에 매핑 된거야. -> 읽기 전용
    lateinit var  orders : MutableList<Order>



}