package com.jeuni.example.springboot.schedule;

import java.util.List;

//@Mapper
public interface EtcMapper {

    /* 월초 생성/발송 일정 세팅 가능 여부 */
    int checkNewScheduleValidation(String currYearMonth);

    /* 공휴일 목록 */
    List<EtcScheduleHolidayVO> selectEtcHolidayList();

    /* 월초 생성 일정 신규 등록 */
    void insertNewMonthCBSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 월초 발송 일정 신규 등록 */
    void insertNewMonthSVSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 월초 발송 일정 수정 */
    void modifyNewMonthSVSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 월초 발송 일정 조회 */
    List<EtcScheduleCalendarComVO> selectNewMonthSVSchedule();

    /* 월초 발송 일정 수정 - 원부 테이블 */
    void modifyNewMonthComSVSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 생성/발송 일정 업무별 목록 */
    List<EtcScheduleCalendarMainVO> selectEtcComScheduleListByJob();

    /* 생성/발송 일정 일별 목록 */
    List<EtcScheduleCalendarComVO> selectComScheduleListByDay(EtcScheduleByDayDto etcScheduleByDayDto);

    /* 발송 일정 일별 목록 - 원부 테이블 */
    List<EtcScheduleCalendarComVO> selectComSVScheduleListByDay(EtcScheduleByDayDto etcScheduleByDayDto);

    /* 생성/발송 작업이력 일별 목록 */
    List<EtcScheduleCalendarJobVO> selectBatchJobListByDay(EtcScheduleByDayDto etcScheduleByDayDto);

    /* 생성/발송 일정 수정 가능 여부 */
    int checkEtcScheduleValidation(EtcScheduleMainDto etcScheduleMainDto);

    /* 생성 일정 신규 등록 */
    void insertEtcCBSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 발송 일정 신규 등록 - 원부 테이블 */
    void insertEtcComSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 발송 일정 신규 등록 */
    void insertEtcSVSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 생성/발송 일정 수정 */
    void modifyEtcSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 발송 일정 수정 - 원부 테이블 */
    void modifyEtcComSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 생성/발송 일정 날짜 수정 */
    void modifyEtcScheduleDay(EtcScheduleMainDto etcScheduleMainDto);

    /* 발송 일정 날짜 수정 - 원부 테이블 */
    void modifyEtcComScheduleDay(EtcScheduleMainDto etcScheduleMainDto);

    /* 생성/발송 일정 삭제 */
    void removeEtcSchedule(EtcScheduleMainDto etcScheduleMainDto);

    /* 생성/발송 일정 삭제 - 원부 테이블 */
    void removeEtcComSchedule(EtcScheduleMainDto etcScheduleMainDto);
}
