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
                $('#commentMain').hide();
                getComment();
            }
        }
    })
};
function getComment() {

}