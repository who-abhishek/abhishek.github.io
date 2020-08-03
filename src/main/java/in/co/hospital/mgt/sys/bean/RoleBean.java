package in.co.hospital.mgt.sys.bean;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

/**
 * Role JavaBean encapsulates Role attributes
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

public class RoleBean extends BaseBean {
	/**
	 * Predefined Role constants
	 */
	public static final int ADMIN = 1;
	public static final int DOCTOR = 2;
	public static final int RECEPTIONIST= 3;
	

	/**
	 * Role Name
	 */

	private String name;

	/**
	 * Role Description
	 */
	private String description;

	/**
	 * accessor
	 */

	/**
	 * @return Name of Role
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param to
	 *            set Name Of Role
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Description of Role
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * @param to
	 *            set Description Of Role
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}
}
