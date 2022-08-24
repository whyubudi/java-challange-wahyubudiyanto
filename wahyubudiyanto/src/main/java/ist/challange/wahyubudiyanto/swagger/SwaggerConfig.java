package ist.challange.wahyubudiyanto.swagger;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ist.challange.wahyubudiyanto"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Java Challange",
                "Wahyu Budiyanto",
                "1.0",
                "Term of service",
                new springfox.documentation.service.Contact("Wahyu Budiyanto", "", "wahyubudi78@gmail.com"),
                "",
                "",
                Collections.emptyList()
        );
        return apiInfo;
    }
}
