<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
	                      
	                      <sec:authentication property="principal" var="pinfo"/>
	                      	<sec:authorize access="isAuthenticated()">
	                      		<c:if test="${pinfo.username eq board.writer }">
			                        <button data-oper='modify' class ="btn btn-info">Modify</button>
	                      		</c:if>
	                      	</sec:authorize>
	                      
	                        <button data-oper='list' class ="btn btn-default">List</button>
	                        
	                        <form id="operForm" action="/board/modify" method="get" >
	                        	<input type="hidden" id="bno" name="bno" value="${board.bno}">
	                        	<input type="hidden" name="pageNum" value="${cri.pageNum}">
                            	<input type="hidden" name="amount" value="${cri.amount}">
                            	<input type="hidden" name="type" value="${cri.type}">
                            	<input type="hidden" name="keyword" value="${cri.keyword}">
	                        </form>
                        </div>
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
                            <i class="fa fa-comments fa-fw"></i>댓글 목록
                            <sec:authorize access="isAuthenticated()">
                            <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right" >새로운 댓글</button>
                            </sec:authorize>
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
                        <!--  footer 추가 -->
                        <div class="panel-footer">
                        </div>
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
<!--  ------------------------- -->
<!-- 모달창 시작 -->
 <!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
        	<label>Reply</label>
        	<input class="form-control" name="reply" value="New Reply">
        </div>
        <div class="form-group">
        	<label>Replyer</label>
        	<input class="form-control" name="replyer" value="Replyer">
        </div>
        <div class="form-group">
        	<label>Reply Date</label>
        	<input class="form-control" name="replyDate" value="">
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" id="modalModBtn" class="btn btn-Warning" >Modify</button>
        <button type="button" id="modalRemoveBtn" class="btn btn-danger" >Remove</button>
        <button type="button" id="modalRegisterBtn" class="btn btn-primary" >Reigster</button>
        <button type="button" id="modalCloseBtn" class="btn btn-info" data-dismiss="modal" >Close</button>
      </div>
    </div>
  </div>
</div>             
<!-- 모달창 끝 -->   

<style>
	.chat > li:hover {
		cursor:pointer
	}
</style>

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>

$(document).ready(function(){

	var bnoValue = ${board.bno};
	var replyUL = $(".chat");
	
	showList(1);
	
	function showList(page){
		replyService.getList(
				{bno : bnoValue, page : page ||1},
				function(replyCnt, list){
					
					if(page == -1){
						pageNum = Math.ceil(replyCnt/10.0);
						showList(pageNum);
						return ;
					}
					
					var str="";
					if(list == null || list.length ==0){
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
					showReplyPage(replyCnt); //추가
				});
	} //end showList
	
	//댓글 페이지 처리 시작
	var pageNum = 1;
    var replyPageFooter = $(".panel-footer");
    
    function showReplyPage(replyCnt){
	    var endNum = Math.ceil(pageNum / 10.0) * 10;  
	    var startNum = endNum - 9; 
	    var prev = startNum != 1;
	    var next = false;
	    
	    if(endNum * 10 >= replyCnt){
	      endNum = Math.ceil(replyCnt/10.0);
	    }
	    
	    if(endNum * 10 < replyCnt){
	      next = true;
	    }
	    
	    var str = "<ul class='pagination pull-right'>";
	    if(prev){
	      str+= "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
	    }
	    
	    for(var i = startNum ; i <= endNum; i++){
	      var active = pageNum == i? "active":"";
	      str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
	    }
	    
	    if(next){
	      str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
	    }
	    
	    str += "</ul></div>";
	    console.log(str);
	    replyPageFooter.html(str);
    }
	//댓글 페이지 처리 끝

	var modal = $("#myModal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	var replyer = null;
	
	<sec:authorize access = "isAuthenticated()">
		replyer = '<sec:authentication property="principal.username"/>';
	</sec:authorize>

	var csrfHeaderName= "${_csrf.headerName}";
	var csrfTokenValue="${_csrf.token}";
	
	$("#addReplyBtn").on("click", function(e){
		modal.find("input").val("");
		modal.find("input[name='replyer']").val(replyer);
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id !='modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		$(".modal").modal("show");
	});
	
	$(document).ajaxSend(function(e, xhr, options){
		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	});
	
	//댓글 등록
	modalRegisterBtn.on("click", function(e){
		var reply = {
				reply : modalInputReply.val(),
				replyer : modalInputReplyer.val(),
				bno : bnoValue
		};
		
		replyService.add(reply, function(result){
			alert(result);
			modal.find("input").val("");
			modal.modal("hide");
			//modalInputReplyDate.show();
			showList(-1);
		});
		
	});  //end modalRegisterBtn
	
	//댓글 이벤트 처리
	$(".chat").on("click", "li", function(e){
		var rno = $(this).data("rno");
				
		replyService.get(rno, function(reply){
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).
							attr("readonly", "readonly");
			modal.data("rno", reply.rno);
			
			//modal.find("button[id = 'modalRegisterBtn']").hide();
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		});
		
	});
	
   //댓글 수정
	modalModBtn.on("click", function(e){
		
		var originalReplyer = modalInputReplyer.val();
		
		let reply = {
				rno:modal.data("rno"),
				reply : modalInputReply.val(),
				replyer : originalReplyer
				
		};
		
		if(!replyer){
			alert("로그인후 삭제가 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		if(replyer != originalReplyer){
			alert("자신이 작성한 댓글만 삭제가 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		replyService.update(reply, function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});
		
	}); //end modalModBtn
	
	
	//댓글 삭제
	modalRemoveBtn.on("click", function(e){
		var rno = modal.data("rno");
		
		if(!replyer){
			alert("로그인후 삭제가 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		var originalReplyer = modalInputReplyer.val();
		
		if(replyer != originalReplyer){
			alert("자신이 작성한 댓글만 삭제가 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		replyService.remove(rno, originalReplyer, function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});
	});  //end modalRemoveBtn
	
	//댓글 이벤트 처리
	replyPageFooter.on("click","li a", function(e){
		e.preventDefault();
		var targetPageNum = $(this).attr("href");
		pageNum = targetPageNum;
		showList(pageNum);
	});
	
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
    