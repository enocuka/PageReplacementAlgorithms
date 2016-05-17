/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pagereplacementalgorithms;

import Algorithm.AlgorithmFIFO;
import Algorithm.AlgorithmLRU;
import Algorithm.AlgorithmOptimal;
import Page.Page;
import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author enocu
 */
public class pageReplacementAlgorithms extends Application {

    private Label nrOfFramesLabel;
    private TextField nrOfFramesTextField;
    private Label inputStringLabel;
    private TextField inputStringTextField;
    private Button inputStringButton;
    private HBox sequenceHbox;
    private Button generateButton;
    private Button clearAllButton;
    private AlgorithmGUI algorithmGUI;
    private Label imageLabel;
    private FlowPane imageFlowPane;
    private BorderPane root;
    private Tooltip tooltip;
    private ArrayList<Button> referenceStringButton;

    @Override
    public void start(Stage primaryStage) {

        Image image = new Image(getClass().getResourceAsStream("images/gentleman.png"));
        Tooltip imageTooltip = new Tooltip("Welcome");
        imageTooltip.getStyleClass().add("imagetooltip");
        imageLabel = new Label("", new ImageView(image));
        imageLabel.setTooltip(imageTooltip);
        imageFlowPane = new FlowPane(imageLabel);
        imageFlowPane.setAlignment(Pos.CENTER);
        referenceStringButton = new ArrayList<>();
        root = new BorderPane();
        root.getStylesheets().add("css/css.css");
        tooltip = new Tooltip("Only Integer");
        tooltip.getStyleClass().add("buttonSequence");

        createFramesField();
        createInputStringField();
        generateButton();
        clearAllButton();

        Pane topPane = new Pane();
        topPane.setPrefHeight(50);
        ScrollPane topPaneScrollPane = new ScrollPane();
        topPaneScrollPane.setContent(topPane);
        sequenceHbox = new HBox(10);
        sequenceHbox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        Label sequenceLabel = new Label("Sequence");
        sequenceLabel.getStyleClass().add("textFilter");
        hBox.getChildren().addAll(sequenceLabel, sequenceHbox);
        hBox.setLayoutX(10);
        hBox.setLayoutY(10);
        topPane.getChildren().add(hBox);
        VBox topVbox = new VBox(10);
        HBox topHbox = new HBox(10);
        topHbox.setAlignment(Pos.CENTER);
        Pane forSpace1 = new Pane();
        forSpace1.setPrefSize(10, 5);
        Pane forSpace2 = new Pane();
        forSpace2.setPrefSize(30, 5);
        topHbox.getChildren().addAll(nrOfFramesLabel, nrOfFramesTextField, forSpace1,
                inputStringLabel, inputStringTextField, inputStringButton,
                forSpace2, generateButton, clearAllButton);
        Pane forSpace = new Pane();
        forSpace.setPrefSize(10, 5);
        topVbox.getChildren().addAll(topPaneScrollPane, topHbox, forSpace);

        root.setTop(topVbox);
        root.setCenter(imageFlowPane);
        root.getStyleClass().add("paneBackgrond");
        Scene scene = new Scene(root, 1000, 650);

        primaryStage.setTitle("Page Replacement Algorithms - Eno Çuka");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }

