function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    $.ajax(
        {
            type: "POST",
            url: "/comment",
            contentType: "application/json",
            data: JSON.stringify({
                "parentId": questionId,
                "content": commentContent,
                "type": 1
            }),
            success: function (response) {
                if (response.code == 200) {
                    $("#comment_section").hide();
                } else {
                    if (response.code == 2000) {
                        var isAccepted = confirm(response.message);
                        if(isAccepted){
                            window.open("https://github.com/login/oauth/authorize?client_id=905932fc9303b64ca14a&redirect_uri=http://localhost:9002/callback&scope=user&state=1");
                            window.localStorage.setItem("closable",true);
                        }
                    } else {
                        alert(response.message);
                    }
                }
            },
            dataType: "json"
        });
}