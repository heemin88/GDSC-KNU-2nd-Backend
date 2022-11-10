package jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book :Item() {
    private var author : String ?= null
    private var isbn : String ?= null
}