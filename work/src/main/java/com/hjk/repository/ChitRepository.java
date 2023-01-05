package com.hjk.repository;

import com.hjk.model.Chit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChitRepository extends JpaRepository<Chit, Long> {

    @Query("select c from Chit c where c.단지코드 = :단지코드 and c.전표일자 = :전표일자 and c.전표번호 = :전표번호 and c.상세순번 = :상세순번")
    Chit get(@Param("단지코드") String 단지코드, @Param("전표일자") String 전표일자, @Param("전표번호") String 전표번호, @Param("상세순번") String 상세순번);
    @Transactional
    @Modifying
    @Query("update Chit c set c.표준계정과목 = :표준계정과목 where c.단지코드 = :단지코드 and c.전표일자 = :전표일자 and c.전표번호 = :전표번호 and c.상세순번 = :상세순번")
    void update(@Param("단지코드") String 단지코드, @Param("전표일자") String 전표일자, @Param("전표번호") String 전표번호, @Param("상세순번") String 상세순번, @Param("표준계정과목") String 표준계정과목);

}
