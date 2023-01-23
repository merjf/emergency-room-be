package com.portaleps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.portaleps.exception.CustomException;
import com.portaleps.helper.PrinterHelper;
import com.portaleps.model.entity.Role;
import com.portaleps.model.entity.User;
import com.portaleps.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EntityScan( basePackages = {"com.portaleps.model.entity"} )
public class PortalepsApplication {

	private static final Logger LOGGER = LogManager.getLogger(PortalepsApplication.class);

	@Value("${data.path}")
	private String DATA_DIR;
	@Value("${output.path}")
	private String OUTPUT_DIR;
	@Value("${logs.path}")
	private String LOGS_DIR;

	@Autowired
	private PrinterHelper printerHelper;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository userRepository) {
		return (args) -> {
			User user = userRepository.findByUsername("admin");
			if(user == null){
				user = new User();
				user.setName("Admin");
				user.setSurname("Admin");
				user.setUsername("admin");
				user.setEmail("admin@admin.com");
				user.setActive(true);
				user.setChangePassword(false);
				user.setPassword(passwordEncoder().encode("admin"));
				List<Role> roles = new ArrayList<>();
				roles.add(Role.ROLE_ADMIN);
				user.setRoles(roles);
				userRepository.save(user);
			}
			initFolders();
		};
	}

	public void initFolders(){
		LOGGER.info("initFolders() - start");
		try{
			Files.createDirectories(Paths.get(DATA_DIR));
			Files.createDirectories(Paths.get(OUTPUT_DIR));
			Files.createDirectories(Paths.get(LOGS_DIR));
			LOGGER.info("initFolders() - folders created");
		} catch(Exception e){
			LOGGER.error("initFolders() - folders not created");
			LOGGER.error("initFolders() - end");
			e.printStackTrace();
			throw new CustomException("Folders not created", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("initFolders() - end");
	}

	@Bean
	public XmlMapper xmlMapper(){
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		xmlMapper.findAndRegisterModules();
		return xmlMapper;
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return objectMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(PortalepsApplication.class, args);

	}

}
