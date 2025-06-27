package com.example.doglogbe.config;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));

        return new CustomUserDetails(member);
    }
}
