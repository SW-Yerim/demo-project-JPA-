package com.example.jpa.api.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.jpa.api.common.dto.CompanyResponseDTO;
import com.example.jpa.api.common.entity.CompanyEntity;
import com.example.jpa.api.common.repository.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CompanyRepository companyRepository;

    /*
     * 단일 회사 조회
     * request
     * compId (필수)
     */
    @Transactional
    public CompanyResponseDTO getCompany(Integer compId) {
        // DB에서 사용자 조회해서 Entity 에 삽입
        Optional<CompanyEntity> optionalEntity = companyRepository.findById(compId);

        if (optionalEntity.isPresent()) {
            // Entity -> DTO 변환 과정
            CompanyEntity entity = optionalEntity.get();
            CompanyResponseDTO result = CompanyResponseDTO.toDto(entity);
            return result;
        } else {
            throw new EntityNotFoundException("회사를 찾을 수 없습니다: " + compId);
        }
    }

    /*
     * 회사 리스트 조회
     * request
     * 
     */
    @Transactional
    public List<CompanyResponseDTO> getCompList() {
        // DB에서 사용자 조회해서 Entity 에 삽입
        List<CompanyEntity> optionalEntity = companyRepository.findAll();
        List<CompanyResponseDTO> result = new ArrayList<>();

        // Entity -> DTO 변환 과정
        for (CompanyEntity entity : optionalEntity) {
            result.add(CompanyResponseDTO.toDto(entity));
        }

        return result;
    }

}
