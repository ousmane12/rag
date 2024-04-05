package code.ousmane.rag.controllers;

import code.ousmane.rag.services.RagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OpenAIRestController {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private RagService ragService;

    @GetMapping("/chat")
    public String chat(String message){
        return chatClient.call(message);
    }

    @GetMapping("/api/rag")
    public Map rag(String query) throws JsonProcessingException {
        String response = ragService.askLLM(query);
        return new ObjectMapper().readValue(response, Map.class);
    }
}
