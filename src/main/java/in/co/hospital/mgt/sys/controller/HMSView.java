package in.co.hospital.mgt.sys.controller;

public interface HMSView {
	
	public String APP_CONTEXT = "/Hopital-Management";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	public String DOCTOR_VIEW = PAGE_FOLDER + "/DoctorView.jsp";	
	public String DOCTOR_LIST_VIEW = PAGE_FOLDER + "/DoctorListView.jsp";
	
	public String PATIENT_VIEW = PAGE_FOLDER + "/PatientView.jsp";	
	public String PATIENT_LIST_VIEW = PAGE_FOLDER + "/PatientListView.jsp";
	
	
	public String RECEPTIONIST_VIEW = PAGE_FOLDER + "/ReceptionistView.jsp";	
	public String RECEPTIONIST_LIST_VIEW = PAGE_FOLDER + "/ReceptionistListView.jsp";
	
	
	public String APPOINTMENT_VIEW = PAGE_FOLDER + "/AppointmentView.jsp";	
	public String APPOINTMENT_LIST_VIEW = PAGE_FOLDER + "/AppointmentListView.jsp";
		
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";
	
	public String PATIENT_CTL = APP_CONTEXT + "/ctl/PatientCtl";
	public String PATIENT_LIST_CTL = APP_CONTEXT + "/ctl/PatientListCtl";
	
	
	public String APPOINTMENT_CTL = APP_CONTEXT + "/ctl/AppointmentCtl";
	public String APPOINTMENT_LIST_CTL = APP_CONTEXT + "/ctl/AppointmentListCtl";

	public String DOCTOR_CTL = APP_CONTEXT + "/ctl/DoctorCtl";
	public String DOCTOR_LIST_CTL = APP_CONTEXT + "/ctl/DoctorListCtl";
	
	public String RECEPTIONIST_CTL = APP_CONTEXT + "/ctl/ReceptionistCtl";
	public String RECEPTIONIST_LIST_CTL = APP_CONTEXT + "/ctl/ReceptionistListCtl";
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
