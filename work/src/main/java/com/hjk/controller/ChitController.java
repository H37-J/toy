package com.hjk.controller;

import com.hjk.model.Chit;
import com.hjk.model.common.CommonResult;
import com.hjk.model.dto.ChitDto;
import com.hjk.service.ChitService;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/chit")
@RequiredArgsConstructor
public class ChitController {
    private final ChitService chitService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<Chit>> list() {
        return CommonResult.success(chitService.findAll());
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public CommonResult<List<ChitDto.Response>> update() throws CsvValidationException, IOException {
        return CommonResult.success(chitService.update());
    }
}
