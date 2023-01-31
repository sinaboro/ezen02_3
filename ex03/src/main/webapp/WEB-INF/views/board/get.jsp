<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read Page</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        
	                        <div class="form-group">
	                        	<label>Bno</label> 
	                        	<input class="form-control" name="bno" value="${board.bno}" readonly>
	                        </div>

	                        <div class="form-group">
	                        	<label>Title</label> 
	                        	<input class="form-control" name="title" value="${board.title}" readonly>
	                        </div>
	                        <div class="form-group">
	                        	<label>Text Area</label>
	                        	<textarea rows="3" class="form-control" name="content" readonly>${board.content}</textarea> 
	                        </div>
	                        <div class="form-group">
	                        	<label>Writer</label> <input class="form-control" name="Writer" value="${board.writer}" readonly>
	                        </div>
	                        <button data-oper='modify' class ="btn btn-info">Modify</button>
	                        <button data-oper='list' class ="btn btn-default">List</button>
	                        
	                        <form id="operForm" action="/board/modify" method="get">
	                        	<input type="hidden" id="bno" name="bno" value="${board.bno}">
	                        	
	                        	<input type="hidden" name="pageNum" value="${cri.pageNum}">
                            	<input type="hidden" name="amount" value="${cri.amount}">
                            	<input type="hidden" name="type" value="${cri.type}">
                            	<input type="hidden" name="keyword" value="${cri.keyword}">
	                        </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
<!--  ------------------------- -->

			<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i>Reply
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<ul class="chat">
                        		<li class="left clearfix" data-rno='12'>
                        			<div>
                        				<div class="header">
                        					<strong class="primary-font">user00</strong>
                        					<small class="pull-right text-muted">2023-01-31</small>
                        				</div>
                        				<p>Good Job!!</p>
                        			</div>
                        		</li>
                        	</ul>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
<!--  ------------------------- -->
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>

$(document).ready(function(){

	var bnoValue = ${board.bno};
	var replyUL = $(".chat");
	
	showList(1);
	
	function showList(page){
		replyService.getList(
				{bno : bnoValue, page : page ||1},
				function(list){
					var str="";
					if(list == null || list.length ==0){
						replyUL.html("");
						return ;
					}
					
					for(var i=0, len=list.length || 0; i<len ; i++){
						str += "<li class='left clearfix' data-rno='"+list[i].rno+" '>";
						str += "<div>";
						str += "<div class='header'>";
						str += "<strong class='primary-font'>" + list[i].replyer + "</strong>";
						str += "<small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate)  + "</small>";
						str += "</div>";
						str += "<p>"+ list[i].reply +"</p>";
						str += "</div></li>";
					}
					replyUL.html(str);
				});
	} //end showList
	
});
	
  /*	replyService.add(
				{reply:"댓글 추가", replyer:"댓글 추가", bno:7013656},
				function(result){
					alert("reslut : " + result);
				}
	); */ 
	
   /*	replyService.getList({bno:bnoValue, page:1}, function(list){
		for(var i=0, len=list.length||0; i<len; i++){
			console.log(list[i]);
		}
	});  */
	
   /*	replyService.remove(17,function(result){
		console.log(result);
		if(result === "success"){
			alert("REMOVED");
		}
	}, function(err){
		alert("ERROR");
	
	}); */
	
   /*	replyService.update(
		{rno : 1, bno: bnoValue, reply : "Modified Reply222"},
		 function(result){
			alert("수정 완료");
		},function(error){
			alert("수정 실패");
		}		  
	); */
	
/*	replyService.get(3,function(data){
		console.log(data);
	});
*/
	
	
</script>

<script>
	$(document).ready(function(){
		var operForm = $("#operForm");
		$("button[data-oper='modify']").on("click",function(e){
			operForm.attr("action", "/board/modify").submit();
		});
		$("button[data-oper='list']").on("click",function(e){
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list");
			operForm.submit();
		});
		
		
	});
</script>          
<%@ include file="../includes/footer.jsp" %>
    