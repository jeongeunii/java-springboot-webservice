var main = {
    init : function () {
        var _this = this;
        var totCountOfJob = 0;
        var countOfCOJob = 0;

        $.ajax({
            type: 'GET',
            url: '/api/v1/table/tctSamComs',
            dataType: 'json',
            async: false,
            contentType: 'application/json; charset=utf-8'
        }).done(function(data) {
            var yns = [];
            var counts = [];

            for(var i=0; i<data.length; i++) {
                yns.push(data[i].yn);
                counts.push(data[i].count);
            }

            var ctx = $('#myTctSamComChart');
            _this.makeChart(ctx, 'bar', yns, counts, 'create');
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });

        $.ajax({
            type: 'GET',
            url: '/api/v1/table/tctBillSoaps',
            dataType: 'json',
            async: false,
            contentType: 'application/json; charset=utf-8'
        }).done(function(data) {
            var yns = [];
            var counts = [];

            for(var i=0; i<data.length; i++) {
                yns.push(data[i].yn);
                counts.push(data[i].count);
            }

            var ctx = $('#myTctBillSoapChart');
            _this.makeChart(ctx, 'bar', yns, counts, 'send');
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });

        $.ajax({
            type: 'GET',
            url: '/api/v1/table/batchJobDetail/tot',
            dataType: 'json',
            async: false,
            contentType: 'application/json; charset=utf-8'
        }).done(function(data) {
            totCountOfJob = data;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });

        $.ajax({
            type: 'GET',
            url: '/api/v1/table/batchJobDetail',
            dataType: 'json',
            async: false,
            contentType: 'application/json; charset=utf-8'
        }).done(function(data) {
            countOfCOJob = data;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });

        _this.makeProgressBar(totCountOfJob, countOfCOJob);
    },
    makeChart : function (ctx, type, labels, data, yn) {
        var ynText = yn==='create' ? '청구서 생성여부' : '청구서 발송여부';
        var backColors = yn==='create' ? ['#B2CCFF', '#B5B2FF', '#D1B2FF', '#FFB2F5'] : ['#B2CCFF', '#B2EBF4', '#B7F0B1', '#CEF279'];
        var myChart = new Chart(ctx, {
            type: type,
            data: {
                labels: labels,
                datasets: [{
                    label: '건수',
                    data: data,
                    backgroundColor: backColors
                }]
            },
            options: {
                title: {
                    display: true,
                    text: ynText
                },
                legend: {
                    display: false
                },
                responsive: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            stepSize: 5
                        }
                    }],
                    xAxes: [{
                        ticks: {
                            fontSize: 10
                        }
                    }]
                }
            }
        });
    },
    makeProgressBar : function (totCountOfJob, countOfCOJob) {
        var bar = document.getElementById('progressBar');
        var ratio = "SAMFILE 업로드 【" + countOfCOJob + "/" + totCountOfJob + "】";
        $('#uploadRatio').text(ratio);
        bar.style.width = (countOfCOJob / totCountOfJob * 100) + "%";
    }
};

main.init();
