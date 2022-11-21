package com.jeuni.example.springboot.inspect;

//@Mapper
public interface InspectMapper {

    /* 비동기 처리 쓰레드 */
    public void asyncInspect(InspectMainDto inspectMainDto);
}
