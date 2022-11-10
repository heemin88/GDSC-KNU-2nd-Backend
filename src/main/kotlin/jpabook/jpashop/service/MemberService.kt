package jpabook.jpashop.service

import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    /**
     * 회원가입
     */
    @Transactional //변경
    open fun join(member: Member): Long? {
        validateDuplicateMember(member) //중복 회원 검증
        memberRepository.save(member)
        return member.id
    }
    private fun validateDuplicateMember(member: Member) {
        val findMembers = memberRepository.findByName(member.name)
        check(findMembers.isEmpty()) { "이미 존재하는 회원입니다." }
    }

    /**
     * 전체 회원 조회
     */
    fun findMembers(): List<Member?>? {
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long?): Member? {
        return memberRepository.findOne(memberId)
    }
}