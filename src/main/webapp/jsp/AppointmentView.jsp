<%@page import="in.co.hospital.mgt.sys.controller.AppointmentCtl"%>
<%@page import="in.co.hospital.mgt.sys.util.DataUtility"%>
<%@page import="in.co.hospital.mgt.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Appointment</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true
    });
  } );
  </script>
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
                      <h3 class="panel-title">Appointment</h3>
                      <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                    </div>
                    <jsp:useBean id="bean" class="in.co.hospital.mgt.sys.bean.AppointmentBean"
            scope="request"></jsp:useBean>
            
            
                    <div class="panel-body">
                      <form action="<%=HMSView.APPOINTMENT_CTL%>" method="post"  >
                      
                     
              
              <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
                       
                       <div class="row">
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>First Name</label>
                              <input type="text" name="fName"  placeholder="Enter First Name" class="form-control input-md"  value="<%=DataUtility.getStringData(bean.getFirstName())%>" >
                              <div><font  color="red"><%=ServletUtility.getErrorMessage("fName", request)%></font></div>
                            </div>
                          </div>
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Last Name</label>
                              <input type="text" name="lName"  class="form-control input-md" placeholder="Enter Last Name" value="<%=DataUtility.getStringData(bean.getLastName()) %>" >
                              <div><font
                        color="red"> <%=ServletUtility.getErrorMessage("lName", request)%></font></div>
                            </div>
                          </div>
                        </div>
                       
                        <div class="row">
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Doctor Name</label>
                              <input type="text" name="docName"  class="form-control input-md" placeholder="Enter Doctor Name" value="<%=DataUtility.getStringData(bean.getDoctorName()) %>" >
                              <div><font
                        color="red"> <%=ServletUtility.getErrorMessage("docName", request)%></font></div>
                            </div>
                          </div>
                          
                           <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Mobile No</label>
                              <input type="text" name="mobile"  placeholder="Enter Mobile No." class="form-control input-md"  value="<%=DataUtility.getStringData(bean.getMobileNo())%>" >
                              <div><font  color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font></div>
                            </div>
                          </div>
                          
                        </div>
                        
                       
                        <div class="row">
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Appointment Date</label>
                               <input type="text" name="appDate" id="datepicker"  placeholder="Enter Date(dd/MM/yyyy)" class="form-control input-md"  value="<%=DataUtility.getDateString(bean.getAppDate())%>" >
                              <div><font  color="red"><%=ServletUtility.getErrorMessage("appDate", request)%></font></div>
                            </div>
                          </div>
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Time</label>
                              <input type="text" name="time"  class="form-control input-md" placeholder="Enter Age" value="<%=DataUtility.getStringData(bean.getTime()) %>" >
                              <div><font
                        color="red"> <%=ServletUtility.getErrorMessage("time", request)%></font></div>
                            </div>
                          </div>
                        </div>
                        
                        </div>
                        
                         <div class="row">
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Deceased</label>
                              <textarea rows="3" cols="4" placeholder="Enter Deceased" name="deceased" class="form-control input-md" ><%=DataUtility.getStringData(bean.getDeceased()) %></textarea> 
                              <div><font  color="red"><%=ServletUtility.getErrorMessage("deceased", request)%></font></div>
                            </div>
                          </div>
                          <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="form-group">
                              <label>Address</label>
                                <textarea rows="3" cols="4" placeholder="Enter Address" name="address" class="form-control input-md" ><%=DataUtility.getStringData(bean.getAddress()) %></textarea> 
                              <div><font
                       	 color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></div>
                            </div>
                          </div>
                        </div>
                        
                        <input type="submit" value="<%=AppointmentCtl.OP_SAVE%>" name="operation" class="btn btn-skin btn-block btn-lg">
                   
                    </div>
                       </form>
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