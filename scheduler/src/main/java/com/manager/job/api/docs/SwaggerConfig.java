package com.manager.job.api.docs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.manager"))
				.paths(PathSelectors.any()).build().apiInfo(metaData());

	}

	private ApiInfo metaData() {
		String title = "Job Manager REST API";
		String description = "The goal of Job Management Service is to handle the execution of multiple types of Jobs. The actions performed by these Jobs are not important; possible examples of these Jobs could be performing a data-load into a DWH, performing indexing of some file-based content or sending emails.";
		Contact contactName = new Contact("Dinesh Kori", "https://linkedin.com/dinesh.kori86",
				"dinesh.kori86@gmial.com");
		String license = "FreeWare Version 2.0";
		String licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0";
		String termsOfServiceUrl = "Terms of service";
		String version = "1.0";
		List<VendorExtension> vr = new ArrayList<VendorExtension>();
		ApiInfo apiInfo = new ApiInfo(title, description, version, termsOfServiceUrl, contactName, license, licenseUrl,
				vr);
		return apiInfo;
	}
}