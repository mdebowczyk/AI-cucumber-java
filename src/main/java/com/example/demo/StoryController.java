package com.example.demo;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StoryController {

    @GetMapping("/story")
    public String generateStory(@RequestParam String prompt) {
        String projectId = "your-google-cloud-project-id";
        String location = "us-central1";
        String modelName = "gemini-1.5-pro-preview-0409";

        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(prompt);
            return response.toString();
        } catch (IOException e) {
            return "Error generating story: " + e.getMessage();
        }
    }
}
