package code.ousmane.rag;

import code.ousmane.rag.services.RagService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class RagApplication {

	public static void main(String[] args) {
		SpringApplication.run(RagApplication.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunner(RagService ragService,
										@Value("classpath:pdfs/*") Resource[] pdfRessources){
		return args -> {
			ragService.textEmbedding(pdfRessources);
			String query = "Donne moi pour chaque document, la liste des diplomes et les compétences acquises au format json";
			String response = ragService.askLLM(query);
			System.out.println(response);
		};
	}*/


/*	```json
	{
		"8dcf5975-73e9-423d-96d8-f954fdb2ab59": {
		"diplomes": [
		"DUT",
				"DIC",
				"DST",
				"DIT",
				"LICENCE",
				"MASTER"
    ],
		"competences_acquises": [
		"Réaliser et maintenir des applications informatiques",
				"Gérer des projets de développement informatique",
				"Assister des ingénieurs et des chefs de projets en informatique",
				"Analyser ou constituer un cahier des charges",
				"Réaliser des logiciels performants",
				"Assurer la validation, le suivi et la maintenance de logiciels"
    ]
	},
		"168de77d-9a1b-44ff-9167-fd34924eef86": {
		"diplomes": [
		"DUT",
				"DIC",
				"DST",
				"DIT",
				"LICENCE",
				"MASTER",
				"D"
    ],
		"competences_acquises": [
		"Concevoir, optimiser et maintenir des systèmes de gestion de données massives",
				"Développer des interfaces intuitives",
				"Créer des logiciels d'aide à la décision",
				"Mettre en place des programmes d'automatisation",
				"Gérer des systèmes de gestion, de collecte et d'analyse de données à grande échelle",
				"Utiliser des outils de fouille de données et de recherche d'information",
				"Maîtriser des algorithmes d'apprentissage automatique et de reconnaissance des formes"
    ]
	},
		"31b6ae74-3c5d-41ae-be14-d98af4c962d8": {
		"diplomes": [
		"MASTER"
    ],
		"competences_acquises": [
		"Concevoir, optimiser et maintenir des systèmes de gestion de données massives",
				"Développer des interfaces intuitives",
				"Créer des logiciels d'aide à la décision",
				"Mettre en place des programmes d'automatisation",
				"Gérer des systèmes de gestion, de collecte et d'analyse de données à grande échelle",
				"Utiliser des outils de fouille de données et de recherche d'information",
				"Maîtriser des algorithmes d'apprentissage automatique et de reconnaissance des formes"
    ]
	},
		"a0389545-5c77-4da5-8529-f8c7eb495149": {
		"diplomes": [
		"DST",
				"DIC",
				"DIT",
				"L",
				"MASTER"
    ],
		"competences_acquises": [
		"Réaliser et maintenir des applications informatiques",
				"Gérer des projets de développement informatique",
				"Assister des ingénieurs et des chefs de projets en informatique",
				"Analyser ou constituer un cahier des charges",
				"Réaliser des logiciels performants",
				"Assurer la validation, le suivi et la maintenance de logiciels"
    ]
	}
	}
```*/
}
