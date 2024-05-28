package hind.gadany.blockchainworkshop.service;

import hind.gadany.blockchainworkshop.entity.Block;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Blockchain {
    private ArrayList<Block> chain;
    private int difficulty;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.difficulty = 4;
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, "0", new ArrayList<>(), difficulty);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < chain.size(); i++) {
            currentBlock = chain.get(i);
            previousBlock = chain.get(i - 1);

            if (!currentBlock.calculateHash().equals(currentBlock.getCurrentHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }

            if (!previousBlock.getCurrentHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            if (!currentBlock.getCurrentHash().substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
