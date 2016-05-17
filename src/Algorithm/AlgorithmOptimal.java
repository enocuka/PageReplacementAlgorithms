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
public class AlgorithmOptimal extends Algorithm{

    public AlgorithmOptimal(ArrayList<Integer> referenceString, int numberOfFrame) {
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

        int posOfReferenceString = instruction.getTime();
        for (Instruction instruction1 : instructions) {
            instruction1.setTime(Integer.MAX_VALUE);
        }

        for (int pos1 = super.referenceString.size() - 1; pos1 > posOfReferenceString; pos1--) {
            for (int pos = 0; pos < instructions.size(); pos++) {
                if (super.referenceString.get(pos1) == instructions.get(pos).getNumber()) {
                    instructions.get(pos).setTime(pos1);
                    break;
                }
            }
        }

        Instruction lastInstruction = instructions.get(0);
        for (int pos = 0; pos < instructions.size(); pos++) {
            if (instructions.get(pos).getTime() > lastInstruction.getTime()) {
                lastInstruction = instructions.get(pos);
            }
        }

        instructions.remove(lastInstruction);
        instructions.add(0, instruction);
        page.setNewPage(true);
        return page;
    }
}
