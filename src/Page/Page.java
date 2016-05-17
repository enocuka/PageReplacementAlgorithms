/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Page;

import java.util.ArrayList;

/**
 *
 * @author enocu
 */
public class Page {

    private int numberOfFrames;
    private ArrayList<Instruction> instructions;
    private boolean newPage;

    public Page(int numberOfFrames) {
        this.numberOfFrames = numberOfFrames;
        newPage = true;
        instructions = new ArrayList<Instruction>();
        for (int frame = 0; frame < numberOfFrames; frame++) {
            instructions.add(new Instruction(-1, 0));
        }
    }

    public Page clonePage(Page page) {
        page.setNumberOfFrames(numberOfFrames);
        page.getInstructions().clear();
        for (Instruction instruction : instructions) {
            page.getInstructions().add(new Instruction(instruction.getNumber(), instruction.getTime()));
        }
        return page;
    }

    public boolean contentInstruction(Instruction instruction) {
        
        for (Instruction instruction1 : instructions) {
            if (instruction1.getNumber() == instruction.getNumber()) {
                instruction1.setTime(instruction.getTime());
                return true;
            }
        }
        return false;
    }


    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    public void setNumberOfFrames(int numberOfFrames) {
        this.numberOfFrames = numberOfFrames;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public boolean isNewPage() {
        return newPage;
    }

    public void setNewPage(boolean newPage) {
        this.newPage = newPage;
    }


}
