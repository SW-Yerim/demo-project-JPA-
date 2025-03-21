package com.example.jpa.api.common.dto;

import java.time.LocalDateTime;

import com.example.jpa.api.common.entity.UsersEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponseDTO {

    private Integer compId; // 사용자 소속 회사
    private String compNm; // 사용자 소속 회사 이름
    private Integer deptId; // 사용자 소속 부서
    private String deptNm; // 사용자 소속 부서 이름
    private Integer userId; // 사용자 아이디
    private String userNm; // 사용자 이름
    private String userPw; // 사용자 비밀번호
    private Integer rankId; // 사용자 직급
    private String userEmail; // 사용자 이메일

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime userDt; // 사용자 가입일

    private Integer userRole; // 사용자 권한 ( 0:일반사용자 / 1:관리자 )
    private Integer userStatus; // 사용자 재직상태 ( 0:재직 / 1:휴직 / 2:퇴직 )

    // Entity -> DTO 변환 생성자 추가
    public static UsersResponseDTO toDto(UsersEntity entity) {
        return UsersResponseDTO.builder()
                .compId(entity.getCompId())
                .compNm(entity.getCompany().getCompNm())
                .deptId(entity.getDeptId())
                .deptNm(entity.getDepartment().getDeptNm())
                .userId(entity.getUserId())
                .userNm(entity.getUserNm())
                .rankId(entity.getRankId())
                .userEmail(entity.getUserEmail())
                .userDt(entity.getUserDt())
                .userRole(entity.getUserRole())
                .userStatus(entity.getUserStatus())
                .build();
    }

}
