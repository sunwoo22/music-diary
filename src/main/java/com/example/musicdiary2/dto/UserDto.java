package com.example.musicdiary2.dto;

import com.example.musicdiary2.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private LocalDate createdDate;
    private LocalDate modifiedDate;

    private String authkey; // 인증키
    private int authstatus; // 권한확인

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

}
