/*
 * Copyright (c) 2015 Zack Rauen
 * Website: www.ZackRauen.com
 *
 * All rights reserved. Use is subject to license terms.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * If a copy of the License is not provided with the work, you may
 * obtain a copy at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Utilities for use with JavaFX. This contains several functions
 * and function wrappers that make using JavaFX slightly easier
 * than it already is. It is mainly things that I use and find
 * useful however I have and will continue to take suggestions
 * from other people.<br>
 * The class allows for easy-to-make alerts and error dialogs
 * with easy to use wrappers if you only want to use certain
 * pieces of the alert.
 * @author Zack Rauen
 * @version 1.1
 */
public final class FXUtils {

	/**
	 * Creates the error dialog for the given error.
	 *
	 * @param e the exception to display.
	 */
	public static void createErrorDialog(Exception e) {
		FXUtils.createErrorDialog(null, null, null, null, e);
	}

	/**
	 * Creates the error dialog for the given error with a title.
	 *
	 * @param title the title to display
	 * @param e the exception to display
	 */
	public static void createErrorDialog(String title, Exception e) {
		FXUtils.createErrorDialog(title, null, null, null, e);
	}

	/**
	 * Creates the error dialog for the given error with a title
	 * and a custom heading.
	 *
	 * @param title the title to display
	 * @param heading the heading to be displayed
	 * @param e the exception to display
	 */
	public static void createErrorDialog(String title, String heading, Exception e) {
		FXUtils.createErrorDialog(title, heading, null, null, e);
	}

	/**
	 * Creates the error dialog for the given error with a title
	 * and a custom heading as well as description.
	 *
	 * @param title the title to display
	 * @param heading the heading to be displayed
	 * @param description the description to present
	 * @param e the exception to use
	 */
	public static void createErrorDialog(String title, String heading, String description, Exception e) {
		FXUtils.createErrorDialog(title, heading, description, null, e);
	}

	/**
	 * Creates the error dialog for the given error with a title
	 * and a custom heading as well as description.
	 * 
	 * A null in place of one of the parameters will cause the
	 * default for that section to appear, aside from the exception.
	 * 
	 * By default this window is created with a width of 600 and also
	 * centers itself on screen.
	 *
	 * @param title the title to display
	 * @param heading the heading to be displayed
	 * @param description the description to present
	 * @param expansionLabel the label for expanded content
	 * @param e the exception to use
	 */
	public static void createErrorDialog(String title, String heading, String description, String expansionLabel, Exception e) {
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
		alert.setTitle(title==null ? "Exception Dialog" : title);
		alert.setHeaderText(heading==null ? e.getClass().getSimpleName() : heading);
		alert.setContentText(description==null ? "Something went wrong... Check below for more details." : description);
		alert.getDialogPane().setPrefWidth(600);
		java.io.StringWriter sw = new java.io.StringWriter();
		java.io.PrintWriter pw = new java.io.PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();
		javafx.scene.control.Label label = new javafx.scene.control.Label(expansionLabel==null ? "Error:" : expansionLabel);
		javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		javafx.scene.layout.GridPane.setVgrow(textArea, javafx.scene.layout.Priority.ALWAYS);
		javafx.scene.layout.GridPane.setHgrow(textArea, javafx.scene.layout.Priority.ALWAYS);
		javafx.scene.layout.GridPane expContent = new javafx.scene.layout.GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		label.setPadding(new javafx.geometry.Insets(25,0,0,3));
		expContent.setVgap(10);
		expContent.add(textArea, 0, 1);
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}
	
	/**
	 * Show basic alert.
	 *
	 * @param content the content to show
	 */
	public static void showAlert(String content) {
		FXUtils.showAlert(null, null, content);
	}
	
	/**
	 * Show basic alert with title.
	 *
	 * @param title the title to use
	 * @param content the content to show
	 */
	public static void showAlert(String title, String content) {
		FXUtils.showAlert(title, null, content);
	}
	
	/**
	 * Show alert with title and header.
	 *
	 * @param title the title to use
	 * @param header the header to display
	 * @param content the content to show
	 */
	public static void showAlert(String title, String header, String content) {
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
		alert.setTitle(title==null ? "Alert" : title);
		alert.setHeaderText(header==null ? "Alert Dialog" : header);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
