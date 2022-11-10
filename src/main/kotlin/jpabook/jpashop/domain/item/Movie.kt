package jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie: Item() {
    private var director : String ?= null
    private var actor : String ?= null
}