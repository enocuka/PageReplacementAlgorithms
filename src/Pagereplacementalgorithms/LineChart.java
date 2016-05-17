/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pagereplacementalgorithms;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.*;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
/**
 *
 * @author enocu
 */
public class LineChart {
    public static BarChart getLineChart(ArrayList<String>algorithms,ArrayList<Integer>pageFaults){
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Algorithm");
        yAxis.setLabel("Page Faults");
       final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
       XYChart.Series series = new XYChart.Series();
       for(int i = 0; i <algorithms.size(); i++){
           series.getData().add(new XYChart.Data(algorithms.get(i), pageFaults.get(i)));
       }
       bc.getData().addAll(series);
       return bc;
    }
}
