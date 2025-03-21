package com.example.jpa.api.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpa.api.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // UPDATE board_table SET board_hits=board_hits+1 WHERE id=?
    @Modifying
    @Query(value = "UPDATE BoardEntity b " +
            "SET b.boardHits = b.boardHits + 1 " +
            "WHERE b.id=:id") // Entity로 쿼리 작성
    void updateHits(@Param("id") Long id);

}
