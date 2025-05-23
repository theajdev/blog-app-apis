package com.aj.blog.blogappapis;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aj.AJLogger;
import com.aj.blog.blogappapis.entities.Role;
import com.aj.blog.blogappapis.entities.User;
import com.aj.blog.blogappapis.repositories.RoleRepository;
import com.aj.blog.blogappapis.repositories.UserRepo;
import com.aj.blog.blogappapis.utils.AppConstants;

@SpringBootApplication
public class BlogAppApisApplication extends SpringBootServletInitializer implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
		//AJLogger.log("BlogApp", "HELLO");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * System.out.println("Bcrypted password for rahul: "+passwordEncoder.encode(
		 * "rahul@123"));
		 * System.out.println("Bcrypted password for ram: "+passwordEncoder.encode(
		 * "ram@123"));
		 * 
		 * @SuppressWarnings("deprecation") Key key =
		 * Keys.secretKeyFor(SignatureAlgorithm.HS512); String base64Key =
		 * Base64.getEncoder().encodeToString(key.getEncoded());
		 * System.out.println("Base64 Key: " + base64Key);
		 */
		
		try {
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");
			
			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_USER");
			
			List<Role> roles = List.of(role,role1);
			List<Role> result = roleRepo.saveAll(roles);
			
			
			User user=new User();
			//user.setId(AppConstants.userId);
			user.setEmail(AppConstants.email);
			user.setAbout(AppConstants.about);
			user.setName(AppConstants.userName);
			// roles
			Role adminRole = roleRepo.findById(AppConstants.isAdmin ? AppConstants.ADMIN_USER : AppConstants.NORMAL_USER)
					.orElseThrow(() -> new RuntimeException("Role not found"));
			
			user.setRoles(Set.of(adminRole));
			
			user.setPassword(AppConstants.password);
			User save = userRepo.save(user);
			/*
			 * result.forEach(r->{ AJLogger.log("BlogApp", "New Roles:"+r); });
			 */
			
		} catch (Exception e) {
			AJLogger.log("BlogApp", "Exception: "+e);
		}
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        // Pass your main application class to the builder.
	        return application.sources(BlogAppApisApplication.class);
	    }
}
