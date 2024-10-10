package pl.mkan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessApplication {

	@Value("${server.front}")
	private String frontUri;

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(@NonNull CorsRegistry registry) {
//				registry
//						.addMapping("/**")
//						.allowedOrigins(frontUri)
//						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//						.allowedHeaders("*")
//						.allowCredentials(true)
//						.maxAge(3600);
//			}
//		};
//	}

}
