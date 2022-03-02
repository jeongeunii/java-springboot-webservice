package com.jeuni.example.springboot.web;

import com.jeuni.example.springboot.service.JobsService;
import com.jeuni.example.springboot.web.dto.JobsListResponseDto;
import com.jeuni.example.springboot.web.dto.JobsSaveRequestDto;
import com.jeuni.example.springboot.web.dto.JobsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobsController {

    private final JobsService jobsService;

    @PostMapping("/api/v1/jobs")
    public Long save(@RequestBody JobsSaveRequestDto requestDto) {
        return jobsService.save(requestDto);
    }

    @GetMapping("/api/v1/jobs/{date}")
    public List<JobsListResponseDto> findByDate(@PathVariable String date, Model model) {
        List<JobsListResponseDto> jobsListResponseDtos = jobsService.findByDate(date);

        return jobsListResponseDtos;
    }

    @GetMapping("/api/v1/jobs/update/{id}")
    public JobsListResponseDto findById(@PathVariable Long id, Model model) {
        return jobsService.findById(id);
    }

    @PutMapping("/api/v1/jobs/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody JobsUpdateRequestDto requestDto) {
        return jobsService.update(id, requestDto);
    }
}
