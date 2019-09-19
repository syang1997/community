document.write("<script  src='until.js'></script>");
function postComment() {
    var parentId=$('#questionId').val();
    var questionId=parentId;
    var comenttype=$('#commentType').val();
    var content=$('#commentContent').val();
    $.ajax({
        type:"POST",
        url:"/comment/send",
        data:{"parentId":parentId,"type":comenttype,"content":content,"questionId":questionId},
        success:function (result) {
            if(result.code==1){
                if(result.prompt="请登陆!"){
                    var isAccpeted=confirm(result.prompt);
                    if(isAccpeted){
                        window.open("https://github.com/login/oauth/authorize?client_id=ffa5ba2e813be2787c75&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(result.prompt);
                }
            }
            if(result.code==0){
                window.location.href='/question/'+questionId;
            }
        }
    })
};
function getComment(id) {
    $.ajax({
        type:"POST",
        url:"/comment/get",
        data:{"id":id},
        success:function (result) {
            if (result.code == 0) {
                var comments = result.data;
                $.each(comments, function (index, comments) {
                    var li = '<div class="media">\n' +
                        '                        <div class="media-left">\n' +
                        '                        <a >\n' +
                        '                            <img class="media-object img-rounded" src="'+comments.creator.avatarUrl+'" ">\n' +
                        '                        </a>\n' +
                        '                        </div>\n' +
                        '                    <div class="media-body">\n' +
                        '                        <h5 class="media-heading">\n' +
                        '                            <span>'+comments.creator.name+'</span>\n' +
                        '                        </h5>\n' +
                        '                        <div>' + comments.content + '</div>\n' +
                        '                        <div class="menu" id="collpase-'+comments.id+'">\n' +
                        '                            <span class="glyphicon glyphicon-thumbs-up icon" aria-hidden="true"></span>\n' +
                        '                            <span class="glyphicon glyphicon-comment icon" data-id="'+comments.id+'" data-collapse="in" onclick="collapseComents(this)" aria-hidden="true"></span><span>'+comments.replyCount+'</span>\n' +
                        '                            <span class="pull-right" >'+formatDate(comments.gmtCreate)+'</span>\n' +
                        '                        </div>\n' +
                        ' <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  collapse sub-comments" id="comment-'+comments.id+'">\n' +
                        '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="commentlist-'+comments.id+'" >' +
                        '</div>' +
                        '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">' +
                        '<input type="text" class="form-control" id="second-'+comments.id+'"placeholder="评论一下">' +
                        '<button type="button" class="btn btn-success pull-right" onclick="postCollapseComents('+comments.id+')">评论</button>' +
                        '                    </div>\n' +
                        '                    </div>\n' +
                        '                    </div>\n' +
                        '<hr  class="col-lg-12 col-md-12 col-sm-12 col-xs-12">\n' +
                        '</div>';
                    $('#commentsList').append(li);
                });
            }
        }
    })
}

/**
 * 展开二级评论
 */
function collapseComents(e) {
    var id=e.getAttribute("data-id");
    var comments=$('#comment-'+id);
    var collapse=e.getAttribute("data-collapse");
    if(collapse){
        fushsecond(id);
        comments.addClass("in");
        e.removeAttribute("data-collapse");
        e.classList.add("active");
    }else {
        comments.removeClass("in");
        e.setAttribute("data-collapse","in");
        e.classList.remove("active");
    }
}

function postCollapseComents(id) {
    var content= document.getElementById('second-'+id).value;
    var type=2;
    var parentId=id;
    var questionId=$('#questionId').val();
    $.ajax({
        type:"POST",
        url:"/comment/send",
        data:{"parentId":parentId,"type":type,"content":content,"questionId":questionId},
        success:function (result) {
            if(result.code==1){
                if(result.prompt=="请登陆!"){
                    var isAccpeted=confirm(result.prompt);
                    if(isAccpeted){
                        window.open("https://github.com/login/oauth/authorize?client_id=ffa5ba2e813be2787c75&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(result.prompt);
                }
            }
            if(result.code==0){
                fushsecond(id);
                $('input[id="second-20"]').val('');
            }
        }
    })
}

function fushsecond(id) {
    $.ajax({
        type:"POST",
        url:"/comment/get/second",
        data:{"id":id},
        success:function (result) {
            if(result.code==0){
                var comments = result.data;
                $('#commentlist-'+id).empty();
                $.each(comments, function (index, comments) {
                    var li='                    <div class="media">\n' +
                        '                        <div class="media-left">\n' +
                        '                        <a >\n' +
                        '                            <img class="media-object img-rounded" src="'+comments.creator.avatarUrl+'">\n' +
                        '                        </a>\n' +
                        '                        </div>\n' +
                        '                    <div class="media-body">\n' +
                        '                        <h5 class="media-heading">\n' +
                        '                            <span>'+comments.creator.name+'</span>\n' +
                        '                        </h5>\n' +
                        '                        <div>' + comments.content + '</div>\n' +
                        '                        <div class="menu">\n' +
                        '                            <span class="pull-right">'+formatDate(comments.gmtCreate)+'</span>\n' +
                        '                        </div>\n' +
                        '                    </div>\n' +
                        '                    </div>';
                    $('#commentlist-'+id).append(li);
                });
            }

        }
    });
}
