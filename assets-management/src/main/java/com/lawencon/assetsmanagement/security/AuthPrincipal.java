package com.lawencon.assetsmanagement.security;

import org.springframework.security.core.Authentication;

public interface AuthPrincipal {
	Authentication getAuthentication();
}
