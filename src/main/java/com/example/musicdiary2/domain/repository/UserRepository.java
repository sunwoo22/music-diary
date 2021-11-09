package com.example.musicdiary2.domain.repository;

import com.example.musicdiary2.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String userEmail);

    // 회원가입
//    void register(UserEntity userEntity) throws Exception;

    // db에 authkey저장
    @Transactional
    @Modifying
    @Query("update user u set u.authkey = :authkey where u.email = :email")
    int createAuthkey(@Param("email") String email, @Param("authkey") String authkey) throws Exception;

    // 이메일 인증 후 authstatus 1로 변경
    @Transactional
    @Modifying
    @Query("update user u set u.authstatus = 1 where u.email = :email")
    void updateAuthstatus(@Param("email") String email) throws Exception;

    // 이메일 중복 체크
    @Query("select count(u) from user u where u.email = :email")
    int idChk(@Param("email") String email);

}
