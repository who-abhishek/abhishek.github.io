<%@page import="in.co.hospital.mgt.sys.util.DataUtility"%>
<%@page import="in.co.hospital.mgt.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section  class="intro">
      <div class="intro-content">
        <div class="container">
          <div class="row">
           
            <div class="col-lg-6">
              <div class="form-wrapper">
                <div class="wow fadeInRight" >

                  <div class="panel panel-skin">
                    <div class="panel-heading">
                      <h3 class="panel-title">Login</h3>
                      <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                    </div>
                    <jsp:useBean id="bean" class="in.co.hospital.mgt.sys.bean.UserBean"
            scope="request"></jsp:useBean>
            
            
                    <div class="panel-body">
                      <form action="<%=HMSView.LOGIN_CTL%>" method="post"  >
                      
                      <% String uri=(String)request.getAttribute("uri");%>
		
              <input type="hidden" name="uri" value="<%=uri%>">
              
              <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
                       
                       
                        <div class="row">
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Login Id</label>
                              <input type="text" name="login"  placeholder="Enter Login Id" class="form-control input-md"  value="<%=DataUtility.getStringData(bean.getLogin())%>" >
                              <div><font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></div>
                            </div>
                          </div>
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Password</label>
                              <input type="password" name="password"  class="form-control input-md" placeholder="Enter Password" value="<%=DataUtility.getStringData(bean.getPassword()) %>" >
                              <div><font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></div>
                            </div>
                          </div>
                        </div>

                        <input type="submit" value="<%=LoginCtl.OP_SIGN_IN %>" name="operation" class="btn btn-skin btn-block btn-lg">

                        <p class="lead-footer"><a href="<%=HMSView.FORGET_PASSWORD_CTL%>">Forget my password</a></p>

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

<%@ include file="Footer.jsp" %>
</body>
</html>