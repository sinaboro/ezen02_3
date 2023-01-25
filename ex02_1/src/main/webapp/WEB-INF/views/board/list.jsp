<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">List Page</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                            <button id='regBtn' type="button" class="btn btn-xs btn-primary pull-right">
                            	Register New Board
                            </button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" >
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                         <c:forEach items="${list}" var="board">
                          <tr class="odd gradeX">
                              <td>${board.bno}</td>
                              
                              <td>
                              <a class="move" href='${board.bno}'> ${board.title}</a>
                              </td>
                              
                              <td>${board.writer}</td>
                              <td><fmt:formatDate pattern="yyyy/MM/dd" 
                              value="${board.regdate}"/> </td>
                              <td><fmt:formatDate pattern="yyyy/MM/dd" 
                              value="${board.updateDate}"/> </td>
                          </tr>
                         </c:forEach>
                         
                                </tbody>
                            </table>
                  <form id="actionForm" action="/board/list" method="get">
                  	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                  	<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                  </form>
                            <!-- /.table-responsive -->
      <!-- 페이징 처리 시작 -->
<div class="pull-right">
	<ul class="pagination">
	  
	  <c:if test="${pageMaker.prev}">
		  <li class="paginate_button previous" ><a href="${pageMaker.startPage-1 }">Privious</a></li>
	  </c:if>
	  
	  <c:forEach var="num" begin="${pageMaker.startPage }" 
	  					end="${pageMaker.endPage }">
		 
		  <li class="paginate_button ${pageMaker.cri.pageNum == num? "active":"" }" ><a href="${num}">${num}</a></li>
	  </c:forEach>
	  
	  <c:if test="${pageMaker.next}">
		  <li class="paginate_button next" ><a href="${pageMaker.endPage+1 }">Next</a></li>
	  </c:if>
	</ul>
</div>	
<!-- 페이징 처리 끝 -->             
               
               <!-- 모달창 시작 -->
               <!-- Modal -->
			  <div class="modal fade" id="myModal" role="dialog">
			    <div class="modal-dialog">
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Modal Title</h4>
			        </div>
			        <div class="modal-body">
			          처리가 완료되었습니다.
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			          <button type="button" class="btn btn-primary" data-dismiss="modal">Save Changes</button>
			        </div>
			      </div>
			    </div>
			  </div>             
               <!-- 모달창 끝 -->             
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>


<script>
	$(document).ready(function(){
		var result = '${result}'
		
		checkModal(result);
		
		history.replaceState({},null,null);
		
		function checkModal(result){
			
			if(result === '' || history.state){
				return ;
			}
			
			if(result === "modify"){
				$(".modal-body").html("수정이 완료되었습니다." );
			}
			else if(result === "delete"){
				$(".modal-body").html("삭제가 완료되었습니다." );
			}
			else if(parseInt(result)>0){
				$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다." );
			}
			
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click", function(){
			self.location = "/board/register";
		});
		
		var actionForm = $("#actionForm");
		
		$(".paginate_button a").on("click", function(e){
			e.preventDefault();
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		$(".move").on("click",function(e){
			e.preventDefault();
			
			actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href") + "' >");
			actionForm.attr("action", "/board/get");
			actionForm.submit();
		});
		
		
		
		
		
		
		
		
		
		
	})
</script>


<%@ include file="../includes/footer.jsp" %>
    