package com.jeuni.example.springboot.web;

import com.jeuni.example.springboot.service.JobsService;
import com.jeuni.example.springboot.service.PostsService;
import com.jeuni.example.springboot.service.SamplesService;
import com.jeuni.example.springboot.service.TctService;
import com.jeuni.example.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final JobsService jobsService;
    private final SamplesService samplesService;
    private final TctService tctService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/calendar")
    public String postsCalendar(Model model) {
        model.addAttribute("jobs", jobsService.findAllJobs());
        return "posts-calendar";
    }

    @GetMapping("/posts/schedule")
    public String postsSchedule(Model model) {
        model.addAttribute("jobs", jobsService.findAllJobs());
        return "posts-schedule";
    }

    @GetMapping("/posts/data")
    public String postsData(Model model) {
        model.addAttribute("samples", samplesService.findAllSamples());
        return "posts-data";
    }

//    @GetMapping("/posts/table")
//    public String postsTable(Model model) {
//        model.addAttribute("tctSamComs", tctService.findAllTctSamComs());
//        model.addAttribute("tctSamComCount", tctService.findCountOfTctSamComs());
//        model.addAttribute("tctBillSoapCount", tctService.findCountOfTctBillSoaps());
//        model.addAttribute("tctSamComTotCount", tctService.findTotCountOfTctSamComs());
//        model.addAttribute("tctBillSoapTotCount", tctService.findTotCountOfTctBillSoaps());
//        return "posts-table";
//    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
