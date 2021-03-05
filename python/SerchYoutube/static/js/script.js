function todayWeather(){
    $.ajax({
        type: "GET",
        url: "/todayWeather",
        data: {},
        success: function(response){
            let tdWeather = response['todaysWeather']
            console.log(tdWeather)
        }
    })
}