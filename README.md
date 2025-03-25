# demo-project-JPA-

<br />


📝 프로젝트 개요
--- 
개인 포트폴리오 용 프로젝트로

현재 전자결재 프로그램을 간략하게 구축 진행중이며,

기능들을 덧붙이며 규모를 키워갈 예정입니다.

<br />

🛠 사용 기술
---
Springboot 3.4.3, JPA, RESTful API
java 17, MySQL

<br />


폴더 구조 및 파일 명 작성법
---
main

 ├── java/com/example/demo
 
 │   ├── 기능
 
 │   │   ├── controller
 
 │   │   │   ├── 기능Controller.java    ( REST API 컨트롤러 )
 
 │   │   ├── service
 
 │   │   │   ├── 기능Service.java       ( 서비스 로직 처리 )
  
 │   │   ├── repository
 
 │   │   │   ├── 기능Repository.java    ( SQL 처리 )
  
 │   │   ├── entity
 
 │   │   │   ├── 기능Entity.java        ( DB 테이블 매핑 )
 
 │   │   ├── dto
 
 │   │   │   ├── 기능RequestDto.java    ( 요청 DTO )
 
 │   │   │   ├── 기능ResponseDto.java    ( 응답 DTO )


<br />


메서드 명 작성법
---
HTTP 메서드 + 구현하고자 하는 핵심 내용

ex)
- 사용자들 정보 출력 : getUserList()
- 사용자 등록 : postUser()
- 사용자 수정 : putUser() / patchUser()
- 사용자 삭제 : deleteUser()


<br />


API 현황
---

[ 공통부분 ]
사용자
- 사용자 정보 조회 API (완료)

[ 전자결재 ]
양식
- 양식 조회 API (완료)
- 양식 생성 API (완료)
- 양식 수정 API (완료)
- 양식 삭제 API (완료)

문서
- 문서 조회 API
- 문서 생성 API
- 문서 수정 API
- 문서 삭제 API

결재라인
- 결재라인 조회 API
- 결재라인 생성 API
- 결재라인 수정 API
- 결재라인 삭제 API


<br />

테이블 구조
---

```
-- 회사 정보 테이블
CREATE TABLE COMPANY (
    comp_id INT AUTO_INCREMENT PRIMARY KEY,         -- 회사 고유 아이디
    comp_nm VARCHAR(100) NOT NULL,                  -- 회사 이름
    comp_status INT DEFAULT 0                       -- 회사 상태 ( 0:사용중 / 1:삭제 )   
)

-- 부서 정보 테이블
CREATE TABLE DEPARTMENT (
    comp_id INT NOT NULL,                           -- 소속 회사 아이디
    dept_id INT NOT NULL,                           -- 부서 고유 아이디
    dept_nm VARCHAR(100) NOT NULL,                  -- 부서 이름
    dept_status INT DEFAULT 0,                      -- 부서 상태 ( 0:사용중 / 1:삭제 )
    PRIMARY KEY (comp_id, dept_id),
    FOREIGN KEY (comp_id) REFERENCES COMPANY(comp_id)
)


-- 유저 정보 테이블
CREATE TABLE USERS (
    comp_id INT NOT NULL,                           -- 사용자 소속 회사
    dept_id INT NOT NULL,                           -- 사용자 소속 부서
    user_id INT AUTO_INCREMENT PRIMARY KEY,         -- 사용자 아이디
    user_nm VARCHAR(100) NOT NULL,                  -- 사용자 이름
    user_pw VARCHAR(255) NOT NULL,                  -- 사용자 비밀번호
    rank_id INT NOT NULL,                           -- 사용자 직급
    user_email VARCHAR(100) NOT NULL,               -- 사용자 이메일
    user_dt DATETIME DEFAULT CURRENT_TIMESTAMP,     -- 사용자 가입일
    user_role INT DEFAULT 0,                        -- 사용자 권한 ( 0:일반사용자 / 1:관리자 )
    user_status INT DEFAULT 0                       -- 사용자 재직상태 ( 0:재직 / 1:휴직 / 2:퇴직 )
)

-- 문서 양식 정보 테이블
CREATE TABLE TEMPLATES (
    comp_id INT NOT NULL,                           -- 양식 소속 회사
    dept_id INT NOT NULL,                           -- 양식 소속 부서
    tem_id INT AUTO_INCREMENT PRIMARY KEY,          -- 양식 아이디
    tem_nm VARCHAR(100) NOT NULL,                   -- 양식 이름
    tem_description TEXT,                           -- 양식 설명
    tem_contents TEXT NOT NULL,                     -- 양식 내용
    tem_status INT NOT NULL,                        -- 양식 상태 ( 0:사용중 / 1:삭제 )
    create_id INT NOT NULL,                         -- 양식 생성자 아이디
    create_comp_id INT NOT NULL,                    -- 양식 생성자 소속 회사
    create_dept_id INT NOT NULL,                    -- 양식 생성자 소속 부서
    create_dt DATETIME DEFAULT CURRENT_TIMESTAMP    -- 양식 생성일
    update_id INT NOT NULL,                         -- 양식 수정자 아이디
    update_comp_id INT NOT NULL,                    -- 양식 수정자 소속 회사
    update_dept_id INT NOT NULL,                    -- 양식 수정자 소속 부서
    update_dt DATETIME DEFAULT CURRENT_TIMESTAMP    -- 양식 수정일
)

-- 문서 정보 테이블
CREATE TABLE DOCUMENTS (
    comp_id INT NOT NULL,                           -- 문서 소속 회사
    dept_id INT NOT NULL,                           -- 문서 소속 부서
    create_id INT NOT NULL,                         -- 문서 생성자 아이디
    create_comp_id INT NOT NULL,                    -- 문서 생성자 소속 회사
    create_dept_id INT NOT NULL,                    -- 문서 생성자 소속 부서
    doc_id VARCHAR(10) NOT NULL PRIMARY KEY,        -- 문서 고유 아이디 - '올해년도 + 시퀀스' 형태 ( ex 2025000001 총 10자리)
    doc_title VARCHAR(255) NOT NULL,                -- 문서 제목
    doc_contents TEXT NOT NULL,                     -- 문서 내용
    doc_status INT NOT NULL,                        -- 문서 결재 상태 ( 000:임시저장 / 100:결재중 / 200:결재완료 / 400:결재반려 / 800:문서삭제 )
    create_dt DATETIME DEFAULT CURRENT_TIMESTAMP,   -- 문서 생성일
    doc_secret INT DEFAULT 0                        -- 보안문서 여부 ( 0:일반문서 / 1:보안문서 )
)

-- 문서 결재라인 테이블
CREATE TABLE SIGNLINE (
    sign_id INT AUTO_INCREMENT PRIMARY KEY,         -- 결재 고유 아이디
    doc_id VARCHAR(10) NOT NULL,                    -- 결재 문서 아이디
    comp_id INT NOT NULL,                           -- 결재자 회사
    dept_id INT NOT NULL,                           -- 결재자 부서서
    user_id INT NOT NULL,                           -- 결재자 아이디
    sign_no INT NOT NULL,                           -- 결재 순서
    sign_status INT DEFAULT 000,                    -- 결재 상태 ( 000:결재대기 / 100:결재중 / 200:결재완료 / 400:결재반려 )
    sign_dt DATETIME NULL,                          -- 결재 시간 ( NULL이면 미결재 )
    FOREIGN KEY (doc_id) REFERENCES DOCUMENTS(doc_id)
)
```










 
