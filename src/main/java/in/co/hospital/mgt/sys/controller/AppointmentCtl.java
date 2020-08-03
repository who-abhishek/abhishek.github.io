package in.co.hospital.mgt.sys.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.hospital.mgt.sys.bean.AppointmentBean;
import in.co.hospital.mgt.sys.bean.BaseBean;
import in.co.hospital.mgt.sys.exception.ApplicationException;
import in.co.hospital.mgt.sys.exception.DuplicateRecordException;
import in.co.hospital.mgt.sys.model.AppointmentModel;
import in.co.hospital.mgt.sys.util.DataUtility;
import in.co.hospital.mgt.sys.util.DataValidator;
import in.co.hospital.mgt.sys.util.PropertyReader;
import in.co.hospital.mgt.sys.util.ServletUtility;

/**
 * Servlet implementation class AppointmentCtl
 */
@WebServlet(name="AppointmentCtl",urlPatterns={"/ctl/AppointmentCtl"})
public class AppointmentCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(AppointmentCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("AppointmentCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("fName"))) {
            request.setAttribute("fName",
                    PropertyReader.getValue("error.require", "First Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("fName"))) {
			request.setAttribute("fName",
					PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("lName"))) {
            request.setAttribute("lName",
                    PropertyReader.getValue("error.require", "Last Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("lName"))) {
			request.setAttribute("lName",
					PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("docName"))) {
            request.setAttribute("docName",
                    PropertyReader.getValue("error.require", "Doctor Name"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("deceased"))) {
            request.setAttribute("deceased",
                    PropertyReader.getValue("error.require", "Deceased"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("deceased"))) {
            request.setAttribute("deceased",
                    PropertyReader.getValue("error.require", "Deceased"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require","Address"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("time"))) {
			request.setAttribute("time", PropertyReader.getValue("error.require","Time"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require","Mobile No"));
			pass = false;
		}
        
        
        if (DataValidator.isNull(request.getParameter("appDate"))) {
			request.setAttribute("appDate", PropertyReader.getValue("error.require","Appointment Date"));
			pass = false;
		}
        

        

        log.debug("AppointmentCtl validate method end");
        return pass;
    }
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("AppointmentCtl populateBean method start");
		AppointmentBean bean=new AppointmentBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request	.getParameter("fName")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		bean.setLastName(DataUtility.getString(request.getParameter("lName")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setTime(DataUtility.getString(request.getParameter("time")));
		bean.setAppDate(DataUtility.getDate(request.getParameter("appDate")));
		bean.setDoctorName(DataUtility.getString(request.getParameter("docName")));
		bean.setDeceased(DataUtility.getString(request.getParameter("deceased")));
		
		populateDTO(bean, request);
		log.debug("AppointmentCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AppointmentCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   AppointmentModel model = new AppointmentModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				AppointmentBean bean;
				try {
					bean = model.findByPK(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}

			ServletUtility.forward(getView(), request, response);
			log.debug("AppointmentCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		AppointmentModel model=new AppointmentModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			AppointmentBean bean=(AppointmentBean)populateBean(request);
				try {
					if(id>0){
						
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(HMSView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Roll no already exists",
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
		AppointmentBean bean=	(AppointmentBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(HMSView.APPOINTMENT_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HMSView.APPOINTMENT_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(HMSView.APPOINTMENT_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("RoleCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HMSView.APPOINTMENT_VIEW;
	}

}
