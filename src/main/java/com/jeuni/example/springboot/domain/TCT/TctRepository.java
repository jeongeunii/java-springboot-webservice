package com.jeuni.example.springboot.domain.TCT;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TctRepository extends JpaRepository<TCT, Long> {

    @Query(value = "select case t.gubun_cd when 'A' then '직권해지' \n" +
                    "                      when 'N' then '일반해지' \n" +
                    "else '' end gubun_cd, \n" +
                    "t.inv_dt, t.svc_mgmt_num, t.rcv_svc_num, t.svc_num, t.pay_amt, t.cust_nm, \n" +
                    "case t.create_yn when 'N' then '미생성' \n" +
                    "                 when 'S' then '생성중' \n" +
                    "                 when 'Y' then '생성 완료' \n" +
                    "else '' end create_yn, \n" +
                    "case s.send_yn when 'N' then '미발송' \n" +
                    "               when 'S' then '발송중' \n" +
                    "               when 'B' then '오류 종료' \n" +
                    "               when 'Y' then '발송 완료' \n" +
                    "else '-' end send_yn \n" +
                    "from TCT_SAM_COM t left join TCT_BILL_SOAP s \n" +
                    "on t.acnt_num = s.acnt_num", nativeQuery = true)
    List<TCT> findAllTctSamComs();

    @Query(value = "select case t.create_yn when 'N' then '미생성' \n" +
                    "                     when 'S' then '생성중' \n" +
                    "                     when 'Y' then '생성 완료' \n" +
                    "end yn, count(*) count \n" +
                    "from TCT_SAM_COM t group by t.create_yn", nativeQuery = true)
    List<ITCT_COUNT> findCountOfTctSamComs();

    @Query(value = "select case s.send_yn when 'N' then '미발송' \n" +
                    "                     when 'S' then '발송중' \n" +
                    "                     when 'B' then '오류 종료' \n" +
                    "                     when 'Y' then '발송 완료' \n" +
                    "end yn, count(*) count \n" +
                    "from TCT_BILL_SOAP s group by s.send_yn", nativeQuery = true)
    List<ITCT_COUNT> findCountOfTctBillSoaps();

    @Query(value = "select count(*) from TCT_SAM_COM t", nativeQuery = true)
    Long findTotCountOfTctSamComs();

    @Query(value = "select count(*) from TCT_BILL_SOAP s", nativeQuery = true)
    Long findTotCountOfTctBillSoaps();

    @Query(value = "select count(*) from BATCH_JOB_DETAIL b where b.task_code='UF'", nativeQuery = true)
    Long findTotCountOfBatchJobDetail();

    @Query(value = "select count(*) from BATCH_JOB_DETAIL b where b.task_code='UF' and b.status='CO'", nativeQuery = true)
    Long findCOCountOfBatchJobDetail();

}
