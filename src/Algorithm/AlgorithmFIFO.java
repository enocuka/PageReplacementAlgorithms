/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import Page.Instruction;
import Page.Page;
import java.util.ArrayList;

/**
 *
 * @author enocu
 */
public class AlgorithmFIFO extends Algorithm {

    public AlgorithmFIFO(ArrayList<Integer> referenceString, int numberOfFrame) {
        super(referenceString, numberOfFrame);
    }

    @Override
    protected Page addInstructionInPage(Page page, Instruction instruction) {

        if (page.contentInstruction(instruction)) {
            page.setNewPage(false);
            return page;
        }

        ArrayList<Instruction> instructions = page.getInstructions();
        instructions.remove(0);
        instructions.add(instruction);
        page.setNewPage(true);
        return page;
    }

}
