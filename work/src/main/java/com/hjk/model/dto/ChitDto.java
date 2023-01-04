package com.hjk.model.dto;

import com.hjk.model.Chit;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChitDto {

    @Getter
    @NoArgsConstructor
    public static class updateRequestDto {
        private String 단지코드;
        private String 전표일자;
        private String 전표번호;
        private String 상세순번;
        private String 표준계정과목;

        public Chit toEntity() {
            return Chit.builder()
                    .단지코드(this.단지코드)
                    .전표일자(this.전표일자)
                    .전표번호(this.전표번호)
                    .상세순번(this.상세순번)
                    .표준계정과목(this.표준계정과목)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response {

        private String 단지코드;

        private String 전표일자;

        private String 전표번호;

        private String 상세순번;

        private String 표준계정과목;

        public Response(Chit chit) {
            this.단지코드 = chit.get단지코드();
            this.전표일자 = chit.get전표일자();
            this.전표번호 = chit.get전표번호();
            this.상세순번 = chit.get상세순번();
            this.표준계정과목 = chit.get표준계정과목();
        }

        @Builder
        public Response(String 단지코드, String 전표일자, String 전표번호, String 상세순번, String 표준계정과목) {
            this.단지코드 = 단지코드;
            this.전표일자 = 전표일자;
            this.전표번호 = 전표번호;
            this.상세순번 = 상세순번;
            this.표준계정과목 = 표준계정과목;
        }
    }
}
