//Aaron Gold
//May 1, 2023
//CS280
//Importing required packages
package com.example.cs280_hw_8;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Business_Expenses extends Application {
    private int mealAllowance, parkingAllowance, taxiAllowance, lodgingAllowance;
    private double parkingDifference, taxiDifference, lodgingOwed, mileReimbursement, totalOwed, totalAllowed, totalExpenses, totalSaved;

    @Override
    public void start(Stage primaryStage) {

        //Declaring given values as final values
        final int MEAL = 47, PARKING = 20, LODGING = 195, TAXI = 40;
        final double VEHICLE = 0.42;

        //Creating labels for categories
        Label header = new Label("Enter information about business expenses. If none, put 0: ");
        Label days = new Label("Number of days of the trip: ");
        Label airfare = new Label("Price of airfare, if any: ");
        Label rentalFees = new Label("Car rental fees, if any: ");
        Label miles = new Label("Number of miles driven, if private vehicle used: ");
        Label parkingFees = new Label("Amount of parking fees, if any: ");
        Label taxi = new Label("Amount of taxi charges, if any: ");
        Label regFees = new Label("Conference or seminar registration fees, if any: ");
        Label lodging = new Label("Lodging charges, per night: ");
        Label input = new Label("Input your data");

        //Creating labels for outputs
        Label totalExpensesIncurred = new Label();
        Label totalAllowableExpenses = new Label();
        Label amountOwed = new Label();
        Label amountSaved = new Label();


        //Creating text fields associated with each category
        TextField daysText = new TextField("");
        TextField airfareText = new TextField("");
        TextField rentalFeesText = new TextField("");
        TextField milesText = new TextField("");
        TextField parkingFeesText = new TextField("");
        TextField taxiText = new TextField("");
        TextField regFeesText = new TextField("");
        TextField lodgingText = new TextField("");


        //Creating HBoxes for all text field and label pairs
        HBox daysLine = new HBox(10.0, days, daysText);
        daysLine.setAlignment(Pos.CENTER);
        HBox airfareLine = new HBox(10.0, airfare, airfareText);
        airfareLine.setAlignment(Pos.CENTER);
        HBox rentalFeesLine = new HBox(10.0, rentalFees, rentalFeesText);
        rentalFeesLine.setAlignment(Pos.CENTER);
        HBox milesLine = new HBox(10.0, miles, milesText);
        milesLine.setAlignment(Pos.CENTER);
        HBox parkingFeesLine = new HBox(10.0, parkingFees, parkingFeesText);
        parkingFeesLine.setAlignment(Pos.CENTER);
        HBox taxiLine = new HBox(10.0, taxi, taxiText);
        taxiLine.setAlignment(Pos.CENTER);
        HBox regFeesLine = new HBox(10.0, regFees, regFeesText);
        regFeesLine.setAlignment(Pos.CENTER);
        HBox lodgingLine = new HBox(10.0, lodging, lodgingText);
        lodgingLine.setAlignment(Pos.CENTER);

        //Making a button to activate the calculation
        Button button = new Button("Calculate");

        button.setOnAction((event) -> {

            //Creating a function to print with commas and 2 decimal places
            DecimalFormat df = new DecimalFormat("0,000.00");

            //Getting all inputs from text fields
            int inputDays = Integer.parseInt(daysText.getText());
            double parkingFeesInput = Double.parseDouble(parkingFeesText.getText());
            double taxiInput = Double.parseDouble(taxiText.getText());
            double lodgingInput = Double.parseDouble(lodgingText.getText());
            double milesInput = Double.parseDouble(milesText.getText());
            double airfareInput = Double.parseDouble(airfareText.getText());
            double rentalInput = Double.parseDouble(rentalFeesText.getText());
            double regInput = Double.parseDouble(regFeesText.getText());

            //Calculations
            mealAllowance = (MEAL * inputDays);
            parkingAllowance = (inputDays * PARKING);
            if (parkingFeesInput > parkingAllowance) {
                parkingDifference = (parkingAllowance - parkingFeesInput);
            } else {
                parkingDifference = 0;
            }
            taxiAllowance = (TAXI * inputDays);
            if (taxiInput > taxiAllowance) {
                taxiDifference = (taxiAllowance - taxiInput);
            } else {
                taxiDifference = 0;
            }
            lodgingAllowance = (inputDays * LODGING);
            if (lodgingInput > lodgingAllowance) {
                lodgingOwed = (lodgingAllowance - lodgingInput);
            } else {
                lodgingOwed = 0;
            }
            mileReimbursement = (VEHICLE * milesInput);


            //Clearing the text fields for next use
            daysText.clear();
            parkingFeesText.clear();
            taxiText.clear();
            lodgingText.clear();
            milesText.clear();
            airfareText.clear();
            rentalFeesText.clear();
            regFeesText.clear();

            //Printing the required outputs
            totalExpenses = airfareInput + rentalInput + parkingFeesInput + taxiInput + regInput + lodgingInput;
            totalAllowed = parkingAllowance + taxiAllowance + lodgingAllowance + mileReimbursement + mealAllowance;
            String totalTripExpenses = ("Total expenses incurred: $" + df.format(totalExpenses));
            totalExpensesIncurred.setText(totalTripExpenses);
            String totalTripAllowed = ("Total expenses allowed: $" + df.format(totalAllowed));
            totalAllowableExpenses.setText(totalTripAllowed);

            //Calculations for total expenses owed or saved
            if (totalAllowed > totalExpenses) {
                totalSaved = 0 - ((totalExpenses - totalAllowed) + lodgingOwed + taxiDifference + parkingDifference);
                String totalAmtSaved = ("Amount saved is: $" + df.format(totalSaved));
                amountSaved.setText(totalAmtSaved);
                amountOwed.setText("Amount owed is: $0");
            } else {
                amountSaved.setText("Amount saved is: $0");
                totalOwed = 0 - ((totalAllowed - totalExpenses) + lodgingOwed + taxiDifference + parkingDifference);
                String totalAmtOwed = ("Amount owed is: $" + df.format(totalOwed));
                amountOwed.setText(totalAmtOwed);
            }
        });

        //Putting all HBoxes in one VBox
        VBox vbox = new VBox(10, header, daysLine, airfareLine, rentalFeesLine, milesLine, parkingFeesLine, taxiLine,
                regFeesLine, lodgingLine, input, button, totalExpensesIncurred, totalAllowableExpenses, amountOwed, amountSaved);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setScene(scene);
        //Setting the title of the file and printing the scene
        primaryStage.setTitle("Expenditure Report:");
        primaryStage.show();
    }
}



