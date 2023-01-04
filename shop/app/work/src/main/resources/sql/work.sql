SELECT * FROM 전표입력정보_리뉴얼

SELECT * FROM 가상테이블_엑셀업로드용2;

Select * from sys.databases;

alter database APT_TAX COLLATE Korean_Wansung_CI_AS;

create table dbo.가상테이블_엑셀업로드용2
(
    col01 varchar(200),
    col02 varchar(200),
    col03 varchar(200),
    col04 varchar(200),
    col05 varchar(200),
    col06 varchar(200),
    col07 varchar(200),
    col08 varchar(200),
    col09 varchar(200),
    col10 varchar(200),
    col11 varchar(200),
    col12 varchar(200),
    col13 varchar(200),
    col14 varchar(200),
    col15 varchar(200),
    col16 varchar(200),
    col17 varchar(200),
    col18 varchar(200),
    col19 varchar(200),
    col20 varchar(200),
    col21 varchar(200),
    col22 varchar(200),
    col23 varchar(200),
    col24 varchar(200),
    col25 varchar(200),
    col26 varchar(200),
    col27 varchar(200),
    col28 varchar(200),
    col29 varchar(200),
    col30 varchar(200),
    col31 varchar(200),
    col32 varchar(200),
    col33 varchar(200),
    col34 varchar(200),
    col35 varchar(200)
)
go

create table dbo.전표입력정보_리뉴얼
(
    단지코드       varchar(5)  not null,
    전표일자       varchar(8)  not null,
    전표번호       varchar(5)  not null,
    상세순번       varchar(5)  not null,
    표준계정코드     varchar(20),
    표준계정과목     varchar(30),
    계정코드       varchar(10),
    계정과목명      varchar(50),
    계정분류       varchar(2),
    적요         varchar(150),
    차대구분       varchar(2),
    금액         decimal(18, 2),
    증빙유형       varchar(2),
    사업자번호      varchar(20),
    상호         varchar(50),
    입력일자       datetime    not null,
    수정일자       datetime    not null,
    수정자        varchar(20) not null,
    입력구분       varchar     not null,
    수익사업여부     varchar(2),
    전세계여부      char,
    표준계정코드2014 varchar(10),
    기부금코드      varchar(2)
)
go

