<%@page import="in.co.hospital.mgt.sys.util.DataUtility"%>
<%@page import="in.co.hospital.mgt.sys.controller.ForgetPasswordCtl"%>
<%@page import="in.co.hospital.mgt.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
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
                      <h3 class="panel-title">Forget Password</h3>
                      <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                    </div>
                    <jsp:useBean id="bean" class="in.co.hospital.mgt.sys.bean.UserBean"
            scope="request"></jsp:useBean>
            
            
                    <div class="panel-body">
                      <form action="<%=HMSView.FORGET_PASSWORD_CTL%>" method="post"  >
                      
                   
                       
                       
                        <div class="row">
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Login Id</label>
                              <input type="text" name="login"  placeholder="Enter Login Id" class="form-control input-md"  value="<%=DataUtility.getStringData(bean.getLogin())%>" >
                              <div><font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></div>
                            </div>
                          </div>
                          

                        <input type="submit" value="<%=ForgetPasswordCtl.OP_GO%>" name="operation" class="btn btn-skin btn-block btn-lg">

                       

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