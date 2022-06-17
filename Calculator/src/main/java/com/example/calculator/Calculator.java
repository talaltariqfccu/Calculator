package com.example.calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Calculator extends Application{

    boolean start=true;
    boolean flag=false;
    String value1="";
    String value2="";
    long temp;
    String op;
    TextField text = new TextField();

    @Override
    public void start(Stage primarystage) throws IOException {

        // Setting text feild for calculator display
        text.setAlignment(Pos.CENTER_RIGHT);
        text.setFont(Font.font("Arial",20));
        text.setEditable(false);
        text.setPrefHeight(10);
        text.setMaxWidth(200);

        StackPane stack = new StackPane();
        stack.getChildren().add(text);


        // Tile pain for creating button layout
        TilePane tile = new TilePane();
        tile.setHgap(10);
        tile.setVgap(10);
        tile.setAlignment(Pos.CENTER);
        tile.getChildren().addAll(
                creatButton_num("7"),
                creatButton_num("8"),
                creatButton_num("9"),
                creatButton_char("/"),

                creatButton_num("4"),
                creatButton_num("5"),
                creatButton_num("6"),
                creatButton_char("x"),

                creatButton_num("1"),
                creatButton_num("2"),
                creatButton_num("3"),
                creatButton_char("-"),

                creatButton_clr("clr"),
                creatButton_num("0"),
                creatButton_clr("="),
                creatButton_char("+")
        );
        tile.setHgap(3);
        tile.setMinSize(20,20);


        BorderPane border = new BorderPane();
        border.setTop(stack);
        border.setCenter(tile);

        Scene scene = new Scene(border,250,290);

        primarystage.setScene(scene);
        primarystage.setResizable(false);
        primarystage.setTitle("My Calculator");
        primarystage.show();
        Button B = new Button("Hello");



    }
//      This function creats the button for number in the calculator
    //______________________________________________________________________________________________________________________
    public Button creatButton_num(String a){
        Button button = new Button(a);
        button.setPrefSize(50,50);
        button.setFont(Font.font(20));


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(start) {
                    text.setText("");

                    if (flag == false) {
                        value1 = value1 + button.getText();
                        text.setText(value1);
                    }
                    if (flag == true) {
                        value2 = value2 + button.getText();
                        text.setText(value2);
                    }
                }
                else{
                    value1=String.valueOf(temp);
                    text.setText(button.getText());
                    value2=value2+button.getText();
                }

            }
        });


        return button;
    }
//     This function creats the button for opertors in the calculator
    //_______________________________________________________________________________________________________________________________________________
    public Button creatButton_char(String a){
        Button button = new Button(a);
        button.setPrefSize(50,50);
        button.setFont(Font.font(20));

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                op=button.getText();
                text.setText(op);
                flag=true;
            }
        });

        return button;
    }

    // This function creats the buttons for equal operator and for restarting the calculator
    //_______________________________________________________________________________________________________________________________________________________
    public Button creatButton_clr(String a){
        Button button = new Button(a);
        button.setPrefSize(50,50);
        button.setFont(Font.font(20));

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(button.getText()=="clr") {
                    start = true;
                    text.setText("");
                    value1 = "";
                    value2 = "";
                    flag=false;
                    temp = 0;
                }

                else{
                    text.setText(String.valueOf(calculate(value1,value2)));
                    text.setText(String.valueOf(temp));
                    start=false;
                }
            }
        });

        return button;
    }

//   this function calculates the operation
    //________________________________________________________________________________________________________________________________________________________
    public long calculate(String a,String b){

        long A=(long) Double.parseDouble(a);
        long B=(long) Double.parseDouble(b);

        switch (op){
            case "/":
                temp=A/B;
                break;

            case "x":
                temp=A*B;
                break;

            case "+":
                temp=A+B;
                break;

            case "-":
                temp=A-B;
                break;

        }
        return temp;
    }


    public static void main(String[] args) {

        launch();//function that evokes the start start function

    }
}
