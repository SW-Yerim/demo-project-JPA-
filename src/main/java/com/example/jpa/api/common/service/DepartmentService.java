package com.example.jpa.api.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.jpa.api.board.dto.BoardDTO;
import com.example.jpa.api.board.entity.BoardEntity;
import com.example.jpa.api.common.dto.DepartmentResponseDTO;
import com.example.jpa.api.common.entity.DepartmentEntity;
import com.example.jpa.api.common.entity.DepartmentPkEntity;
import com.example.jpa.api.common.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DepartmentRepository departmentRepository;

    /*
     * 단일 부서 조회
     * request
     * deptId (필수)
     */
    @Transactional
    public DepartmentResponseDTO getDepartment(Integer compId, Integer deptId) {

        // deptartment 는 복합키로 구성되어있어 복합키 객체 생성
        logger.info("[GET] getDepartment service 1 >>>>>>>>>");
        DepartmentPkEntity departmentPk = new DepartmentPkEntity(compId, deptId);
        Optional<DepartmentEntity> optionalEntity = departmentRepository.findById(departmentPk);

        if (optionalEntity.isPresent()) {
            // Entity -> DTO 변환 과정
            logger.info("[GET] getDepartment service 2 >>>>>>>>>");
            DepartmentEntity entity = optionalEntity.get();
            logger.info("[GET] getDepartment service 3 >>>>>>>>>");
            DepartmentResponseDTO result = DepartmentResponseDTO.toDto(entity);
            return result;
        } else {
            return null;
        }
    }

    /*
     * 부서 리스트 조회
     * request
     * 
     */
    @Transactional
    public List<DepartmentResponseDTO> getDeptList() {
        // DB에서 사용자 조회해서 Entity 에 삽입
        List<DepartmentEntity> optionalEntity = departmentRepository.findAll();
        List<DepartmentResponseDTO> result = new ArrayList<>();

        // Entity -> DTO 변환 과정
        for (DepartmentEntity entity : optionalEntity) {
            result.add(DepartmentResponseDTO.toDto(entity));
        }

        return result;
    }

}
