package com.jeuni.example.springboot.service;

import com.jeuni.example.springboot.domain.TCT.TctRepository;
import com.jeuni.example.springboot.web.dto.TctCountResponseDto;
import com.jeuni.example.springboot.web.dto.TctResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TctService {

    private final TctRepository tctRepository;

    @Transactional
    public List<TctResponseDto> findAllTctSamComs() {
        return tctRepository.findAllTctSamComs().stream()
                .map(TctResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TctCountResponseDto> findCountOfTctSamComs() {
        return tctRepository.findCountOfTctSamComs().stream()
                .map(TctCountResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TctCountResponseDto> findCountOfTctBillSoaps() {
        return tctRepository.findCountOfTctBillSoaps().stream()
                .map(TctCountResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long findTotCountOfTctSamComs(){
        return tctRepository.findTotCountOfTctSamComs();
    }

    public Long findTotCountOfTctBillSoaps(){
        return tctRepository.findTotCountOfTctBillSoaps();
    }

    public Long findTotCountOfBatchJobDetail(){
        return tctRepository.findTotCountOfBatchJobDetail();
    }

    public Long findCOCountOfBatchJobDetail(){
        return tctRepository.findCOCountOfBatchJobDetail();
    }
}
