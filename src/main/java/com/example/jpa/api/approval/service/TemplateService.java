package com.example.jpa.api.approval.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jpa.api.approval.dto.TemplateInsertRequestDTO;
import com.example.jpa.api.approval.dto.TemplateResponseDTO;
import com.example.jpa.api.approval.dto.TemplateUpdateRequestDTO;
import com.example.jpa.api.approval.entity.TemplateEntity;
import com.example.jpa.api.approval.repository.TemplateRepository;
import com.example.jpa.api.common.dto.UsersResponseDTO;
import com.example.jpa.api.common.service.UsersService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final UsersService usersService;

    private final TemplateRepository templateRepository;

    /*
     * 단일 양식 조회
     * 반환타입 : TemplateResponseDto
     * request :
     * temId (필수)
     */
    @Transactional
    public TemplateResponseDTO getTemplate(Integer temId) {

        TemplateEntity templates = templateRepository.findTemplate(temId); // DB에서 전체 조회

        TemplateResponseDTO result = TemplateResponseDTO.toDto(templates);

        return result;
    }

    /*
     * 양식 리스트 조회
     * 반환타입 : List<TemplateResponseDto>
     * request :
     * temId, compId, deptId, searchType, searchKeyword, pageSize, pageNum
     */
    @Transactional
    public List<TemplateResponseDTO> getTemList() {

        List<TemplateEntity> templates = templateRepository.findAll(); // DB에서 전체 조회

        List<TemplateResponseDTO> result = new ArrayList<>();
        for (TemplateEntity temp : templates) {
            result.add(TemplateResponseDTO.toDto(temp));
        }

        return result;
    }

    /*
     * 양식 등록
     * 반환타입 : void
     * request : TemplateInsertRequestDto
     * compId, deptId, temNm(필수), temDescription(필수), temContents(필수),
     * temStatus(필수),
     * createId(필수), createCompId, createDeptId, createDt
     */
    @Transactional
    public void postTemlate(TemplateInsertRequestDTO request) {

        // 유저 정보 가져오기
        UsersResponseDTO user = usersService.getUser(request.getCreateId());
        request.setCreateCompId(user.getCompId());
        request.setCreateDeptId(user.getDeptId());

        TemplateEntity templateEntity = TemplateEntity.toEntityInsert(request);
        templateRepository.save(templateEntity);
    }

    /*
     * 양식 수정
     * 반환타입 : void
     * request :
     * compId(필수), deptId(필수), temId(필수), temNm(필수), temDescription(필수),
     * temContents(필수), temStatus(필수),
     * updateId(필수), updateCompId, updateDeptId
     */
    @Transactional
    public void putTemplate(TemplateUpdateRequestDTO request) {

        // 유저 정보 가져오기
        UsersResponseDTO user = usersService.getUser(request.getUpdateId());
        request.setUpdateCompId(user.getCompId());
        request.setUpdateDeptId(user.getDeptId());

        // 수정할 양식 가져오기
        TemplateEntity templateEntity = templateRepository.findById(request.getTemId())
                .orElseThrow(() -> new RuntimeException("양식을 찾을 수 없습니다."));

        // 수정할 필드만 받아온 값으로 업데이트 ( create 관련 내용들이 nullable = false 라서 해당 부분 추가 )
        templateEntity.setCompId(request.getCompId());
        templateEntity.setDeptId(request.getDeptId());
        templateEntity.setTemId(request.getTemId());
        templateEntity.setTemNm(request.getTemNm());
        templateEntity.setTemDescription(request.getTemDescription());
        templateEntity.setTemContents(request.getTemContents());
        templateEntity.setTemStatus(request.getTemStatus());
        templateEntity.setUpdateId(request.getUpdateId());
        templateEntity.setUpdateCompId(request.getUpdateCompId());
        templateEntity.setUpdateDeptId(request.getUpdateDeptId());

        templateRepository.save(templateEntity);

    }

    /*
     * 양식 미사용 처리
     * 반환타입 : void
     * request :
     * temId(필수)
     */
    public void patchTemplate(Integer temId) {

        // 수정할 양식 가져오기
        TemplateEntity templateEntity = templateRepository.findById(temId)
                .orElseThrow(() -> new RuntimeException("양식을 찾을 수 없습니다."));

        // 기존 상태값 가져와서 수정
        Integer status = templateEntity.getTemStatus() == 0 ? 1 : 0;
        templateEntity.setTemStatus(status);

        templateRepository.save(templateEntity);
    }

    /*
     * 양식 삭제
     * 반환타입 : void
     * request :
     * temId(필수)
     */
    public void deleteTemplate(Integer temId) {

        templateRepository.deleteById(temId);

    }

}
