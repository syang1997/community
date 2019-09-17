function recommend(id,tag) {
    $.ajax({
        type:"POST",
        url:"/question/recommend",
        data:{"id":id,"tag":tag},
        success:function (result) {
            if(result.code==0){

            }
        }
    })
}
function getQuestions(num) {
    $.ajax({
        url:"/question/list/"+num,
        type:"POST",
        success:function (result) {
            if(result.code==0){
                var question=result.data.list;
                var pageInfo=result.data;
                var num=1;
                var li='';
                $('#question-pages').empty();
                $("#question-list").empty();
                $.each(question,function(inde,question) {
                    li='<div class="media">\n' +
                        '                <div class="media-left">\n' +
                        '                        <img class="media-object img-rounded" src="'+question.creator.avatarUrl+'" alt="..." >\n' +
                        '                </div>\n' +
                        '                <div class="media-body">\n' +
                        '                    <a href="/question/'+question.id+'">\n' +
                        '                    <h3 class="tilte">'+question.title+'</h3>\n' +
                        '                    </a>\n' +
                        '                    <span class="question-count">'+question.commentCount+'个回复●'+question.viewCount+'次浏览●'+getDateDiff(question.gmtCreate)+'</span>\n' +
                        '                </div>\n' +
                        '                </div>\n' +
                        '            <hr>\n';
                    $("#question-list").append(li);
                });
                if(pageInfo.isFirstPage){
                    li= '                        <li class="disabled">\n' +
                        '                            <a href="#" aria-label="Previous">\n' +
                        '                                <span aria-hidden="true">&laquo;</span>\n' +
                        '                            </a>\n' +
                        '                        </li>';
                }else {
                    li= '                        <li>\n' +
                        '                            <a href="#" onclick="getMyQuestions(1)"aria-label="Previous">\n' +
                        '                                <span aria-hidden="true">&laquo;</span>\n' +
                        '                            </a>\n' +
                        '                        </li>';
                }
                $('#question-pages').append(li);
                for(num;num<=pageInfo.pages;num++){
                    if(pageInfo.pageNum==num){
                        li='<li class="active"><a href="#">'+num+'</a></li>';
                    }else {
                        li='<li><a href="#" onclick="getMyQuestions('+num+')">'+num+'</a></li>';
                    }
                    $('#question-pages').append(li);

                }
                if(pageInfo.isLastPage){
                    li='<li class="disabled">\n' +
                        '                            <a href="#" aria-label="Next">\n' +
                        '                                <span aria-hidden="true">&raquo;</span>\n' +
                        '                            </a>\n' +
                        '                        </li>\n';
                }else {
                    li='<li>\n' +
                        '                            <a href="#" onclick="getMyQuestions(99999999)" aria-label="Next">\n' +
                        '                                <span aria-hidden="true">&raquo;</span>\n' +
                        '                            </a>\n' +
                        '                        </li>\n' ;
                }
                $('#question-pages').append(li);
            }
        }
    })
}

function selectTag(e) {
    var value=e.getAttribute("data-tag");
    var previous=$('#tag').val();
    if(previous.indexOf(value)==-1){
        if(previous){
            $('#tag').val(previous+','+value);
        }else {
            $('#tag').val(value);
        }
    }
}
function showSelectTag(){
    $('#select-tag').show();
}