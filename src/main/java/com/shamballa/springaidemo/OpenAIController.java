package com.shamballa.springaidemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
@CrossOrigin("*")
public class OpenAIController {

    private final ChatClient chatClient;

    public OpenAIController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message) {

        ChatResponse chatResponse = chatClient
                .prompt(message)
                .call()
                .chatResponse();

        System.out.println(chatResponse.getMetadata().getModel());

        String response = chatResponse
                .getResult()
                .getOutput()
                .getText();

        return ResponseEntity.ok(response);
    }
}
