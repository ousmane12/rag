package code.ousmane.rag.services;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RagService  {

    @Value("${spring.ai.openai.api-key}")
    private String API_KEY;
    @Autowired
    private VectorStore vectorStore;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String askLLM(String query) {

        List<Document> documentList = vectorStore.similaritySearch(query);
        String systemMessageTemplate = """
                 Answer the following question based only on the provided CONTEXT.
                 If the answer is not found in the CONTEXT, respond "I don't know".
                 CONTEXT:
                     {CONTEXT}
                """;
        Message systemMessage = new SystemPromptTemplate(systemMessageTemplate)
                .createMessage(Map.of("CONTEXT", documentList));
        UserMessage userMessage = new UserMessage(query);
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        OpenAiApi apiKey = new OpenAiApi(API_KEY);
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .withTemperature(0F)
                .withMaxTokens(2000)
                .build();
        OpenAiChatClient openAiChatClient = new OpenAiChatClient(apiKey, options);
        ChatResponse response = openAiChatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    public void textEmbedding(Resource[] pdfRessources) {
        jdbcTemplate.update("delete from vector_store");
        PdfDocumentReaderConfig pdfDocumentReaderConfig = PdfDocumentReaderConfig.defaultConfig();
        String content = "";
        for(Resource resource : pdfRessources){
            PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(resource, pdfDocumentReaderConfig);
            List<Document> documentList = pagePdfDocumentReader.get();
            content += documentList.stream().map(document -> document.getContent()).collect(Collectors.joining("\n")) + "\n";
        }

        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<String> chunks = tokenTextSplitter.split(content, 1000);
        List<Document> chuncksDoc = chunks.stream().map(chunck -> new Document(chunck)).collect(Collectors.toList());
        vectorStore.accept(chuncksDoc);
    }

}
