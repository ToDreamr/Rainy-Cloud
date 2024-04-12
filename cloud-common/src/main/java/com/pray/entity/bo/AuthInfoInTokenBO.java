package com.pray.entity.bo;

/**
 * 保存在token信息里面的用户信息
 *
 * com.mall4j.cloud.auth.service.impl.AuthAccountServiceImpl
 * com.mall4j.cloud.auth.controller.LoginController
 * @author FrozenWatermelon
 * @date 2020/7/3
 */
public class AuthInfoInTokenBO {

	/**
	 * 用户在自己系统的用户id
	 */
	private AuthUser authUser;
	private String accessToken;

	private String refreshToken;

	private Integer expiresIn;

	public AuthUser getAuthUser() {
		return authUser;
	}

	public void setAuthUser(AuthUser authUser) {
		this.authUser = authUser;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public String toString() {
		return "AuthInfoInTokenBO{" +
				"authUser=" + authUser +
				", accessToken='" + accessToken + '\'' +
				", refreshToken='" + refreshToken + '\'' +
				", expiresIn=" + expiresIn +
				'}';
	}
}
