package com.jeuni.example.springboot.schedule;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EtcServiceImpl implements EtcService {

    private EtcMapper etcMapper;

    /* 월초 생성/발송 일정 세팅 */
    @Override
    public int setNewMonthSchedule(String calYearMonth) {
        int result = 0;
        int nowMonth = LocalDate.now().getMonthValue();

        if (etcMapper.checkNewScheduleValidation(calYearMonth) == 0) {
            if (Integer.parseInt(calYearMonth.substring(4)) == nowMonth) {
                insertNewMonthSchedule();
                result = 1;
            }
        } else {
            result = 2;
        }

        return result;
    }

    /* 월초 생성/발송 일정 등록 */
    public void insertNewMonthSchedule() {
        EtcScheduleMainDto etcScheduleCBDto = null;
        EtcScheduleMainDto etcScheduleSVDto = null;
        ArrayList<String> validDateList = getValidDateList();
        String now = LocalDate.now().toString();
        String date = null;
        int year = Integer.parseInt(now.substring(0, 4));
        int month = Integer.parseInt(now.substring(5, 7));
        int taskStartDay = 0;
        int period = 0;
        boolean isCBSchedule = false;

        /* 지난달 발송 일정 등록 */
        etcMapper.insertNewMonthCBSchedule(EtcScheduleMainDto.builder().build());

        /* 월초 스케줄 자동화 로직 */
        String[][] schedule = {{"SMT", "8", "6", "1"}, {"WIT", "6", "5", "1"}};

        for (String[] sch : schedule) {
            taskStartDay = Integer.parseInt(sch[1]);
            period = Integer.parseInt(sch[2]);
            date = LocalDate.of(year, month, taskStartDay).toString().replace("-", "");
            for (int j = 0; j < validDateList.size(); j++) {
                if (Integer.parseInt(validDateList.get(j)) >= Integer.parseInt(date)) {
                    for (int k = j, l = 0; k < j + period; k++, l++) {
                        // ...
                        isCBSchedule = false;

                        if (sch[0].equals(JobSe.SMT.getCode())) {
                            isCBSchedule = true;
                            // ...
                        }

                        if (isCBSchedule) {
                            etcScheduleCBDto = EtcScheduleMainDto.builder().jobSe(sch[0]).taskStartDay(validDateList.get(k))
                                    .taskEndDay(validDateList.get(k)).holidayYn("N").build();
                            etcMapper.insertNewMonthCBSchedule(etcScheduleCBDto);
                        }

                        etcScheduleSVDto = EtcScheduleMainDto.builder().jobSe(sch[0]).taskSn(Integer.toString(Integer.parseInt(sch[3]) + l))
                                .taskStartDay(validDateList.get(k)).taskEndDay(validDateList.get(k)).build();
                        etcMapper.modifyNewMonthSVSchedule(etcScheduleSVDto);
                    }
                    break;
                }
            }
        }

        /* 월초 발송 일정 목록 */
        List<EtcScheduleCalendarComVO> SVScheduleList = etcMapper.selectNewMonthSVSchedule();

        for (EtcScheduleCalendarComVO comVO : SVScheduleList) {
            if (comVO.getTaskStartDay().length() != 0 && comVO.getTaskEndDay().length() != 0) {
                etcScheduleSVDto = EtcScheduleMainDto.builder().scheSn(comVO.getScheSn())
                        .taskStartDay(comVO.getTaskStartDay()).taskEndDay(comVO.getTaskEndDay()).build();
                /* 월초 발송 일정 세팅 - 원부 테이블 */
                etcMapper.modifyNewMonthSVSchedule(etcScheduleSVDto);
            }
        }
    }

    /* 영업일 기준 일자 목록 */
    public ArrayList<String> getValidDateList() {
        ArrayList<String> validDateList = new ArrayList<>();
        LocalDate now = LocalDate.now();

        /* 공휴일 목록 */
        List<EtcScheduleHolidayVO> holidaySelectList = etcMapper.selectEtcHolidayList();
        List<LocalDate> holidayList = new ArrayList<>();
        LocalDate date = null;
        String holidayDate = "";

        for (EtcScheduleHolidayVO holiday : holidaySelectList) {
            if (holiday.getHolidayDt().length() != 0 && holiday.getHolidayNm().length() != 0) {
                holidayDate = holiday.getHolidayDt();
                int year = Integer.parseInt(holidayDate.substring(0, 4));
                int month = Integer.parseInt(holidayDate.substring(4, 6));
                int day = Integer.parseInt(holidayDate.substring(6));

                holidayList.add(LocalDate.of(year, month, day));
            }
        }

        for (int i = 1; i <= now.lengthOfMonth(); i++) {
            date = LocalDate.of(now.getYear(), now.getMonthValue(), i);
            String day = date.getDayOfWeek().toString();

            if (day.equals("SATURDAY") || day.equals("SUNDAY") || holidayList.contains(date)) {
                continue;
            }
            validDateList.add(date.toString().replace("-", ""));
        }

        return validDateList;
    }

    /* 생성/발송 일정 업무별 목록 */
    @Override
    public List<Map<String, Object>> selectEtcScheduleListByJob() {
        List<EtcScheduleCalendarMainVO> scheduleList = etcMapper.selectEtcComScheduleListByJob();

        List<Map<String, Object>> calendarList = new ArrayList<>();
        HashMap<String, Object> event = null;

        for (EtcScheduleCalendarMainVO sche : scheduleList) {
            event = new HashMap<>();
            if (sche.getJobSe().length() != 0 && sche.getTaskCode().length() != 0
            && sche.getTaskStartDay().length() != 0 && sche.getTaskEndDay().length() != 0) {
                event.put("title", sche.getJobSe() + " - " + sche.getTaskCode());
                event.put("start", sche.getTaskStartDay());
                event.put("taskStartDay", sche.getTaskStartDay());
                event.put("taskEndDay", sche.getTaskEndDay());

                if (sche.getTaskCode().equals(TaskCode.CB.getCode())) {
                    event.put("color", "#F8E0E0");
                } else if (sche.getTaskCode().equals(TaskCode.SV.getCode())) {
                    event.put("color", "#E0ECF8");
                }
            }
            calendarList.add(event);
        }

        /* 공휴일 목록 */
        List<EtcScheduleHolidayVO> holidayList = etcMapper.selectEtcHolidayList();

        for (EtcScheduleHolidayVO holiday : holidayList) {
            event = new HashMap<>();
            if (holiday.getHolidayDt().length() != 0 && holiday.getHolidayNm().length() != 0) {
                event.put("title", holiday.getHolidayNm());
                event.put("color", "#E6E6E6");
                event.put("start", holiday.getHolidayDt());
                event.put("code", "holiday");
            }
            calendarList.add(event);
        }

        return calendarList;
    }

    /* 생성/발송 일정 일별 목록 */
    @Override
    public List<EtcScheduleCalendarComVO> selectComScheduleListByDay(EtcScheduleByDayDto etcScheduleByDayDto) {
        String currYearMonth = LocalDate.now().toString().replace("-", "").substring(0, 6);
        List<EtcScheduleCalendarComVO> scheduleList = null;

        if (etcScheduleByDayDto.getTaskCode().equals(TaskCode.SEND_VMG.getCode())
                && etcScheduleByDayDto.getTaskStartDay().substring(0, 6).equals(currYearMonth)) {
            /* 이번달 발송 일정 일별 목록 - 원부 테이블 */
            scheduleList = etcMapper.selectComSVScheduleListByDay(etcScheduleByDayDto);
        } else {
            scheduleList = etcMapper.selectComScheduleListByDay(etcScheduleByDayDto);
        }

        return scheduleList;
    }

    /* 생성/발송 작업이력 일별 목록 */
    @Override
    public List<EtcScheduleCalendarJobVO> selectBatchJobListByDay(EtcScheduleByDayDto etcScheduleByDayDto) {
        return etcMapper.selectBatchJobListByDay(etcScheduleByDayDto);
    }

    /* 생성/발송 일정 신규 등록 */
    @Override
    public void insertEtcSchedule(EtcScheduleMainDto etcScheduleMainDto) {
        if (etcScheduleMainDto.getTaskCode().equals(TaskCode.CREATE_BILL.getCode())) {
            etcMapper.insertEtcCBSchedule(etcScheduleMainDto);
        } else if (etcScheduleMainDto.getTaskCode().equals(TaskCode.SEND_VMG.getCode())) {
            /* 발송 일정 신규 등록 - 원부 테이블 */
            etcMapper.insertEtcComSchedule(etcScheduleMainDto);
            etcMapper.insertEtcSVSchedule(etcScheduleMainDto);
        }
    }

    /* 생성/발송 일정 수정 */
    @Override
    public void modifyEtcSchedule(EtcScheduleMainDto etcScheduleMainDto) {
        if (etcScheduleMainDto.getScheSeq() != 0) {
            etcMapper.modifyEtcSchedule(etcScheduleMainDto);

            if (etcScheduleMainDto.getTaskCode().equals(TaskCode.SEND_VMG.getCode()) && etcScheduleMainDto.getScheSn() != 0) {
                /* 발송 일정 수정 - 원부 테이블 */
                etcMapper.modifyEtcComSchedule(etcScheduleMainDto);
            }
        }
    }

    /* 생성/발송 일정 날짜 수정 */
    @Override
    public int modifyEtcScheduleDay(EtcScheduleMainDto etcScheduleMainDto) {
        int result = 0;

        if (etcMapper.checkEtcScheduleValidation(etcScheduleMainDto) == 0) {
            etcMapper.modifyEtcScheduleDay(etcScheduleMainDto);

            if (etcScheduleMainDto.getTaskCode().equals(TaskCode.SEND_VMG.getCode())) {
                /* 발송 일정 날짜 수정 - 원부 테이블 */
                etcMapper.modifyEtcComScheduleDay(etcScheduleMainDto);
            }
            result = 1;
        }

        return result;
    }

    /* 생성/발송 일정 삭제 */
    @Override
    public void removeEtcSchedule(EtcScheduleMainDto etcScheduleMainDto) {
        if (etcScheduleMainDto.getScheSeq() != 0) {
            etcMapper.removeEtcSchedule(etcScheduleMainDto);

            if (etcScheduleMainDto.getTaskCode().equals(TaskCode.SEND_VMG.getCode())) {
                /* 발송 일정 삭제 - 원부 테이블 */
                etcMapper.removeEtcComSchedule(etcScheduleMainDto);
            }
        }
    }
}
