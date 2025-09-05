package com.pharmacy.pharmacy.Controller;

import com.pharmacy.pharmacy.DTO.SymptomRequest;
import com.pharmacy.pharmacy.Service.CustomAiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SymptomController {
    private final CustomAiService aiService;

    public SymptomController(CustomAiService aiService) {
        this.aiService = aiService;
    }



    @PostMapping("/predict")
    public String predict(@RequestBody SymptomRequest request) {
        // Join symptoms into a single string
        String symptoms = String.join(", ", request.getSymptoms());
        return aiService.predict(symptoms);
    }

}
