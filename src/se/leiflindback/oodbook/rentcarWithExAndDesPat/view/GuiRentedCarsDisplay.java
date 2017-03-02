/*
 * Copyright (c) 2016, Leif Lindbäck
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package se.leiflindback.oodbook.rentcarWithExAndDesPat.view;

import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;

/**
 * Shows a GUI with the number of rented cars.
 */
public class GuiRentedCarsDisplay extends RentedCarsDisplay {
    private Display display;

    /**
     * Starts the GUI.
     */
    public GuiRentedCarsDisplay() {
        new Thread(() -> {
            Application.launch(Display.class, null);
        }).start();
        this.display = Display.getDisplay();
    }

    /**
     * Shows a GUI with the number of rented cars of each type.
     */
    @Override
    protected void printCurrentState(Map<CarDTO.CarType, Integer> noOfRentedCars) {
        display.updateStatsList(noOfRentedCars);
    }

    // ----------------- Code below here is for GUI handling only. -------------------------------
    public static class Display extends Application {
        private static Display display;
        private ObservableList<String> rentalStats = FXCollections.observableArrayList();

        @Override
        public void start(Stage stage) throws Exception {
            FlowPane rootNode = new FlowPane(10, 10);

            rootNode.getChildren().add(new ListView<>(rentalStats));
            Scene scene = new Scene(rootNode, 200, 75);

            stage.setTitle("Rented Cars");
            stage.setScene(scene);
            stage.show();

            setDisplay(this);
        }

        private void updateStatsList(Map<CarDTO.CarType, Integer> noOfRentedCars) {
            deleteAllStats();
            for (CarDTO.CarType type : CarDTO.CarType.values()) {
                rentalStats.add(type.toString().toLowerCase() + ": " + noOfRentedCars.get(type));
            }
        }

        private void deleteAllStats() {
            rentalStats.remove(0, rentalStats.size() - 1);
        }

        private void setDisplay(Display display) {
            synchronized (Display.class) {
                this.display = display;
                Display.class.notifyAll();
            }
        }

        private static Display getDisplay() {
            synchronized (Display.class) {
                while (display == null) {
                    try {
                        Display.class.wait();
                    } catch (InterruptedException ignore) {
                    }
                }
                Display lastCreatedDisplay = display;
                display = null;
                return lastCreatedDisplay;
            }
        }
    }
}
