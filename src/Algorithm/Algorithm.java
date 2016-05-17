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
public abstract class Algorithm {
    private ArrayList<Page> pages;
    protected ArrayList<Integer> referenceString;
    private int numberOfFrame;
    
    public Algorithm(ArrayList<Integer> referenceString, int numberOfFrame) {
        this.referenceString = referenceString;
        this.numberOfFrame = numberOfFrame;
        pages = new ArrayList<Page>();
        generatePages();
    }
    public void generatePages() {
        for (int input = 0; input < referenceString.size(); input++) {
            Page page = new Page(numberOfFrame);
            Instruction instruction = new Instruction(referenceString.get(input), input);
            if (!pages.isEmpty()) {
                page = pages.get(pages.size() - 1).clonePage(page);
            }
            pages.add(addInstructionInPage(page, instruction));
        }
    }
    public int getPageFaults(){
        int pageFaults = 0;
        if(pages.isEmpty()){
            return pageFaults;
        }
        for (int pos = 0; pos < pages.size(); pos++) {
            if (pos > numberOfFrame - 1 && pages.get(pos).isNewPage()) {
                pageFaults++;
            }
        }
        return pageFaults;
    }
    public double getRatePageFaults(){
        return (double)getPageFaults()/(double)referenceString.size();
    }
    
    /**
     *
     * @param page
     * @param instruction
     * @return
     */
    protected abstract Page addInstructionInPage(Page page, Instruction instruction);
    
    
    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public ArrayList<Integer> getReferenceString() {
        return referenceString;
    }

    public void setReferenceString(ArrayList<Integer> referenceString) {
        this.referenceString = referenceString;
    }

    public int getNumberOfFrame() {
        return numberOfFrame;
    }

    public void setNumberOfFrame(int numberOfFrame) {
        this.numberOfFrame = numberOfFrame;
    }
    
}
