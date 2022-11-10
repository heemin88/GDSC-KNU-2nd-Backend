package jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album : Item() {
    private var artist : String ?= null
    private var etc : String ?= null
}