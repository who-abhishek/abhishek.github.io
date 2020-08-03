<%@page import="in.co.hospital.mgt.sys.controller.ChangePasswordCtl"%>
<%@page import="in.co.hospital.mgt.sys.util.DataUtility"%>
<%@page import="in.co.hospital.mgt.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
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
									<h3 class="panel-title">Change Password</h3>
									<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
									</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
									</font></b>
								</div>
								<jsp:useBean id="bean"
									class="in.co.hospital.mgt.sys.bean.UserBean" scope="request"></jsp:useBean>


								<div class="panel-body">
									<form action="<%=HMSView.CHANGE_PASSWORD_CTL%>" method="post">



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
													<label>Old Password</label> <input type="password" name="oldPassword"
														placeholder="Enter Old Password"
														class="form-control input-md"
														value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>>
													<div>
														<font color="red"><%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
													</div>
												</div>
											</div>
											
											
										</div>

										<div class="row">
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>New Password</label> <input type="password" name="newPassword"
														placeholder="Enter New Password"
														class="form-control input-md"
														value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%>>
													<div>
														<font color="red"><%=ServletUtility.getErrorMessage("newPassword", request)%></font>
													</div>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6 col-md-6">
												<div class="form-group">
													<label>Confirm Password</label> <input type="password" name="confirmPassword"
														placeholder="Enter Confirm Password"
														class="form-control input-md"
														value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>>
													<div>
														<font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
													</div>
												</div>
											</div>
										</div>

										</div>

										<input type="submit" value="<%=ChangePasswordCtl.OP_SAVE%>"
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