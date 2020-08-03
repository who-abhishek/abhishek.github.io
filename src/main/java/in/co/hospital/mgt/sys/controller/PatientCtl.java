package in.co.hospital.mgt.sys.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.hospital.mgt.sys.bean.BaseBean;
import in.co.hospital.mgt.sys.bean.PatientBean;
import in.co.hospital.mgt.sys.bean.RoleBean;
import in.co.hospital.mgt.sys.bean.UserBean;
import in.co.hospital.mgt.sys.exception.ApplicationException;
import in.co.hospital.mgt.sys.exception.DuplicateRecordException;
import in.co.hospital.mgt.sys.model.PatientModel;
import in.co.hospital.mgt.sys.model.UserModel;
import in.co.hospital.mgt.sys.util.DataUtility;
import in.co.hospital.mgt.sys.util.DataValidator;
import in.co.hospital.mgt.sys.util.PropertyReader;
import in.co.hospital.mgt.sys.util.ServletUtility;

/**
 * Servlet implementation class PatientCtl
 */
@WebServlet(name="PatientCtl",urlPatterns={"/ctl/PatientCtl"})
public class PatientCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(PatientCtl.class);
    
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("PatientCtl validate method start");
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
        
        if (DataValidator.isNull(request.getParameter("doctor"))) {
            request.setAttribute("doctor",
                    PropertyReader.getValue("error.require", "Doctor"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("deceased"))) {
			request.setAttribute("deceased",
					PropertyReader.getValue("error.require", "Deceased"));
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
        
        
        
        if (DataValidator.isNull(request.getParameter("emailId"))) {
			request.setAttribute("emailId", PropertyReader.getValue("error.require","Email Id"));
			pass = false;
		}
        
       
        
        if ("-----Select-----".equalsIgnoreCase(request
				.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
        
        if ("-----Select-----".equalsIgnoreCase(request
				.getParameter("material"))) {
			request.setAttribute("material",
					PropertyReader.getValue("error.require", "Material Status"));
			pass = false;
		}
        
        

        log.debug("PatientCtl validate method end");
        return pass;
    }
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("PatientCtl Method populatebean Started");

		PatientBean bean = new PatientBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

	
		bean.setFirstName(DataUtility.getString(request	.getParameter("fName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lName")));

		bean.setDoctorName(DataUtility.getString(request.getParameter("doctor")));

		bean.setDeceased(DataUtility.getString(request.getParameter("deceased")));

		
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
		
		bean.setMaritialStatus(DataUtility.getString(request.getParameter("material")));
		
		
		populateDTO(bean, request);

		log.debug("PatientCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PatientCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   PatientModel model = new PatientModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				PatientBean bean;
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
			log.debug("PatientCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("DoctorCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		PatientModel model=new PatientModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			PatientBean bean=(PatientBean)populateBean(request);
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
				ServletUtility.setErrorMessage("Email Id is already exists",
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
			PatientBean bean=	(PatientBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(HMSView.PATIENT_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HMSView.PATIENT_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(HMSView.PATIENT_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("DoctorCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HMSView.PATIENT_VIEW;
	}

}
