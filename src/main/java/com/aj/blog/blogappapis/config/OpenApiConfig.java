package com.aj.blog.blogappapis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title = "Blog App APIs",
				description = "This application contains APIs for blogging app.",
				version = "1.0",
				contact = @Contact(
						name = "Aj Softwares",
						email = "asksoftwares1@gmail.com",
						url = "https://clgportal.vercel.app"
					),
				termsOfService = "ajsoftwares@2025"
				),
				security = @SecurityRequirement(name = "bearerAuth") // Enable globally
		)
@SecurityScheme(
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    scheme = "bearer",
	    bearerFormat = "JWT"
	)
public class OpenApiConfig {
	
}
