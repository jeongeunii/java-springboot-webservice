package com.jeuni.example.springboot.inspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class InspectService {

    private final InspectMapper inspectMapper;
    private final AsyncService asyncService;

    public InspectService(final InspectMapper inspectMapper, final AsyncService asyncService) {
        this.inspectMapper = inspectMapper;
        this.asyncService = asyncService;
    }

    /* 비동기 처리 쓰레드 */
    @Transactional
    public void asyncInspect(InspectMainDto inspectMainDto) {
        ArrayList<CompletableFuture<Integer>> result = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            result.add(asyncService.asyncInspect(InspectMainDto.builder().seq(i).build()));
        }

        for (CompletableFuture<Integer> c : result) {
            log.info("### Async inspect success : " + Integer.toString(c.join()));
        }
    }
}
