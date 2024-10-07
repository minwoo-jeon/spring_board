<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>minwoo'shop</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>

        #commentList {
            text-align: center;
            width : 50%;
            margin : auto;
            font-size:25px;
            color : rgb(97,97,97);
            padding: 8px 0 8px 0;

        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: "Noto Sans KR", sans-serif;
        }

        .container {
            width : 50%;
            height: 50%;
            margin : auto;
        }

        .writing-header {
            position: relative;
            margin: 20px 0 0 0;
            padding-bottom: 10px;
            border-bottom: 1px solid #323232;
        }

        /*#replyForm > input{*/
        /*    text-align: center;*/
        /*    width: 50%;*/
        /*    height: 35px;*/
        /*    margin: 5px 40px 10px 0px;*/
        /*    border: 1px solid #e9e8e8;*/
        /*    padding: 8px;*/
        /*    background: #f8f8f8;*/
        /*    outline-color: #e6e6e6;*/
        /*}*/

         input {
            width: 90%;
            height: 35px;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            padding: 8px;
            background: #f8f8f8;
            outline-color: #e6e6e6;
        }

        textarea {
            width: 100%;
            background: #f8f8f8;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            resize: none;
            padding: 8px;
            outline-color: #e6e6e6;
        }

        .frm {
            width:100%;
        }
        .btn {
            background-color: rgb(236, 236, 236); /* Blue background */
            border: none; /* Remove borders */
            color: black; /* White text */
            padding: 6px 12px; /* Some padding */
            font-size: 16px; /* Set a font size */
            cursor: pointer; /* Mouse pointer on hover */
            border-radius: 5px;
        }

        .btn:hover {
            text-decoration: underline;
        }

        #comment-comment > input {
            width: 100%;
            height: 35px;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            padding: 8px;
            background: #f8f8f8;
            outline-color: #e6e6e6;

        }
    </style>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">minwoo'shop</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>
<script>
    let msg = "${msg}";
    if(msg=="WRT_ERR") alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");
    if(msg=="MOD_ERR") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
</script>
<div class="container">
    <h2 class="writing-header">게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
    <form id="form" class="frm" action="" method="post">
        <input type="hidden" name="bno" value="${boardDto.bno}">

        <input name="title" type="text" value="${boardDto.title}" placeholder="  제목을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}>            <br>
        <textarea name="content" rows="20" placeholder=" 내용을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}>${boardDto.content}</textarea>      <br>


        <c:if test="${mode eq 'new'}">
            <button type="button" id="writeBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 등록</button>
        </c:if>
        <c:if test="${mode ne 'new'}">
            <button type="button" id="writeNewBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 글쓰기</button>
        </c:if>
        <c:if test="${boardDto.writer eq loginId}">
            <button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit"></i> 수정</button>
            <button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash"></i> 삭제</button>
        </c:if>
        <button type="button" id="listBtn" class="btn btn-list"><i class="fa fa-bars"></i> 목록</button>
    </form>
</div>


<div id="commentList"></div>
<div id="replyForm" style="display:none">
    <input type="text" name="replyComment" placeholder="댓글을 남겨보세요">
    <button id="wrtRepBtn" type="button">등록</button>
</div>



<div class="container">

<input type="text" name="comment" placeholder="댓글을 남겨보세요."  ${mode=="new" ? "style='display:none'" :""}>

     <button id="sendBtn"  ${mode=="new" ? "style='display:none'" : "type='button'"}>등록</button>


</div>


