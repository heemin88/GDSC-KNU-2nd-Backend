package jpabook.jpashop.domain

import javax.persistence.Embeddable

@Embeddable // 어딘가에 내장될 수 있다.
class Address {
    constructor(city: String, street: String, zipcode: String) {
        this.city = city
        this.street = street
        this.zipcode = zipcode
    }

    private var city :String = ""
    private var street: String= ""
    private var zipcode : String = ""

}
