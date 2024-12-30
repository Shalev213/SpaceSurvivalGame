package org.example;

import org.opencv.core.Core;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        System.load("C:\\Users\\USER\\IdeaProjects\\MultiplayerGame2\\lib\\opencv_java4100.dll");
        System.out.println("OpenCV version: " + Core.VERSION);


        Window window = new Window();
        window.showWindow();
    }
}