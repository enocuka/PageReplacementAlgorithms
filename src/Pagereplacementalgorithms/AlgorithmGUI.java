/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pagereplacementalgorithms;

import Algorithm.AlgorithmFIFO;
import Algorithm.AlgorithmLRU;
import Algorithm.AlgorithmOptimal;
import Page.Instruction;
import Page.Page;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author enocu
 */
public class AlgorithmGUI extends TabPane {

    private ArrayList<Integer> referenceString;
    private int nrOfFrames;
    private AlgorithmFIFO algorithmFIFO;
    private AlgorithmOptimal algorithmOptimal;
    private AlgorithmLRU algorithmLRU;

    public AlgorithmGUI(ArrayList<Integer> referenceString, int nrOfFrames) {
        this.referenceString = referenceString;
        this.nrOfFrames = nrOfFrames;
        Tab FIFOTab = new Tab("Algorithm FIFO", createViewFIFO(referenceString, nrOfFrames));
        FIFOTab.setClosable(false);
        Tab OptimalTab = new Tab("Algorithm Optimal", createViewOptimal(referenceString, nrOfFrames));
        OptimalTab.setClosable(false);
        Tab LRUTab = new Tab("Algorithm LRU", createViewLRU(referenceString, nrOfFrames));
        LRUTab.setClosable(false);
        Tab chartsTab = new Tab("Charts",createCharts());
        chartsTab.setClosable(false);
        this.getTabs().addAll(FIFOTab,OptimalTab,LRUTab,chartsTab);
    }

    private BorderPane createViewFIFO(ArrayList<Integer> referenceString, int nrOfFrames) {

        Pane pane = new Pane();
        pane.getStylesheets().add("css/css.css");
        
        algorithmFIFO = new AlgorithmFIFO(referenceString, nrOfFrames);
        ArrayList<Page> pages = algorithmFIFO.getPages();
        
        pane.getChildren().add(createTable(pages, referenceString, nrOfFrames));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new ScrollPane(pane));

        Label pageFaultsLabel = new Label("Page Faults = " + algorithmFIFO.getPageFaults());
        pageFaultsLabel.getStyleClass().add("pageFaults");
        pageFaultsLabel.setPadding(new Insets(0, 60, 0, 0));
        DecimalFormat formatter = new DecimalFormat("#.##");
        Label pageFaultsRateLabel = new Label("Page Faults Rate = " + formatter.format(algorithmFIFO.getRatePageFaults()));
        pageFaultsRateLabel.getStyleClass().add("pageFaults");
        FlowPane flowPane = new FlowPane(pageFaultsLabel, pageFaultsRateLabel);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getStyleClass().add("flowPanebottem");
        borderPane.setBottom(flowPane);
        return borderPane;
    }
    private BorderPane createViewOptimal(ArrayList<Integer> referenceString, int nrOfFrames) {

        Pane pane = new Pane();
        pane.getStylesheets().add("css/css.css");
        
        algorithmOptimal = new AlgorithmOptimal(referenceString, nrOfFrames);
        ArrayList<Page> pages = algorithmOptimal.getPages();
        
        pane.getChildren().add(createTable(pages, referenceString, nrOfFrames));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new ScrollPane(pane));

        Label pageFaultsLabel = new Label("Page Faults = " + algorithmOptimal.getPageFaults());
        pageFaultsLabel.getStyleClass().add("pageFaults");
        pageFaultsLabel.setPadding(new Insets(0, 60, 0, 0));
        DecimalFormat formatter = new DecimalFormat("#.##");
        Label pageFaultsRateLabel = new Label("Page Faults Rate = " + formatter.format(algorithmOptimal.getRatePageFaults()));
        pageFaultsRateLabel.getStyleClass().add("pageFaults");
        FlowPane flowPane = new FlowPane(pageFaultsLabel, pageFaultsRateLabel);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getStyleClass().add("flowPanebottem");
        borderPane.setBottom(flowPane);
        return borderPane;
    }
    
    private BorderPane createViewLRU(ArrayList<Integer> referenceString, int nrOfFrames) {

        Pane pane = new Pane();
        pane.getStylesheets().add("css/css.css");
        
        algorithmLRU = new AlgorithmLRU(referenceString, nrOfFrames);
        ArrayList<Page> pages = algorithmLRU.getPages();
        
        pane.getChildren().add(createTable(pages, referenceString, nrOfFrames));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new ScrollPane(pane));

        Label pageFaultsLabel = new Label("Page Faults = " + algorithmLRU.getPageFaults());
        pageFaultsLabel.getStyleClass().add("pageFaults");
        pageFaultsLabel.setPadding(new Insets(0, 60, 0, 0));
        DecimalFormat formatter = new DecimalFormat("#.##");
        Label pageFaultsRateLabel = new Label("Page Faults Rate = " + formatter.format(algorithmLRU.getRatePageFaults()));
        pageFaultsRateLabel.getStyleClass().add("pageFaults");
        FlowPane flowPane = new FlowPane(pageFaultsLabel, pageFaultsRateLabel);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getStyleClass().add("flowPanebottem");
        borderPane.setBottom(flowPane);
        return borderPane;
    }

    private HBox createTable(ArrayList<Page> pages, ArrayList<Integer> referenceString,int nrOfFrames) {
        HBox hBox = new HBox();
        hBox.setLayoutX(10);
        hBox.setLayoutY(10);
        for (int pos = 0; pos < pages.size(); pos++) {
            ArrayList<Instruction> instructions = pages.get(pos).getInstructions();
            VBox vBox = new VBox(0);
            vBox.setAlignment(Pos.CENTER);
            Label headerLabel = new Label("" + referenceString.get(pos));
            headerLabel.setPrefSize(60, 30);
            headerLabel.getStyleClass().add("labelHeader");
            vBox.getChildren().add(headerLabel);
            for (Instruction instruction : instructions) {
                Label bodyLabel = new Label("");
                if (instruction.getNumber() >= 0) {
                    bodyLabel = new Label("" + instruction.getNumber());
                }
                bodyLabel.setPrefSize(60, 30);
                if (pos > nrOfFrames - 1 && pages.get(pos).isNewPage()) {
                    bodyLabel.getStyleClass().add("labelBodyNew");
                } else {
                    bodyLabel.getStyleClass().add("labelBody");
                }
                vBox.getChildren().add(bodyLabel);
            }
            hBox.getChildren().add(vBox);

        }

        return hBox;
    }
    private GridPane createCharts(){
        GridPane gridPane = new GridPane();
        ArrayList<String> algorithms = new ArrayList<>();
        algorithms.add("FIFO");
        algorithms.add("Optimal");
        algorithms.add("LRU");
        ArrayList<Integer> pageFaults = new ArrayList<>();
        pageFaults.add(algorithmFIFO.getPageFaults());
        pageFaults.add(algorithmOptimal.getPageFaults());
        pageFaults.add(algorithmLRU.getPageFaults());
        gridPane.add(LineChart.getLineChart(algorithms, pageFaults), 0, 0);
        return gridPane;
    }
}
