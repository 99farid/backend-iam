package com.lawencon.assetsmanagement.sercurity;

import org.springframework.security.core.Authentication;

public interface AuthPrincipal {
	Authentication getAuthentication();
}
