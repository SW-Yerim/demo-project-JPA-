package com.example.jpa.api.approval.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jpa.api.approval.dto.TemplateInsertRequestDTO;
import com.example.jpa.api.approval.dto.TemplateResponseDTO;
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
     * 반환타입 : int ( 0:실패 / 1:성공 )
     * request : TemplateInsertRequestDto
     * compId, deptId, temNm(필수), temDescription(필수), temContents(필수),
     * temStatus(필수),
     * createId(필수), createCompId, createDeptId, createDt
     */
    public int postTemlate(TemplateInsertRequestDTO request) {

        // 유저 정보 가져오기
        UsersResponseDTO user = usersService.getUser(request.getCreateId());
        request.setCreateCompId(user.getCompId());
        request.setCreateDeptId(user.getDeptId());

        TemplateEntity templateEntity = TemplateEntity.toEntity(request);
        templateRepository.save(templateEntity);

        return 1;
    }

    /*
     * 양식 수정
     * 반환타입 : int ( 0:실패 / 1:성공 )
     * request :
     * compId(필수), deptId(필수), temId(필수), temNm(필수), temDescription(필수),
     * temContents(필수), temStatus(필수),
     * updateId(필수), updateCompId, updateDeptId
     */
    // public int putTem(TemplateInsertRequestDTO request, UsersResponseDTO
    // userInfo) {

    // }

    /*
     * 양식 임시 삭제
     * 반환타입 : int ( 0:실패 / 1:성공 )
     * request :
     * compId(필수), deptId(필수), temId(필수), temNm(필수), temDescription(필수),
     * temContents(필수), temStatus(필수),
     * updateId(필수), updateCompId, updateDeptId
     */
    // public int patchTem(TemplateInsertRequestDTO request, UsersResponseDTO
    // userInfo) {

    // }

    /*
     * 양식 삭제
     * 반환타입 : int ( 0:실패 / 1:성공 )
     * request :
     * temId(필수)
     */
    // public int postTem(TemplateInsertRequestDTO request, UsersResponseDto
    // userInfo) {

    // }

}
