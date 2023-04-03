package com.opencloudplatform.edm.domain;

import java.util.Objects;

public class Admin {
	private Integer adminInx;
	private String adminId;
	private String adminPw;

	public Admin (){}

	public Admin(String adminId, String adminPw, String adminName) {
		this.adminId = adminId;
		this.adminPw = adminPw;
		this.adminName = adminName;
	}
	private String adminName;

	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getAdminInx() {
		return adminInx;
	}
	public void setAdminInx(int adminInx) {
		this.adminInx = adminInx;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	@Override
	public String toString() {
		return "Admin [adminInx=" + adminInx + ", adminId=" + adminId + ", adminPw=" + adminPw + ", adminName="
				+ adminName + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Admin admin = (Admin) o;
		return Objects.equals(adminInx, admin.adminInx) && Objects.equals(adminId, admin.adminId) && Objects.equals(adminPw, admin.adminPw) && Objects.equals(adminName, admin.adminName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminInx, adminId, adminPw, adminName);
	}
}

