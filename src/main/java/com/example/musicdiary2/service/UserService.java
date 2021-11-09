package com.example.musicdiary2.service;

import com.example.musicdiary2.domain.Role;
import com.example.musicdiary2.domain.entity.UserEntity;
import com.example.musicdiary2.domain.repository.UserRepository;
import com.example.musicdiary2.dto.UserDto;
import com.example.musicdiary2.mail.MailHandler;
import com.example.musicdiary2.mail.Tempkey;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    // 이메일 인증
    private JavaMailSender mailSender;

//    TransactionTemplate transactionTemplate;
//    UserDto userDto;
//
//    public UserService(PlatformTransactionManager txManager,
//                       UserDto userDto) {
//        this.transactionTemplate = new TransactionTemplate(txManager);
//        this.userDto = userDto;
//    }

    // 회원가입 - 아이디 중복 체크
    @Transactional
    public void register1(UserDto userDto) throws Exception {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(userDto.toEntity());
    }

    // 회원가입 - 이메일 인증
    @Transactional
    public void register(UserDto userDto) throws Exception {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // db에 회원가입 정보 저장
        userRepository.save(userDto.toEntity());

        // 인증키 생성
        String key = new Tempkey().getKey(10, false);

        // 인증키 db에 저장
        userRepository.createAuthkey(userDto.getEmail(), key);

        // 메일 발송
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(userDto.getEmail());
//        message.setFrom("username2da@gmail.com");
//        message.setSubject("회원가입 확인 메일입니다.");
//        message.setText(new StringBuffer()
//                .append("<h3>가입을 시도한 본인 이메일이 맞는지 확인하고 있습니다.</h3>")
//                .append("<a href='http://localhost:8080/user/emailConfirm?email=")
//                .append(userDto.getEmail())
//                .append("&key=")
//                .append(key)
//                .append("' target='_blenk'>회원가입 완료를 위해 이곳을 눌러주세요.</a>").toString());
//        mailSender.send(message);

        // 메일 발송
        MailHandler mailHandler = new MailHandler(mailSender);

        mailHandler.setTo(userDto.getEmail());
        mailHandler.setFrom("dlatjsdn0622@naver.com");
        mailHandler.setSubject("회원가입 확인 메일입니다.");

        String htmlContent = "<h3>가입을 시도한 본인 이메일이 맞는지 확인하고 있습니다.</h3>"
                + "<a href='http://localhost:8080/user/emailConfirm?email=" + userDto.getEmail()
                + "&key=" + key
                + "' target='_blenk'>회원가입 완료를 위해 이곳을 눌러주세요.</a>";
        mailHandler.setText(htmlContent, true);

//        mailHandler.setText(new StringBuffer()
//                .append("<h3>가입을 시도한 본인 이메일이 맞는지 확인하고 있습니다.</h3>")
//                .append("<a href='http://localhost:8080/user/emailConfirm?email=")
//                .append(userDto.getEmail()).append("&key=").append(key)
//                .append("' target='_blenk'>회원가입 완료를 위해 이곳을 눌러주세요.</a>")
//                .toString());

        mailHandler.send();
    }

    // authstatus 1로 변경
    @Transactional
    public void updateAuthstatus(String email) throws Exception {
        userRepository.updateAuthstatus(email);
    }

    // 이메일 중복 체크
    @Transactional
    public int idChk(String email) throws Exception {
        int result = userRepository.idChk(email);
        return result;
    }


    // 회원가입
    @Transactional
    public Long joinUser(UserDto userDto) {
        // 비밀번호 암호화
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(userDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(userEmail);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    // 이메일로 id 찾기
    @Transactional
    public Long getUserId(String username) {
        Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(username);
        UserEntity userEntity = userEntityWrapper.get();

        UserDto userDto = UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();

        return userDto.getId();
    }
}