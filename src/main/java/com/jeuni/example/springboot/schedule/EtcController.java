package com.jeuni.example.springboot.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/etc")
public class EtcController {

    @Autowired
    private EtcService etcService;

    /* 월초 생성/발송 일정 세팅 */
    @PostMapping("/setNewMonthSchedule/{calYearMonth}")
    @ResponseBody
    public int setNewMonthSchedule(@PathVariable String calYearMonth) {
        return etcService.setNewMonthSchedule(calYearMonth);
    }

    /* 생성/발송 일정 업무별 조회 */
    @GetMapping("/getEtcScheduleListByJob")
    @ResponseBody
    public List<Map<String, Object>> getEtcScheduleByJob() {
        return etcService.selectEtcScheduleListByJob();
    }

    /* 생성/발송 일정 일별 조회 */
    @GetMapping(value = "/getComScheduleListByDay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<EtcScheduleCalendarComVO> getComScheduleListByDay(@RequestParam(value = "jobSe") String jobSe,  @RequestParam(value = "taskCode") String taskCode,
                                                                  @RequestParam(value = "taskStartDay") String taskStartDay,
                                                                  @RequestParam(value = "taskEndDay") String taskEndDay) {
        EtcScheduleByDayDto etcScheduleByDayDto = EtcScheduleByDayDto.builder().jobSe(jobSe).taskCode(taskCode).taskStartDay(taskStartDay).taskEndDay(taskEndDay).build();

        return etcService.selectComScheduleListByDay(etcScheduleByDayDto);
    }

    /* 생성/발송 작업이력 일별 조회 */
    @GetMapping(value = "/getBatchJobListByDay", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<EtcScheduleCalendarJobVO> getBatchJobListByDay(@RequestParam(value = "jobSe") String jobSe, @RequestParam(value = "taskCode") String taskCode,
                                                               @RequestParam(value = "taskStartDay") String taskStartDay) {
        EtcScheduleByDayDto etcScheduleByDayDto = EtcScheduleByDayDto.builder().jobSe(jobSe).taskCode(taskCode).taskStartDay(taskStartDay).build();

        return etcService.selectBatchJobListByDay(etcScheduleByDayDto);
    }

    /* 생성/발송 일정 신규 등록 */
    @PostMapping(value = "/insertEtcSchedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void insertEtcSchedule(Model model, @RequestBody EtcScheduleMainDto etcScheduleMainDto) {
        etcService.insertEtcSchedule(etcScheduleMainDto);
    }

    /* 생성/발송 일정 수정 */
    @PutMapping(value = "/modifyEtcSchedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void modifyEtcSchedule(Model model, @RequestBody EtcScheduleMainDto etcScheduleMainDto) {
        etcService.modifyEtcSchedule(etcScheduleMainDto);
    }

    /* 생성/발송 일정 날짜 수정 */
    @PutMapping(value = "/modifyEtcScheduleDay", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int modifyEtcScheduleDay(Model model, @RequestBody EtcScheduleMainDto etcScheduleMainDto) {
        return etcService.modifyEtcScheduleDay(etcScheduleMainDto);
    }

    /* 생성/발송 일정 삭제 */
    @DeleteMapping(value = "/removeEtcSchedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void removeEtcSchedule(Model model, @RequestBody EtcScheduleMainDto etcScheduleMainDto) {
        etcService.removeEtcSchedule(etcScheduleMainDto);
    }
}