(function($) {

	"use strict";

$(document).ready(function(){
    var date = new Date();
    var today = date.getDate();
    // Set click handlers for DOM elements
    $(".right-button").click({date: date}, next_year);
    $(".left-button").click({date: date}, prev_year);
    $(".month").click({date: date}, month_click);
    $("#add-button").click({date: date}, new_event);
    $(".update-modal").click({date: date}, update_modal);
    $(".months-row").children().eq(date.getMonth()).addClass("active-month");
    init_calendar(date);
    var events = check_events(today, date.getMonth()+1, date.getFullYear());
    show_events(events, months[date.getMonth()], today);
    $("#data-ok-button").click(data_ok_event);
    $("#data-insert-button").click(data_insert_event);
});

function data_ok_event() {
    var data = $(".data-textarea").val();
    var rows = data.split("\n");
    var table = $("#tbody-sample");

    for(var y in rows) {
        if(parseInt(y) === (rows.length - 1)) break;
        var cells = rows[y].split("\t");
        var row = $("<tr />");
        for(var x in cells) {
            row.append("<td>" + cells[x] + "</td>");
        }
        table.append(row);
    }
}

function data_insert_event() {
    var samples = [];
    var data = $(".data-textarea").val();
    var rows = data.split("\n");

    for(var y in rows) {
        if(parseInt(y) === (rows.length - 1)) break;
        var cells = rows[y].split("\t");
        var sample = [];
        for(var x in cells) {
            sample.push(cells[x]);
        }

        var sampleData = {
            type: sample[0],
            acnt_num: sample[1],
            rcv_num: sample[2],
            send_yn: sample[3]
        }

        samples.push(sampleData);
    }

    $.ajax({
        type: 'POST',
        url: '/api/v1/samples',
        dateType: 'json',
        async: false,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(samples)
    }).done(function() {
        alert('SAMPLE이 등록되었습니다.');
        window.location.href = '/posts/data';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function init_calendar(date) {
    $(".tbody").empty();
    $(".events-container").empty();
    var calendar_days = $(".tbody");
    var month = date.getMonth(); // January - December : 0 - 11
    var year = date.getFullYear();
    var day_count = days_in_month(month, year);
    var row = $("<tr class='table-row'></tr>");
    var today = date.getDate();
    date.setDate(1);
    var first_day = date.getDay(); // Sunday - Saturday : 0 - 6
    for(var i=0; i<42+first_day; i++) {
        var day = i-first_day+1;

        if(i%7===0) {
            calendar_days.append(row);
            //row = $("<tr class='table-row'></tr>");
        }

        if(i < first_day || day > day_count) {
            var curr_date = $("<td class='table-date nil'>"+"</td>");
            row.append(curr_date);
        }
        else {
            var events = check_events(day, month+1, year);
            var curr_date = $("<td class='table-date'>"+day+"</td>");
            var curr_event = $("<span class='table-job-name'></span>");

            if(today===day && $(".active-date").length===0) {
                curr_date.addClass("active-date");
                show_events(events, months[month], day);
            }

            if(events.length !== 0) {
                var jobName;
                curr_date.addClass("event-date");
                if(events.length === 1) {
                    jobName = events[0]["name"];
                } else {
                    jobName = events[0]["name"] + " 외 " + (events.length-1) + "개";
                }
                curr_event = $("<span class='table-job-name'>"+jobName+"</span>");
            }

            curr_date.click({events: events, month: months[month], day:day}, date_click);
            row.append(curr_date);
            curr_date.append(curr_event);
        }
    }

    calendar_days.append(row);
    $(".year").text(year);
}


function days_in_month(month, year) {
    var monthStart = new Date(year, month, 1);
    var monthEnd = new Date(year, month + 1, 1);
    return (monthEnd - monthStart) / (1000 * 60 * 60 * 24);
}


function date_click(event) {
    $(".events-container").show(250);
    $("#dialog").hide(250);
    $(".active-date").removeClass("active-date");
    $(this).addClass("active-date");
    show_events(event.data.events, event.data.month, event.data.day);
};


function month_click(event) {
    $(".events-container").show(250);
    $("#dialog").hide(250);
    var date = event.data.date;
    $(".active-month").removeClass("active-month");
    $(this).addClass("active-month");
    var new_month = $(".month").index(this);
    date.setMonth(new_month);
    init_calendar(date);
}


function next_year(event) {
    $("#dialog").hide(250);
    var date = event.data.date;
    var new_year = date.getFullYear()+1;
    $("year").html(new_year);
    date.setFullYear(new_year);
    init_calendar(date);
}


function prev_year(event) {
    $("#dialog").hide(250);
    var date = event.data.date;
    var new_year = date.getFullYear()-1;
    $("year").html(new_year);
    date.setFullYear(new_year);
    init_calendar(date);
}


function new_event(event) {

    var date = event.data.date;
    var month = (date.getMonth()+1).toString();
    var day = (parseInt($(".active-date").html())).toString();
    month = month.length === 1 ? "0" + month : month;
    day = day.length === 1 ? "0" + day : day;

    var dateValue = date.getFullYear() + month + day;

    if($(".active-date").length===0)
        return;

    $("input").click(function(){
        $(this).removeClass("error-input");
    })

    $("#dialog input[type=text]").val('');
    $("#date").val(dateValue);
    $(".events-container").hide(250);
    $("#dialog").show(250);

    $("#cancel-button").click(function() {
        $("#name").removeClass("error-input");
        $("#state").removeClass("error-input");
        $("#date").removeClass("error-input");
        $("#time").removeClass("error-input");
        $("#dialog").hide(250);
        $(".events-container").show(250);
    });

    $("#ok-button").unbind().click({date: event.data.date}, function() {

        var date = event.data.date;
        var day = parseInt($(".active-date").html());

        var data = {
            name: $("#name").val(),
            state: $("#state").val(),
            date: $("#date").val(),
            time: $("#time").val()
        }

        if(data.name.length === 0) {
            $("#name").addClass("error-input");
            alert('JOB 이름을 입력하세요');
            return;
        }
        else if(data.state.length === 0) {
            $("#state").addClass("error-input");
            alert('JOB 상태를 입력하세요');
            return;
        }
        else if(data.state === "생성" && data.time.length === 0) {
            $("#time").addClass("error-input");
            alert('JOB 수행시간을 입력하세요');
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/api/v1/jobs',
            dateType: 'json',
            async: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('JOB이 등록되었습니다.');
            window.location.href = '/posts/calendar';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });

    });
}


function show_events(events, month, day) {
    $(".events-container").empty();

    if(events.length>0) {
        $(".events-container").show(250);

        for(var i=0; i<events.length; i++) {
            var event_card = $("<div class='event-card'></div>");
            var event_name = $("<div class='event-name'>"+events[i]["name"]+": </div>");
            var event_count = $("<div class='event-count'>"+events[i]["state"]+"</div>");
            if(events[i]["cancelled"]===true) {
                $(event_card).css({
                    "border-left": "10px solid #FF1744"
                });
                event_count = $("<div class='event-cancelled'>Cancelled</div>");
            }
            $(event_card).append(event_name).append(event_count);
            $(".events-container").append(event_card);
        }
    }
}


function check_events(day, month, year) {

    var month = month.toString().length === 1 ? "0" + month : month;
    var day = day.toString().length === 1 ? "0" + day : day;
    var events = [];

    var date = "" + year + month + day;

    $.ajax({
        type: 'GET',
        url: '/api/v1/jobs/' + date,
        dateType: 'json',
        async: false,
        contentType: 'application/json; charset=utf-8'
    }).done(function(data) {
        for(var i=0; i<data.length; i++) {
            var event = data[i];
            if(date===data[i].date) {
                events.push(event);
            }
        }
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });

    return events;
}


function update_modal(event) {

    var id = event.target.id;

    $.ajax({
        type: 'GET',
        url: '/api/v1/jobs/update/' + id,
        dateType: 'json',
        async: false,
        contentType: 'application/json; charset=utf-8'
    }).done(function(data) {
        $("#update-name").val(data.name);
        $("#update-state").val(data.state);
        $("#update-date").val(data.date);
        $("#update-time").val(data.time);
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });

    $("#update-cancel-button").click(function() {
        $("#update-name").removeClass("error-input");
        $("#update-state").removeClass("error-input");
        $("#update-date").removeClass("error-input");
        $("#update-time").removeClass("error-input");
        $("#updateModal").modal("hide");
    });

    $("#update-button").unbind().click({date: event.data.date}, function() {

        var data = {
            id: id,
            name: $("#update-name").val(),
            state: $("#update-state").val(),
            date: $("#update-date").val(),
            time: $("#update-time").val()
        }

        if(data.name.length === 0) {
            $("#update-name").addClass("error-input");
            alert('JOB 이름을 입력하세요');
            return;
        }
        else if(data.state.length === 0) {
            $("#update-state").addClass("error-input");
            alert('JOB 상태를 입력하세요');
            return;
        }
        else if(data.state === "생성" && data.time.length === 0) {
            $("#update-time").addClass("error-input");
            alert('JOB 수행시간을 입력하세요');
            return;
        }

        $.ajax({
            type: 'PUT',
            url: '/api/v1/jobs/update/' + id,
            dateType: 'json',
            async: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('JOB이 수정되었습니다.');
            window.location.href = '/posts/calendar';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });

    });

}


const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
];

})(jQuery);
