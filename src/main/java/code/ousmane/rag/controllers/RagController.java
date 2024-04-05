package code.ousmane.rag.controllers;

import code.ousmane.rag.services.RagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RagController {
    @Autowired
    private RagService ragService;

    @GetMapping("/rag")
    public String index(@RequestParam(name = "query", defaultValue = "") String query, Model model){
        if(query.equals("")) return "rag";
        String response = ragService.askLLM(query);
        model.addAttribute("query", query);
        model.addAttribute("response", response);
        return "rag";
    }
}
