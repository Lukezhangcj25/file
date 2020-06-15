/**
 *提交回复
 */

function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    commenttarget(questionId, 1, commentContent);
}

function commenttarget(targetId, type, commentContent) {
    if (!commentContent) {
        alert("回复不能为空!");
        return;
    }

    $.ajax(
        {
            type: "POST",
            url: "/comment",
            contentType: "application/json",
            data: JSON.stringify({
                "parentId": targetId,
                "content": commentContent,
                "type": type
            }),
            success: function (response) {
                if (response.code == 200) {
                    $("#comment_section").hide();
                    window.location.reload();
                } else {
                    if (response.code == 2000) {
                        var isAccepted = confirm(response.message);
                        if (isAccepted) {
                            window.open("https://github.com/login/oauth/authorize?client_id=905932fc9303b64ca14a&redirect_uri=http://localhost:9002/callback&scope=user&state=1");
                            window.localStorage.setItem("closable", true);
                        }
                    } else {
                        alert(response.message);
                    }
                }
            },
            dataType: "json"
        });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var commentContent = $("#input-" + commentId).val();
    commenttarget(commentId, 2, commentContent)
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    var comment = $("#commenthr-" + id);
    var subCommentContainer = $("#comment-" + id);

    if (comments.is('.collapse.in')) {
        // 收起评论
        comments.find(".comment-two-top").remove();
        comments.removeClass("collapse in");
        comments.addClass("collapse on");
        comment.addClass("comment");
        e.classList.remove("active");
    } else {
        // 展开评论

        $.getJSON("/comment/" + id, function (data) {

            $.each(data.data.reverse(), function (index, comment) {

                var mediaLeftElement = $("<div/>", {
                    "class": "media-left"
                }).append($("<a/>", {
                    }).append($("<img/>", {
                    "class": "media-object img-rounded",
                    "src": comment.user.avatarUrl
                })));


                var mediaBodyElement = $("<div/>", {
                    "class": "media-body"
                }).append($("<h6/>", {
                    "class": "media-heading"
                    }).append($("<span/>", {
                    "html": comment.user.name
                    })))
                    .append($("<div/>", {
                        "html": comment.content
                    }))
                    .append($("<div/>", {
                        "class": "menu",
                    }).append($("<span/>", {
                        "class": "glyphicon glyphicon-thumbs-up font"
                        }))
                        .append($("<span/>", {
                            "class": "icon",
                            "html":0
                        }))
                        .append($("<span/>", {
                            "class": "glyphicon glyphicon-thumbs-down font"
                        }))
                        .append($("<span/>", {
                            "class": "icon",
                            "html":0
                        }))
                        .append($("<span/>", {
                            "class": "pull-right",
                            "html":moment(comment.gmtCreate).format("YYYY-MM-DD")
                        }))
                    )
                    .append($("<div/>", {
                        "class": "comment",
                    }));


                var commentElement = $("<div/>", {
                    "class": "col-lg-12 col-md-12 col-sm-12 col-sx-12 comment-two-top",
                }).append(mediaLeftElement)
                    .append(mediaBodyElement);

                subCommentContainer.prepend(commentElement);

            });

            comments.removeClass("collapse on");
            comments.addClass("collapse in");
            comment.removeClass("comment");
            e.classList.add("active");
        });
    }
}