package com.companny.pinponalv.shoptech.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT Authorization header using the Bearer scheme."
)

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                .title("ShopTech API")
                .version("1.0")
                .description("ShopTech RESTFUL API" +
                        "Complete store management. Enables making purchases."
                        + "Includes role-based access control")
                        .contact(new Contact()
                                .name("pinponalv")
                                .url("https://www.github.com/pinponalv")
                                .email("pinpondev@gmail.com")));
    }
}
