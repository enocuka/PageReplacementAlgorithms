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
public class AlgorithmLRU extends Algorithm {

    public AlgorithmLRU(ArrayList<Integer> referenceString, int numberOfFrame) {
        super(referenceString, numberOfFrame);
    }

    @Override
    protected Page addInstructionInPage(Page page, Instruction instruction) {

        if (page.contentInstruction(instruction)) {
            page.setNewPage(false);
            return page;
        }

        ArrayList<Instruction> instructions = page.getInstructions();

        for (int pos = 0; pos < instructions.size(); pos++) {
            if (instructions.get(pos).getNumber() < 0) {
                instructions.set(pos, instruction);
                page.setNewPage(true);
                return page;
            }
        }
        Instruction olderInstruction = instructions.get(0);
        for (int pos = 0; pos < instructions.size(); pos++) {
            if (instructions.get(pos).getTime() < olderInstruction.getTime()) {
                olderInstruction = instructions.get(pos);
            }
        }
        instructions.remove(olderInstruction);
        instructions.add(0, instruction);
        page.setNewPage(true);
        return page;
    }

}
