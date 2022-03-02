package com.jeuni.example.springboot.service;

import com.jeuni.example.springboot.domain.jobs.Jobs;
import com.jeuni.example.springboot.domain.jobs.JobsRepository;
import com.jeuni.example.springboot.web.dto.JobsListResponseDto;
import com.jeuni.example.springboot.web.dto.JobsSaveRequestDto;
import com.jeuni.example.springboot.web.dto.JobsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobsService {

    private final JobsRepository jobsRepository;

    @Transactional
    public List<JobsListResponseDto> findAllJobs() {
        return jobsRepository.findAllJobs().stream()
                .map(JobsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(JobsSaveRequestDto requestDto) {
        return jobsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<JobsListResponseDto> findByDate(String date) {
        return jobsRepository.findByDate(date).stream()
                .map(JobsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public JobsListResponseDto findById(Long id) {
        Jobs entity = jobsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 JOB이 없습니다. id=" + id));

        return new JobsListResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, JobsUpdateRequestDto requestDto) {
        Jobs jobs = jobsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 JOB이 없습니다. id=" + id));

        jobs.update(requestDto);

        return id;
    }
}
