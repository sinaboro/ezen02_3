<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
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
                           		<label>Bno</label> <input name="bno" class="form-control" 
                           		value="${board.bno}" readonly="readonly">
                           	</div>
   
                           	<div class="form-group">
                           		<label>Title</label> <input name="title" class="form-control"
                           		value="${board.title}" readonly="readonly">
                           	</div>
                           
                           	<div class="form-group">
                           	<label>Text Area</label> <textarea rows="3" cols="" name="content" 
                           	class="form-control" readonly="readonly">
                           		${board.content}
                           	</textarea>
                           	</div>
                           
                           	<div class="form-group">
                           		<label>Writer</label> <input name="writer" class="form-control"
                           		value="${board.writer}" readonly="readonly">
                           		>
                           	</div>
                           	
                           	<button data-oper='modify' class="btn btn-default"
                           	onclick="location.href='/board/modify?bno=${board.bno}'" >Modify</button>
                           	<button data-oper='list' class="btn btn-info"
                           	onclick="location.href='/board/list'">List</button>
                           
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
      
<%@ include file="../includes/footer.jsp" %>