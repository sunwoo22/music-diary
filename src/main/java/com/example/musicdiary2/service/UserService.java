package com.example.musicdiary2.service;

import com.example.musicdiary2.domain.Role;
import com.example.musicdiary2.domain.entity.UserEntity;
import com.example.musicdiary2.domain.repository.UserRepository;
import com.example.musicdiary2.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Transactional
    public Long joinUser(UserDto userDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

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

    @Transactional
    public UserDto getPost(Long id) {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(id);
        UserEntity userEntity = userEntityWrapper.get();

        UserDto userDto = UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();

        return userDto;
    }
}