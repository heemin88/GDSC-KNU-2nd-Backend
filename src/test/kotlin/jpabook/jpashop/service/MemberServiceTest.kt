package jpabook.jpashop.service

import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class MemberServiceTest{
    @Autowired
    var memberService: MemberService? = null

    @Autowired
    var memberRepository: MemberRepository? = null

    @Test
    @Throws(Exception::class)
    fun 회원가입() {
//Given
        val member = Member()
        member.name="kim"
        //When
        val saveId = memberService!!.join(member)
        //Then
        assertEquals(member, memberRepository?.findOne(saveId))
    }

    @Test
    @Throws(Exception::class)
    fun 중복_회원_예외() {
//Given
        val member1 = Member()
        member1.name="kim"
        val member2 = Member()
        member2.name=("kim")
        //When
        memberService!!.join(member1)
        try{
            memberService!!.join(member2) //예외가 발생해야 한다.
        }catch (e : IllegalStateException){
            return
        }

        //Then
        print("예외가 발생해야 한다.")
    }
}

