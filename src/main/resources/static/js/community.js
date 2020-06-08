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
        var comment = $("#bt-" + id);
        var i = 0;

        console.log(comment);

        debugger;
        if(comments.is('.collapse.on')) {
            // 收起评论
            comments.removeClass("collapse on");
            comments.addClass("collapse in");
            comment.css("margin-bottom", "5px");
            e.classList.add("active");
        }else if(comments.is('.collapse.in')){
            // 展开评论
            comments.removeClass("collapse in");
            comments.addClass("collapse on");
            comment.removeAttr("style");
            e.classList.remove("active");
        }else{
            // 如页面不存在in或on时，实现展开功能
            comments.addClass("collapse in");
            comment.css("margin-bottom","5px");
        }




}