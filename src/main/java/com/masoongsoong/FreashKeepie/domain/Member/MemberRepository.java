package com.masoongsoong.FreashKeepie.domain.Member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

    Optional<Member> findByloginID(String loginID); //Optional : 반환값이 있을수도, 없을수도(null) 있음 -> 예외처리 방지

    Boolean existsByNickname(String nickname);
    //Boolean existsByloginID(String loginID); //안 쓰이는데 오류 뜬느것 같길래 주석처리함
}
