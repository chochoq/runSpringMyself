$.ajax({
    type: "GET",
    url: "/test",
    data: {},
    success: function(response){
       console.log(response)
    }
  })

$.ajax({
    type: "POST",
    url: "/test",
    data: { title_give:'봄날은간다' },
    success: function(response){
       console.log(response)
    }
  })