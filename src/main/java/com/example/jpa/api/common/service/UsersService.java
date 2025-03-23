package com.example.jpa.api.common.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.jpa.api.common.dto.UsersResponseDTO;
import com.example.jpa.api.common.entity.UsersEntity;
import com.example.jpa.api.common.repository.UsersRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UsersRepository usersRepository;

    /*
     * 단일 유저 조회
     * request
     * userId
     */
    @Transactional
    public UsersResponseDTO getUser(Integer userId) {

        // DB에서 사용자 조회해서 Entity 에 삽입
        // UsersEntity userEntity = usersRepository.findUser(userId);
        logger.info("[GET] getUser service 1 >>>>>>>>>");
        UsersEntity userEntity = usersRepository.findUser(userId);
        if (userEntity == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        // Entity -> DTO 변환 과정
        logger.info("[GET] getUser service 2 >>>>>>>>>");
        UsersResponseDTO userInfo = UsersResponseDTO.toDto(userEntity);

        return userInfo;
    }

    /*
     * 유저 리스트 조회
     * request
     * 
     */
    @Transactional
    public List<UsersResponseDTO> getUserList() {

        logger.info("[GET] getUserList service >>>>>>>>>");

        // DB에서 사용자 조회해서 Entity 에 삽입
        List<UsersEntity> usersList = usersRepository.findAll(); // DB에서 전체 조회

        // Entity -> DTO 변환 과정
        List<UsersResponseDTO> result = new ArrayList<>();
        for (UsersEntity user : usersList) {
            result.add(UsersResponseDTO.toDto(user));
        }

        return result;
    }

}