<script>

    let bno = "${boardDto.bno}";

    let showList =function (bno){
        $.ajax({
            type:'GET',       // 요청 메서드
            url: '/web/comments?bno='+bno,  // 요청 URI
            success : function(result){
                $("#commentList").html(toHtml(result));    // 서버로부터 응답이 도착하면 호출될 함수
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax(
    }


    $(document).ready(function(){

        showList(bno);

        let formCheck = function() {
            let form = document.getElementById("form");
            if(form.title.value=="") {
                alert("제목을 입력해 주세요.");
                form.title.focus();
                return false;
            }

            if(form.content.value=="") {
                alert("내용을 입력해 주세요.");
                form.content.focus();
                return false;
            }
            return true;
        }

        $("#writeNewBtn").on("click", function(){
            location.href="<c:url value='/board/write'/>";
        });

        $("#writeBtn").on("click", function(){
            let form = $("#form");
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");

            if(formCheck())
                form.submit();
        });

        $("#modifyBtn").on("click", function(){
            let form = $("#form");
            let readonly = $("input[name=title]", "#form").attr('readonly');

            // 1. 읽기 상태이면, 수정 상태로 변경
            if(readonly!=undefined) {
                $(".writing-header").html("게시판 수정");
                $("input[name=title]", "#form").attr('readonly', false);
                $("textarea", "#form").attr('readonly', false);
                $("#modifyBtn").html("<i class='fa fa-pencil'></i> 등록");
                return;
            }

            // 2. 수정 상태이면, 수정된 내용을 서버로 전송
            form.attr("action", "<c:url value='/board/modify'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });

        $("#removeBtn").on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;

            let form = $("#form");
            form.attr("action", "<c:url value='/board/remove?page=${page}&pageSize=${pageSize}'/>");
            form.attr("method", "post");
            form.submit();
        });

        $("#listBtn").on("click", function(){
            location.href="<c:url value='/board/list?page=${sc.page}&pageSize=${sc.pageSize}'/>";
        });


        //수정
        $("#modBtn").click(function(){
            let comment = $("input[name=comment]").val();
            let cno = $(this).attr("data-cno");

            if(comment.trim()==''){
                alert("댓글을 입력해주세요");
                $("input[name=comment]").focus()
                return;
            }


            $.ajax({
                type:'PATCH',       // 요청 메서드
                url: '/web/comments/'+cno, // 요청 URI   // ch4/comments/70 patch
                headers : { "content-type": "application/json"}, // 요청 헤더
                data : JSON.stringify({cno:cno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success : function(result){
                    alert(result);
                    showList(bno);
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
        })


        //답글에 등록 버튼 눌렀을떄
        $("#wrtRepBtn").click(function(){
            let comment = $("input[name=replyComment]").val();
            let pcno = $("#replyForm").parent().attr("data-pcno");

            if(comment.trim()==''){
                alert("댓글을 입력해주세요");
                $("input[name=replyComment]").focus()
                return;
            }


            $.ajax({
                type:'POST',       // 요청 메서드
                url: '/web/comments?bno='+bno,  // 요청 URI
                headers : { "content-type": "application/json"}, // 요청 헤더
                data : JSON.stringify({pcno:pcno , bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success : function(result){
                    alert(result);
                    showList(bno);
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()

            $("#replyForm").css("display","none")
            $("input[name=replyComment]").val('')
            $("#replyForm").appendTo("body");

        })




        //쓰기
        $("#sendBtn").click(function(){
            let comment = $("input[name=comment]").val();

            if(comment.trim()==''){
                alert("댓글을 입력해주세요");
                $("input[name=comment]").focus()
                return;
            }


            $.ajax({
                type:'POST',       // 요청 메서드
                url: '/web/comments?bno='+bno,  // 요청 URI
                headers : { "content-type": "application/json"}, // 요청 헤더
                data : JSON.stringify({bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success : function(result){
                    alert(result);
                    showList(bno);
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
        });

        //답글 버튼 클릭할시
        $("#commentList").on("click",".replyBtn",function () {
            //1. replyForm을 옮기고
            $("#replyForm").appendTo($(this).parent());
            //2. 답글을 입력할 보여준다.
            $('#replyForm').css("display","block");
        });



        //수정버튼을 클릭할시
        $("#commentList").on("click",".modBtn",function () {

            let cno = $(this).parent().attr("data-cno");
            let comment = $("span.comment", $(this).parent()).text();

            //1. comment의 내용을 input에 뿌려주기
            $("input[name=comment]").val(comment)

            //2. cno  전달하기
            $("#modBtn").attr("data-cno", cno);
        });

        //삭제 버튼 클릭시
        // $(".delBtn").click(function(){
        $("#commentList").on("click",".delBtn",function (){

            let cno = $(this).parent().attr("data-cno");
            let bno = $(this).parent().attr("data-bno");

            $.ajax({
                type:'DELETE',       // 요청 메서드
                url: '/web/comments/'+cno+' ?bno='+bno,  // 요청 URI
                success : function(result){
                    alert(result)
                    showList(bno);
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax(
        });
    });



    let toHtml = function (comments){
        let tmp = "<ul>";

        comments.forEach(function (comment){
            tmp += '<li data-cno=' + comment.cno
            tmp += ' data-pcno=' + comment.pcno
            tmp += ' data-bno=' + comment.bno + '>'
            if(comment.cno!=comment.pcno)
                tmp += 'ㄴ'
            tmp += ' 작성자:<span class="commenter">'+ comment.commenter + '</span>'
            tmp += ' 내용:<span class="comment">' + comment.comment + '</span>'
            tmp += ' 등록일:'+ comment.up_date
            tmp += '<button class="delBtn" class="btn btn-remove"><i class="fa fa-trash"></i> 삭제</button>'
            tmp += '<button class="modBtn" class="btn btn-modify"><i class="fa fa-edit"></i>수정</button>'
            tmp += '<button class="replyBtn">답글</button>'
            tmp += '</li>'
        })

        return tmp + "</ul>";
    }
</script>
</body>
</html>