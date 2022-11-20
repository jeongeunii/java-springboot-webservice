package com.jeuni.example.springboot.schedule;

import java.util.List;
import java.util.Map;

public interface EtcService {

    /* 월초 생성/발송 일정 세팅 */
    int setNewMonthSchedule(String calYearMonth);

    /* 생성/발송 일정 업무별 목록 */
    List<Map<String, Object>> selectEtcScheduleListByJob();

    /* 생성/발송 일정 일별 목록 */
    List<EtcScheduleCalendarComVO> selectComScheduleListByDay(EtcScheduleByDayDto etcScheduleByDayDto);

    /* 생성/발송 작업이력 일별 목록 */
    List<EtcScheduleCalendarJobVO> selectBatchJobListByDay(EtcScheduleByDayDto etcScheduleByDayDto);

    /* 생성/발송 일정 신규 등록 */
    void insertEtcSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 생성/발송 일정 수정 */
    void modifyEtcSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 생성/발송 일정 날짜 수정 */
    int modifyEtcScheduleDay(EtcScheduleMainDto etcScheduleMainDto);

    /* 생성/발송 일정 삭제 */
    void removeEtcSchedule(EtcScheduleMainDto etcScheduleMainDto);
}
