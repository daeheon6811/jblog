<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<script type="text/javascript">


	$(document).ready(function() {

		$('#"category" #select_op').click(function() {
			var selectbox = $('#"category"');
	      	var option_value = selectbox[0].value;
		});
	});
	$.ajax({
		url: "/jblog/admin/write/selectbox?option_value=" + option_value,
		type: "get",
		dataType: "json",
		error: function(xhr, status, e){
			console.error(status, e);
		},
		success: function(response){
			console.log(response);
			
			if(response.result != "success"){
				console.error(response.message);
				return;
			}
			
			
		}
	});
	}
	</script>


<div id="container">
	<jsp:include page="/WEB-INF/views/blog/admin/includes/header.jsp" />
	<div id="wrapper">
		<div id="content" class="full-screen">
			<jsp:include page="/WEB-INF/views/blog/admin/includes/menu.jsp" />
			<form action="${pageContext.request.contextPath}/${authUser.id}/admin/write" method="post">
				<table class="admin-cat-write">
					<tr>
						<td class="t">제목</td>
						<td><input type="text" size="60" name="title"> 
					       	<select name="category">
								<c:forEach var="vo" items="${categoryList}" begin="0">
									<option value="${vo.no}">${vo.name}</option>
									
								</c:forEach>

						</select></td>
					</tr>
					<tr>
						<td class="t">내용</td>
						<td><textarea name="contents"></textarea></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="s"><input type="submit" value="포스트하기"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/blog/admin/includes/footer.jsp" />
</div>
</body>
<script type="text/javascript">
	var target = document.getElementById("category");
	target.options[target.selectedIndex].value);
</script>



</html>