package com.hjk.service;

import com.hjk.model.Chit;
import com.hjk.model.dto.ChitDto;
import com.hjk.repository.ChitRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChitService {
    CSVReader reader;
    @Autowired
    private ChitRepository chitRepository;
    public ChitService() throws IOException {
    }
    public List<Chit> findAll() {
        return chitRepository.findAll();
    }

    @Transactional
    public List<ChitDto.Response> update() throws CsvValidationException, IOException {
        reader = new CSVReader(new FileReader("./src/main/java/com/hjk/service/chit.csv"));
        List<ChitDto.Response> responseList = new ArrayList<>();

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            String 단지코드 = nextLine[0];
            String 전표일자 = nextLine[1];
            String 전표번호 = nextLine[2];
            String 상세순번 = nextLine[3];
            String 표준계정과목 = nextLine[4];

            Chit oldChit = chitRepository.get(단지코드, 전표일자, 전표번호, 상세순번);
            chitRepository.update(단지코드, 전표일자, 전표번호, 상세순번, 표준계정과목);
            Chit updatedChit = chitRepository.get(단지코드, 전표일자, 전표번호, 상세순번);

            ChitDto.Response response = updatedChit.toResponseDto();
            response.setResultMessage(oldChit.get표준계정과목());
            responseList.add(response);
        }

        reader.close();
        return responseList;
    }
}
