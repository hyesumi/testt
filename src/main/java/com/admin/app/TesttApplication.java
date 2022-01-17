package com.admin.app;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TesttApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TesttApplication.class);
	}

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder()
				.bannerMode(Banner.Mode.CONSOLE)
				.sources(TesttApplication.class)
				.run(args);

	}

}