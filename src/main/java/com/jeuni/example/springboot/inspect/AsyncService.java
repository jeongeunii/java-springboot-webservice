package com.jeuni.example.springboot.inspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

    private final InspectMapper inspectMapper;

    public AsyncService(final InspectMapper inspectMapper) {
        this.inspectMapper = inspectMapper;
    }

    /**
     * 비동기 처리 쓰레드와 해당 쓰레드 호출하는 로직이 같은 파일내에 있으면 안됨
     * 비동기 처리 쓰레드 정의 - AsyncService
     * 비동기 처리 쓰레드 호출 - InspectService
     */
    @Async("asyncTaskExecutor")
    public CompletableFuture<Integer> asyncInspect(InspectMainDto inspectMainDto) {
        log.info("### Async inspect start : " + inspectMainDto.getSeq());
        inspectMapper.asyncInspect(inspectMainDto);
        return CompletableFuture.completedFuture(inspectMainDto.getSeq());
    }
}