    private void createFramesField() {
        nrOfFramesLabel = new Label("Nr Of Frames");
        nrOfFramesLabel.getStyleClass().add("textFilter");
        nrOfFramesTextField = new TextField();
        nrOfFramesTextField.setPrefWidth(60);
        nrOfFramesTextField.getStyleClass().add("textFilter");
        nrOfFramesTextField.setTooltip(tooltip);
        nrOfFramesTextField.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    inputStringTextField.requestFocus();
                }
            }
        });

    }

    private void createInputStringField() {
        inputStringLabel = new Label("Input String");
        inputStringLabel.getStyleClass().add("textFilter");
        inputStringTextField = new TextField();
        inputStringTextField.setPrefWidth(60);
        inputStringTextField.getStyleClass().add("textFilter");
        inputStringButton = new Button("Add");
        inputStringButton.getStyleClass().add("buttonGreen");
        inputStringTextField.setTooltip(tooltip);
        inputStringTextField.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    addInputInSequence();
                }

            }
        });
        inputStringButton.setTooltip(tooltip);
        inputStringButton.setOnAction(e -> {
            addInputInSequence();
        });
        inputStringButton.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    addInputInSequence();
                }
            }
        });

    }

    private void addInputInSequence() {
        if (!inputStringTextField.getText().isEmpty()) {
            int input = 0;
            try {
                input = Integer.parseInt(inputStringTextField.getText());
                if (input < 0) {
                    popUpMessage("You must enter a positive integer.", null);
                    return;
                }
                Button button = new Button("" + input);
                button.getStyleClass().add("buttonSequence");
                Tooltip tooltip = new Tooltip("Click to remove");
                tooltip.getStyleClass().add("buttonSequence");
                button.setTooltip(tooltip);
                button.setOnAction(e -> {

                    removeSequence(button);
                });
                sequenceHbox.getChildren().add(button);
                referenceStringButton.add(button);
                inputStringTextField.setText("");
                inputStringTextField.requestFocus();

            } catch (Exception e) {
                popUpMessage("It is not an integer.", "You must enter a positive integer.");
                System.out.println("NoIntegerNr");
            }
        }

    }

    private void removeSequence(Button button) {
        System.out.println(referenceStringButton);
        referenceStringButton.remove(button);
        System.out.println(referenceStringButton);
        sequenceHbox.getChildren().remove(button);
    }

    private void generateButton() {
        generateButton = new Button("Generate");
        generateButton.getStyleClass().add("buttonBlue");
        generateButton.setOnAction(e -> {
            generate();
        });
        generateButton.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    generate();
                }
            }
        });
    }

    private void generate() {
        ArrayList<Integer> referenceString = new ArrayList<>();
        for (Button button : referenceStringButton) {
            referenceString.add(Integer.parseInt(button.getText()));
        }
        if (referenceString.isEmpty()) {
            popUpMessage("Sequence is empty.", "You must add sequence.");
            return;
        }
        try {
            int nrOfF = Integer.parseInt(nrOfFramesTextField.getText());
            if (nrOfF < 1) {
                popUpMessage("Number of frames  must be greater than zero", null);
                return;
            }
            generateButton.setVisible(false);
            nrOfFramesTextField.setEditable(false);
            inputStringTextField.setEditable(false);
            inputStringButton.setDisable(true);
            clearAllButton.setVisible(true);
            algorithmGUI = new AlgorithmGUI(referenceString, nrOfF);
            root.setCenter(algorithmGUI);
        } catch (Exception ex) {
            popUpMessage("Number of frames is not an integer.", "You must enter a positive integer.");
            System.out.println("NoIntegerNrOfFrames");
        }
    }

    private void clearAllButton() {
        clearAllButton = new Button("Clear All");
        clearAllButton.getStyleClass().add("buttonGreen");
        clearAllButton.setVisible(false);
        clearAllButton.setOnAction(e -> {
            clearAll();
        });
        clearAllButton.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    clearAll();
                }
            }
        });
    }

    private void clearAll() {
        root.setCenter(imageFlowPane);
        generateButton.setVisible(true);
        nrOfFramesTextField.setEditable(true);
        nrOfFramesTextField.setText("");
        inputStringTextField.setText("");
        inputStringTextField.setEditable(true);
        inputStringButton.setDisable(false);
        sequenceHbox.getChildren().clear();
        referenceStringButton.clear();
        clearAllButton.setVisible(false);
    }

    private void popUpMessage(String textHeader, String contentString) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Waring");
        alert.setHeaderText(textHeader);
        alert.setContentText(contentString);
        alert.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
