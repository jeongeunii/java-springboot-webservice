package com.jeuni.example.springboot.web;

import com.jeuni.example.springboot.domain.samples.Samples;
import com.jeuni.example.springboot.service.SamplesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SamplesController {

    private final SamplesService samplesService;

    @PostMapping("/api/v1/samples")
    public void save(@RequestBody List<Samples> samplesList) {
        samplesService.save(samplesList);
    }
}
