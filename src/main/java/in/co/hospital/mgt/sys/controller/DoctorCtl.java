package in.co.hospital.mgt.sys.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.hospital.mgt.sys.bean.BaseBean;
import in.co.hospital.mgt.sys.bean.RoleBean;
import in.co.hospital.mgt.sys.bean.UserBean;
import in.co.hospital.mgt.sys.exception.ApplicationException;
import in.co.hospital.mgt.sys.exception.DatabaseException;
import in.co.hospital.mgt.sys.exception.DuplicateRecordException;
import in.co.hospital.mgt.sys.model.UserModel;
import in.co.hospital.mgt.sys.util.DataUtility;
import in.co.hospital.mgt.sys.util.DataValidator;
import in.co.hospital.mgt.sys.util.PropertyReader;
import in.co.hospital.mgt.sys.util.ServletUtility;

/**
 * Servlet implementation class DoctorCtl
 */
@WebServlet(name="DoctorCtl",urlPatterns={"/ctl/DoctorCtl"})
public class DoctorCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log=Logger.getLogger(DoctorCtl.class);
       
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("DoctorCtl validate method start");
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
        
        if (DataValidator.isNull(request.getParameter("login"))) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.require", "Login Id"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}
        
       
        
        if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}
        
       

       
        
        if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require","Mobile No"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("age"))) {
			request.setAttribute("age", PropertyReader.getValue("error.require","Age"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("bGroup"))) {
			request.setAttribute("bGroup", PropertyReader.getValue("error.require","Blood Group"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require","Address"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require","City"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("CNIC"))) {
			request.setAttribute("CNIC", PropertyReader.getValue("error.require","CNIC"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("jDate"))) {
			request.setAttribute("jDate", PropertyReader.getValue("error.require","Joining Date"));
			pass = false;
		}
        if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification", PropertyReader.getValue("error.require","Qualification"));
			pass = false;
		}
        if (DataValidator.isNull(request.getParameter("emailId"))) {
			request.setAttribute("emailId", PropertyReader.getValue("error.require","Email Id"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("spcialization"))) {
			request.setAttribute("spcialization", PropertyReader.getValue("error.require","Spcialization"));
			pass = false;
		}
        
        if ("-----Select-----".equalsIgnoreCase(request
				.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
        
        

        log.debug("DoctorCtl validate method end");
        return pass;
    }
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("DoctorCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoleId(RoleBean.DOCTOR);

		bean.setFirstName(DataUtility.getString(request	.getParameter("fName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setJoiningDate(DataUtility.getDate(request.getParameter("jDate")));
		
		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		bean.setAge(DataUtility.getString(request.getParameter("age")));
		
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		
		bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
		
		bean.setBloodGroup(DataUtility.getString(request.getParameter("bGroup")));
		
		bean.setCNIC(DataUtility.getString(request.getParameter("CNIC")));
		
		bean.setQualification(DataUtility.getString(request.getParameter("qualification")));
		
		bean.setSpcialization(DataUtility.getString(request.getParameter("spcialization")));
		populateDTO(bean, request);

		log.debug("DoctorCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("DoctorCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   UserModel model = new UserModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				UserBean bean;
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
			log.debug("DoctorCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("DoctorCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModel model=new UserModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			UserBean bean=(UserBean)populateBean(request);
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
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(HMSView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login Id is already exists",
						request);
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
			UserBean bean=	(UserBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(HMSView.DOCTOR_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HMSView.DOCTOR_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(HMSView.DOCTOR_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("DoctorCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HMSView.DOCTOR_VIEW;
	}

}
