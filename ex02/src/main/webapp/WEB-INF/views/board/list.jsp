<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           Board List Page
                           <button id="regBtn" type="button" class="btn btn-info btn-xs pull-right" >Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
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
                                	<c:forEach items="${list}" var="board" >
	                                    <tr class="odd gradeX">
	                                        <td>${board.bno}</td>
	                                        
	                                        <td><a href='/board/get?bno=${board.bno}'>${board.title}</a></td>
	                                        
	                                        <td>${board.writer}</td>
	                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
	                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/> </td>
	                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

<!-- 모달 시작 -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body"></div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save Changes</button>
        </div>
      </div>
    </div>
  </div>
<!-- 모달 끝 -->

       
<script>
	$(document).ready(function(){
		var result = '${result}';
		
		checkModal(result);
		
		history.replaceState({},null,null);  //브라우저 주소창을 clear기능.
		
		function checkModal(result){
			if(result == '' || history.state){
				return ;
			}
			if(parseInt(result) > 0){
				$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록 되었습니다.");
			}
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click", function(){
			self.location = "/board/register";
		});
	});
</script>


<%@ include file="../includes/footer.jsp" %>