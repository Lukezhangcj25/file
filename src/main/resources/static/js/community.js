/**
*提交回复
*/
function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();

    if(!commentContent){
        alert("回复不能为空!");
        return;
    }

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
                    window.location.reload();
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


/**
 * 展开二级评论
 */
function collapseComments(e) {
        var id = e.getAttribute("data-id");
        var comments = $("#comment-" + id);
        debugger;
        if(comments.is('.on')){
            comments.removeClass("on");
            comments.addClass("in");
            $(".comment").css("margin-bottom","5px");
        }else if(comments.is('.in')){
            comments.removeClass("in");
            comments.addClass("on");
            $(".comment").css("margin-bottom","15px");
        }else{
            comments.addClass("in");
            $(".comment").css("margin-bottom","5px");
        }
}