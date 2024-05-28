package hind.gadany.blockchainworkshop.controller;

import hind.gadany.blockchainworkshop.entity.Block;
import hind.gadany.blockchainworkshop.entity.Transaction;
import hind.gadany.blockchainworkshop.service.Blockchain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class WebController {
    @Autowired
    private Blockchain blockchain;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("blockchain", blockchain);
        return "index";
    }

    @PostMapping("/mine")
    public String mineBlock(@RequestParam String data, Model model) {
        Transaction transaction = new Transaction("senderAddress", "recipientAddress", Float.parseFloat(data));
        Block newBlock = new Block(blockchain.getLatestBlock().getIndex() + 1, blockchain.getLatestBlock().getCurrentHash(), Collections.singletonList(transaction), blockchain.getDifficulty());
        blockchain.addBlock(newBlock);
        model.addAttribute("blockchain", blockchain);
        return "redirect:/";
    }

    @GetMapping("/validate")
    public String validateBlockchain(Model model) {
        boolean isValid = blockchain.isChainValid();
        model.addAttribute("isValid", isValid);
        return "redirect:/";
    }
}
