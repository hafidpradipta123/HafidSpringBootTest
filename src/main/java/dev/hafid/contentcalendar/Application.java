package dev.hafid.contentcalendar;

import dev.hafid.contentcalendar.config.ContentCalendarProperties;
import dev.hafid.contentcalendar.model.Content;
import dev.hafid.contentcalendar.model.Status;
import dev.hafid.contentcalendar.model.Type;
import dev.hafid.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.time.LocalDateTime;
@EnableConfigurationProperties(ContentCalendarProperties.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean //create instance of a class on a method
	CommandLineRunner commandLineRunner(ContentRepository repository){
		return args -> {
			// insert some data into the database
			Content content = new Content(
					null,
					"Hello chat gpt this is baso tahu",
					"All about chat GPT",
					Status.IDEA,
					Type.VIDEO,
					LocalDateTime.now(),
					null,
					"" );
			repository.save(content);

		};
	}

}
