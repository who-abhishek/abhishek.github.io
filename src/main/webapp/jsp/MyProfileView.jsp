<%@page import="in.co.hospital.mgt.sys.controller.MyProfileCtl"%>
<%@page import="in.co.hospital.mgt.sys.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.hospital.mgt.sys.util.DataUtility"%>
<%@page import="in.co.hospital.mgt.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<section class="intro">
	<div class="intro-content">
		<div class="container">
			<div class="row">

				<div class="col-lg-6">
					<div class="form-wrapper">
						<div class="wow fadeInRight">

							<div class="panel panel-skin">
								<div class="panel-heading">
									<h3 class="panel-title">My Profile</h3>
									<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
									</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
									</font></b>
								</div>
								<jsp:useBean id="bean"
									class="in.co.hospital.mgt.sys.bean.UserBean" scope="request"></jsp:useBean>


								<div class="panel-body">
									<form action="<%=HMSView.MY_PROFILE_CTL%>" method="post">



										<input type="hidden" name="id" value="<%=bean.getId()%>">
										<input type="hidden" name="createdBy"
											value="<%=bean.getCreatedBy()%>"> <input
											type="hidden" name="modifiedBy"
											value="<%=bean.getModifiedBy()%>"> <input
											type="hidden" name="createdDatetime"
											value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
										<input type="hidden" name="modifiedDatetime"
											value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

										<div class="row">
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>First Name</label> <input type="text" name="firstName"
														placeholder="Enter First Name"
														class="form-control input-md"
														value="<%=DataUtility.getStringData(bean.getFirstName())%>">
													<div>
														<font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font>
													</div>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>Last Name</label> <input type="text" name="lastName"
														class="form-control input-md"
														placeholder="Enter Last Name"
														value="<%=DataUtility.getStringData(bean.getLastName())%>">
													<div>
														<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
													</div>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>Login Id</label> <input type="text" name="login"
														placeholder="Enter Login Id" class="form-control input-md"
														value="<%=DataUtility.getStringData(bean.getLogin())%>">
													<div>
														<font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
													</div>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>Date Of Birth</label> <input type="text" name="dob"
														placeholder="Enter Date Of Birth"
														class="form-control input-md"
														value="<%=DataUtility.getDateString(bean.getDob())%>">
													<div>
														<font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
													</div>
												</div>
											</div>
										</div>




										<div class="row">
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>Mobile No</label> <input type="text" name="mobile"
														placeholder="Enter Mobile No."
														class="form-control input-md"
														value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
													<div>
														<font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
													</div>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>Gender</label>
													<%
														HashMap map = new HashMap();
														map.put("Male", "Male");
														map.put("Female", "Female");
													%>
													<%=HTMLUtility.getList("gender", bean.getGender(), map)%>
													<div>
														<font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
													</div>
												</div>
											</div>

										</div>

										<input type="submit" value="<%=MyProfileCtl.OP_SAVE%>"
											name="operation" class="btn btn-skin btn-block btn-lg">

									</form>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<%@ include file="Footer.jsp"%>
</body>
</html>