package jpabook.jpashop

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {
    @GetMapping("hello")
    public fun hello (model: Model) : String{ // model : 컨트롤러에서 뷰로 데이터 전달이 가능
        model.addAttribute("data","hello!")
        return "hello" // hello.html을 찾아서 띄움.
    }
}