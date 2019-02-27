package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import static sample.PitchConstants.*;
import java.util.Random;

public class Main extends Application  {

    private final int windowWidth = 1000;
    private final int windowHeight = 800;


    Stage window;
    Scene mainMenu, gameWindow;
    Button button;

    private int players = 2;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
       window = primaryStage;
        initializeMenuScene();

        Button fourPlayers = new Button("4 Players");


       //set title and show window
       window.setTitle("Pitch Game");
       window.setScene(mainMenu);
       window.show();
    }


    void setPlayerCount(int n){
        this.players = n;
        currentPlayerCountSelection.setText("Current Selection: " + Integer.toString(n) + " Players");
    }


    Label currentPlayerCountSelection;
    void initializeMenuScene() {
        //labels
        Label Greeting = new Label("Welcome to the Pitch Game!");
        Greeting.setTextFill(textColor);
        Label Prompt = new Label("Choose a number of players to play against!");
        Prompt.setTextFill(textColor);
        currentPlayerCountSelection = new Label("Current Selection: 2 Players");
        currentPlayerCountSelection.setTextFill(textColor);


        //set label styles
        Greeting.setStyle("-fx-font: 24 arial");

        //buttons
        Button twoPlayers = new Button("2 Players");
        Button threePlayers = new Button("3 Players");
        Button fourPlayers = new Button("4 Players");
        Button exitButton = new Button("Exit Application");
        Button startButton = new Button("Start Application");

        //initialize borderPane
        BorderPane layout = new BorderPane();



        //intialize vbox for menuitems
        VBox menuItems = new VBox(25);
        menuItems.setAlignment(Pos.CENTER);


        //animating
        CardView displayCard = new CardView(null,1,'S',false);



        //create animation timer for rotating card on menu screen
        AnimationTimer timer = new AnimationTimer() {
            int rotator = 0;
            @Override
            public void handle(long now) {
                rotator += 1;
                displayCard.rotate(rotator);
                if(rotator == 360) rotator = 0;
            }
        };

        //start animator
        timer.start();


        //add elements to vbox
        menuItems.getChildren().addAll(Greeting,Prompt,currentPlayerCountSelection,displayCard.View(),twoPlayers,threePlayers,fourPlayers);

        //layout start and exit buttons next to eachother
        HBox startAndExit = new HBox(5);
        startAndExit.getChildren().addAll(startButton,exitButton);
        startAndExit.setAlignment(Pos.CENTER);

        menuItems.getChildren().add(startAndExit);





        //set center object as menuItems
        layout.setCenter(menuItems);





        //event handlers for buttons

        //player count event handlers
        twoPlayers.setOnAction(e -> setPlayerCount(2));
        threePlayers.setOnAction(e -> setPlayerCount(3));
        fourPlayers.setOnAction(e -> setPlayerCount(4));

        //start
        startButton.setOnAction(e -> {
            //instantiate an instance of Pitch
            Pitch game = new Pitch(window,players,windowWidth,windowHeight);

            //stop rotating card animation
            timer.stop();

            //start pitch game
            game.start();


        });
        //exit
        exitButton.setOnAction(e -> exitProgram());

        layout.setStyle(sideBarStyle);

        mainMenu = new Scene(layout,windowWidth,windowHeight);
    }



    void exitProgram() {
        window.close();
    }



}