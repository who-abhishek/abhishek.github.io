package in.co.hospital.mgt.sys.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.hospital.mgt.sys.bean.BaseBean;
import in.co.hospital.mgt.sys.bean.UserBean;
import in.co.hospital.mgt.sys.exception.ApplicationException;
import in.co.hospital.mgt.sys.exception.DuplicateRecordException;
import in.co.hospital.mgt.sys.model.UserModel;
import in.co.hospital.mgt.sys.util.DataUtility;
import in.co.hospital.mgt.sys.util.DataValidator;
import in.co.hospital.mgt.sys.util.PropertyReader;
import in.co.hospital.mgt.sys.util.ServletUtility;



/**
 * Servlet implementation class MyProfileCtl
 */


/**
 * MyProfile functionality Controller. Performs operation for Add, update
 * operations of MyProfile
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 */
@WebServlet(name="MyProfileCtl",urlPatterns={"/ctl/MyProfileCtl"})
public class MyProfileCtl extends BaseCtl 
{
	private static final long serialVersionUID = 1L;
	
	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
    public static final String OP_CHANGE_MY_PASSWORD="ChangePassword";   
	
    private static Logger log=Logger.getLogger(MyProfileCtl.class);
        
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("MyProfileCtl Method validate Started");
	
		boolean pass=true;
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		if(OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)||op==null){
			return pass;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("login"))){			
		request.setAttribute("login", PropertyReader.getValue("error.require","Login ID"));
		pass=false;
		}
		if(DataValidator.isNull(request.getParameter("firstName"))){
			System.out.println("firstName"+request.getParameter("firstName"));
		request.setAttribute("firstName", PropertyReader.getValue("error.require","First Name"));
		pass=false;
		}else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName",PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require","Last Name"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName",PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		} 
		
		
		 if (DataValidator.isNull(request.getParameter("mobile"))) {
	            request.setAttribute("mobile",PropertyReader.getValue("error.require", "Mobile No"));
	            pass = false;
	        }	
				
		log.debug("MyProfileCtl Method validate Ended");
		return pass;
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
	log.debug("MyProfileCtl Method PopulateBean Started ");
		UserBean bean=new UserBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		
		populateDTO(bean, request);
	
		log.debug("MyProfileCtl Method PopulateBean End ");
		return bean;
	}



	/**
     * Display Concept for viewing profile page view
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		log.debug("MyProfileCTl Method doGet Started");
	
		HttpSession session=request.getSession(true);
		
		UserBean userBean=(UserBean) session.getAttribute("user");
		
		long id=userBean.getId();
		
		String op=DataUtility.getString(request.getParameter("operation"));
		//get Model
		
		UserModel model=new UserModel();
		
		
		if(id>0||op !=null){
			System.out.println("in id>0 condition");
			UserBean bean;
			try{
				bean=model.findByPK(id);
				ServletUtility.setBean(bean, request);
				
			}catch(ApplicationException e){
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		 log.debug("MyProfileCtl Method doGet Ended");
		}
		/**
	     * Submit Concept
	     */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("MyprofileCtl Method doPost Started");
		
		HttpSession session=request.getSession(true);
		
		UserBean userBean=(UserBean) session.getAttribute("user");
		
		long id=userBean.getId();
		
		String op=DataUtility.getString(request.getParameter("operation"));
		 // get model
		UserModel model=new UserModel();
		
		if(OP_SAVE.equalsIgnoreCase(op)){
			UserBean bean=(UserBean) populateBean(request);
			try{
				if(id>0){
					userBean.setFirstName(bean.getFirstName());
					userBean.setLastName(bean.getLastName());
					userBean.setGender(bean.getGender());
					userBean.setMobileNo(bean.getMobileNo());
					userBean.setDob(bean.getDob());
					userBean.setLogin(bean.getLogin());
		
					model.update(userBean);
					
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Profile has been updated Successfully. ", request);
				}
				
			}catch(ApplicationException e){
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}catch(DuplicateRecordException e){
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		}
		else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HMSView.CHANGE_PASSWORD_CTL, request, response);
		return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("MyProfileCtl doPost method end");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {

		
		return HMSView.MY_PROFILE_VIEW;
	}

}
