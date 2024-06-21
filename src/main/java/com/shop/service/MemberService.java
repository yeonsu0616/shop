package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //DB관리. 컨트롤과 연결
@Transactional
@RequiredArgsConstructor //final,@NonNull이 변수에 붙으면 자동 주입(Autowired)을 해줌
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository; //자동 주입 됨

    public Member saveMember(Member member){ //savemember를 멤버 컨트롤에서 부름
        validateDuplicateMember(member);
        return memberRepository.save(member); //데이터베이스에 저장하라는 명령
    }
    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail()); //객체가 있으면 findmember가 나옴
        if(findMember != null){ //가입할때 있는지 없는지 확인
            //throw가 발생이 안되면 save.
            throw new IllegalStateException("이미 가입된 회원입니다."); //예외 발생
        }

    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }
        //빌더패턴
        //리턴을 객체를 리턴
        return User.builder().username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

}
