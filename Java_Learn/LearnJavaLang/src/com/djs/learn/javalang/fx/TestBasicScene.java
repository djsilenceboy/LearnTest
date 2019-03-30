
package com.djs.learn.javalang.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TestBasicScene extends Application
{
	@Override
	public void start(Stage stage) throws Exception{
		Label message = new Label("Hello, world!");
		message.setFont(new Font(100));
		stage.setScene(new Scene(message));
		stage.setTitle("Earth");
		stage.show();
	}

	public static void main(String[] args){
		TestBasicScene testMain = new TestBasicScene();
		try {
			testMain.launch(null);
		} catch (Exception e) {
			System.out.print("Exception = " + e);
		}
	}
}
