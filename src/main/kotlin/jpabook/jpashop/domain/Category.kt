package jpabook.jpashop.domain

import jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private var id : Long ?= null

    private var name : String ?= null
    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name="item_id")]
    )
    private lateinit var items : MutableList<Item>

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private lateinit var parent : Category

    @OneToMany(mappedBy = "parent")
    private lateinit var child : List<Category>

//    //==연관관계 메서드==//
//    fun addChildCategory(child: Category) {
//        this.child.add(child)
//        child.setParent(this)
//    }
}