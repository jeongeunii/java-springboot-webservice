package com.jeuni.example.springboot.web;

import com.jeuni.example.springboot.service.TctService;
import com.jeuni.example.springboot.web.dto.TctCountResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TableController {

    private final TctService tctService;

    @GetMapping("/api/v1/table/tctSamComs")
    public List<TctCountResponseDto> findCountOfTctSamComs(Model model) {
        List<TctCountResponseDto> tctCountResponseDtos = tctService.findCountOfTctSamComs();

        return tctCountResponseDtos;
    }

    @GetMapping("/api/v1/table/tctBillSoaps")
    public List<TctCountResponseDto> findCountOfTctBillSoaps(Model model) {
        List<TctCountResponseDto> tctCountResponseDtos = tctService.findCountOfTctBillSoaps();

        return tctCountResponseDtos;
    }

    @GetMapping("/api/v1/table/batchJobDetail/tot")
    public Long findTotCountOfBatchJobDetail(Model model) {
        Long totCountOfBatchJobDetail = tctService.findTotCountOfBatchJobDetail();

        return totCountOfBatchJobDetail;
    }

    @GetMapping("/api/v1/table/batchJobDetail")
    public Long findCOCountOfBatchJobDetail(Model model) {
        Long countOfBatchJobDetail = tctService.findCOCountOfBatchJobDetail();

        return countOfBatchJobDetail;
    }
}
