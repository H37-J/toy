package com.hjk.model;

import com.hjk.model.common.Base;
import com.hjk.model.dto.ChitDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(name = "전표입력정보_리뉴얼")
@NoArgsConstructor
public class Chit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "단지코드")
    private String 단지코드;

    @Column(name = "전표일자")
    private String 전표일자;

    @Column(name = "전표번호")
    private String 전표번호;

    @Column(name = "상세순번")
    private String 상세순번;

    @Column(name = "표준계정과목")
    private String 표준계정과목;

    @Builder
    public Chit(String 단지코드, String 전표일자, String 전표번호, String 상세순번, String 표준계정과목) {
        this.단지코드 = 단지코드;
        this.전표일자 = 전표일자;
        this.전표번호 = 전표번호;
        this.상세순번 = 상세순번;
        this.표준계정과목 = 표준계정과목;
    }

    public ChitDto.Response toResponseDto() {
        return ChitDto.Response.builder()
                .단지코드(this.단지코드)
                .전표일자(this.전표일자)
                .전표번호(this.전표번호)
                .상세순번(this.상세순번)
                .표준계정과목(this.표준계정과목)
                .build();
    }

    public static List<ChitDto.Response> toResponseDtoList(List<Chit> chits) {
        return chits.stream().map(ChitDto.Response::new).collect(Collectors.toList());
    }
}
