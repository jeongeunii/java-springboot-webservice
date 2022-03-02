package com.jeuni.example.springboot.service;

import com.jeuni.example.springboot.domain.samples.Samples;
import com.jeuni.example.springboot.domain.samples.SamplesRepository;
import com.jeuni.example.springboot.web.dto.SamplesListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SamplesService {

    private final SamplesRepository samplesRepository;

    @Transactional
    public List<SamplesListResponseDto> findAllSamples() {
        return samplesRepository.findAllSamples().stream()
                .map(SamplesListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(List<Samples> samplesList) {
        samplesRepository.saveAll(samplesList);
    }
}
