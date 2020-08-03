<%@page import="in.co.hospital.mgt.sys.model.PatientModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.hospital.mgt.sys.bean.PatientBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.hospital.mgt.sys.model.UserModel"%>
<%@page import="in.co.hospital.mgt.sys.controller.PatientListCtl"%>
<%@page import="in.co.hospital.mgt.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient</title>
<script src="/HospitalManagementSys/js/jquery.min.js"></script>
<script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
</head>
<body>
<%@ include file="Header.jsp"%>

	<section class="intro">
	<div class="intro-content">
		<div class="container">
			<center>
				<h2 align="center">Patient List</h2>
			</center>
			<br>
			<form action="<%=HMSView.PATIENT_LIST_CTL%>" method="post">
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">First Name</th>
							<th scope="col">Email Id</th>
						</tr>
					</thead>
					<tbody>
						<tr>

							<td><input type="text" name="fName"
								placeholder="Enter First Name"
								value="<%=ServletUtility.getParameter("fName", request)%>"
								class="form-control input-md"></td>
							<td><input type="text" name="emailId"
								placeholder="Enter emailId"
								value="<%=ServletUtility.getParameter("emailId", request)%>"
								class="form-control input-md"></td>
							<td><input type="submit" class="btn btn-primary"
								name="operation" value="<%=PatientListCtl.OP_SEARCH%>"></td>
						</tr>
					</tbody>
				</table>
				<center>
					<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
					<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
				</center>

				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col"><input type="checkbox" id="selectall">Select
								All</th>
							<th scope="col">S.No.</th>
							<th scope="col">Name</th>
							<th scope="col">EmailId</th>
							<th scope="col">Mobile No</th>
							<th scope="col">Doctor Name</th>
							<th scope="col">City</th>
							<th scope="col">Address</th>
							<th scope="col">Deceased</th>
							<th scope="col">Edit</th>
						</tr>
					</thead>
					<tbody>

						<%
							int pageNo = ServletUtility.getPageNo(request);
							int pageSize = ServletUtility.getPageSize(request);
							int index = ((pageNo - 1) * pageSize) + 1;
							PatientBean bean = null;
							List list = ServletUtility.getList(request);
							Iterator<PatientBean> it = list.iterator();

							while (it.hasNext()) {
								bean = it.next();
						%>
						<tr>

							<th scope="row"><input type="checkbox" class="case"
								name="ids" value="<%=bean.getId()%>"></th>
							<td><%=index++%></td>
							<td><%=bean.getFirstName() + " " + bean.getLastName()%></td>
							<td><%=bean.getEmailId()%></td>
							<td><%=bean.getMobileNo()%></td>
							<td><%=bean.getDoctorName()%></td>
							<td><%=bean.getCity()%></td>
							<td><%=bean.getAddress()%></td>
							<td><%=bean.getDeceased()%></td>
							<td align="center"><a class="btn btn-primary" href="PatientCtl?id=<%=bean.getId()%>">Edit</a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<table>
					<thead>
						<tr>
							<td><input type="submit" name="operation"
								class="btn btn-primary" value="<%=PatientListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>></td>

							<td><input type="submit" name="operation"
								class="btn btn-primary" value="<%=PatientListCtl.OP_NEW%>"></td>

							<td><input type="submit" name="operation"
								class="btn btn-primary" value="<%=PatientListCtl.OP_DELETE%>"
								<%=(list.size() == 0) ? "disabled" : ""%>></td>

							<%
								PatientModel model = new PatientModel();
							%>
							<td align="right"><input type="submit" name="operation" class="btn btn-primary"
								value="<%=PatientListCtl.OP_NEXT%>"
								<%=((list.size() < pageSize) ) ? "disabled" : ""%>></td>
					</thead>
				</table>
				<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
					type="hidden" name="pageSize" value="<%=pageSize%>">
			</form>
		</div>
	</div>
	</section>
	<%@ include file="Footer.jsp"%>
</body>
</html>