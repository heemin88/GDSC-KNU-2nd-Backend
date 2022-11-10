package jpabook.jpashop.controller

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController {
    @Autowired
    private val memberService: MemberService? = null
    @GetMapping(value = ["/members/new"])
    fun createForm(model: Model): String {
        model.addAttribute("memberForm", MemberForm())
        return "members/createMemberForm"
    }

    @PostMapping(value = ["/members/new"])
    fun create(@Validated form: MemberForm, result: BindingResult): String {
        if (result.hasErrors()) {
            return "members/createMemberForm"
        }
        val address = form.city?.let {
            form.street?.let { it1 ->
                form.zipcode?.let { it2 ->
                    Address(
                        it, it1, it2
                    )
                }
            }
        }
        val member = Member()
        member.name = form.name.toString()
        if (address != null) {
            member.address=address
        }
        memberService!!.join(member)
        return "redirect:/"
    }
}