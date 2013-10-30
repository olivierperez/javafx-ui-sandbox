/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package javafx.scene.control;


import static javafx.scene.control.DialogResources.getIcon;
import static javafx.scene.control.DialogResources.getMessage;
import static javafx.scene.control.DialogResources.getString;
import static javafx.scene.control.DialogResponse.CLOSED;
import static javafx.scene.control.DialogResponse.OK;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

import com.sun.javafx.css.StyleManager;

/**
 * A class containing a number of pre-built JavaFX modal dialogs.
 * <p>
 * Note: This is a copy of the official OpenJFX UI Sandbox Control revision rt-9e5ef340d95f.
 * Changes are marked and described in the readme file.
 *
 * @author OpenJFX Authors
 * @author Marco Jakob (http://edu.makery.ch)
 */
public class Dialogs {


    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    private Dialogs() {
        // no-op as we don't want people creating instances of this class
    }



    /***************************************************************************
     *                                                                         *
     * Confirmation Dialogs                                                    *
     *                                                                         *
     **************************************************************************/

    /**
     * Brings up a dialog with the options Yes, No and Cancel; with the title,
     * <b>Select an Option</b>.
     *
     * @param owner
     * @param message
     * @return
     */
    public static DialogResponse showConfirmDialog(final Stage owner, final String message) {
        return showConfirmDialog(owner,
                                    message,
                                    DialogType.CONFIRMATION.getDefaultMasthead());
    }

    public static DialogResponse showConfirmDialog(final Stage owner, final String message,
                                    final String masthead) {
        return showConfirmDialog(owner,
                                    message,
                                    masthead,
                                    DialogType.CONFIRMATION.getDefaultTitle());
    }

    public static DialogResponse showConfirmDialog(final Stage owner, final String message,
                                    final String masthead, final String title) {
        return showConfirmDialog(owner,
                                    message,
                                    masthead,
                                    title,
                                    DialogType.CONFIRMATION.getDefaultOptions());
    }

    public static DialogResponse showConfirmDialog(final Stage owner, final String message,
                                    final String masthead, final String title, final DialogOptions options) {
        return showSimpleContentDialog(owner,
                                    title,
                                    masthead,
                                    message,
                                    DialogType.CONFIRMATION,
                                    options);
    }



    /***************************************************************************
     *                                                                         *
     * Information Dialogs                                                     *
     *                                                                         *
     **************************************************************************/

    public static void showInformationDialog(final Stage owner,
                                             final String message) {
        showInformationDialog(owner,
                                    message,
                                    DialogType.INFORMATION.getDefaultMasthead());
    }

    public static void showInformationDialog(final Stage owner, final String message,
                                             final String masthead){
        showInformationDialog(owner,
                                    message,
                                    masthead,
                                    DialogType.INFORMATION.getDefaultTitle());
    }

    /*
     * Info message string displayed in the masthead
     * Info icon 48x48 displayed in the masthead
     * "OK" button at the bottom.
     *
     * text and title strings are already translated strings.
     */
    public static void showInformationDialog(final Stage owner, final String message,
                                             final String masthead, final String title){
        showSimpleContentDialog(owner,
                                    title,
                                    masthead,
                                    message,
                                    DialogType.INFORMATION,
                                    DialogType.INFORMATION.getDefaultOptions());
    }



    /***************************************************************************
     *                                                                         *
     * Warning Dialogs                                                         *
     *                                                                         *
     **************************************************************************/

    /**
     * showWarningDialog - displays warning icon instead of "Java" logo icon
     *                     in the upper right corner of masthead.  Has masthead
     *                     and message that is displayed in the middle part
     *                     of the dialog.  No bullet is displayed.
     *
     *
     * @param  owner           - Component to parent the dialog to
     * @param  appInfo         - AppInfo object
     * @param  masthead        - masthead in the top part of the dialog
     * @param  message         - question to display in the middle part
     * @param  title           - dialog title string from resource bundle
     *
     */
    public static DialogResponse showWarningDialog(final Stage owner, final String message) {
        return showWarningDialog(owner,
                                message,
                                DialogType.WARNING.getDefaultMasthead());
    }

    public static DialogResponse showWarningDialog(final Stage owner, final String message,
                                        final String masthead) {
        return showWarningDialog(owner,
                                message,
                                masthead,
                                DialogType.WARNING.getDefaultTitle());
    }

    public static DialogResponse showWarningDialog(final Stage owner, final String message,
                                        final String masthead, final String title) {
        return showWarningDialog(owner,
                                message,
                                masthead,
                                title,
                                DialogType.WARNING.getDefaultOptions());
    }

    public static DialogResponse showWarningDialog(final Stage owner, final String message,
                                        final String masthead, final String title,
                                        DialogOptions options) {
        return showSimpleContentDialog(owner,
                                title,
                                masthead,
                                message,
                                DialogType.WARNING,
                                options);
    }



    /***************************************************************************
     *                                                                         *
     * Exception / Error Dialogs                                               *
     *                                                                         *
     **************************************************************************/

    public static DialogResponse showErrorDialog(final Stage owner, final String message) {
        return showErrorDialog(owner,
                                message,
                                DialogType.ERROR.getDefaultMasthead());
    }

    public static DialogResponse showErrorDialog(final Stage owner, final String message,
                                            final String masthead) {
        return showErrorDialog(owner,
                                message,
                                masthead,
                                masthead);
    }

    public static DialogResponse showErrorDialog(final Stage owner, final String message,
                                            final String masthead, final String title) {
        return showErrorDialog(owner,
                                message,
                                masthead,
                                title,
                                DialogType.ERROR.getDefaultOptions());
    }

    public static DialogResponse showErrorDialog(final Stage owner, final String message,
                                            final String masthead, final String title,
                                            DialogOptions options) {
        return showSimpleContentDialog(owner,
                title,
                masthead,
                message,
                DialogType.ERROR,
                options);
    }

    public static DialogResponse showErrorDialog(final Stage owner, final String message,
                                      final String masthead, final String title,
                                      final Throwable throwable) {

        DialogTemplate template = new DialogTemplate(owner, title, masthead, null);
        template.setErrorContent(message, throwable);
        return showDialog(template);
    }



    /***************************************************************************
     *                                                                         *
     * User Input Dialogs                                                      *
     *                                                                         *
     **************************************************************************/

    public static String showInputDialog(final Stage owner, final String message) {
        return showInputDialog(owner, message, "Masthead");
    }

    public static String showInputDialog(final Stage owner, final String message,
                                        final String masthead) {
        return showInputDialog(owner, message, masthead, "Title");
    }

    public static String showInputDialog(final Stage owner, final String message,
                                        final String masthead, final String title) {
        return showInputDialog(owner, message, masthead, title, null);
    }

    public static String showInputDialog(final Stage owner, final String message,
                                        final String masthead, final String title,
                                        final String initialValue) {
        return showInputDialog(owner, message, masthead, title, initialValue, Collections.<String>emptyList());
    }

    public static <T> T showInputDialog(final Stage owner, final String message,
                                        final String masthead, final String title,
                                        final T initialValue, final T... choices) {
        return showInputDialog(owner, message, masthead, title, initialValue, Arrays.asList(choices));
    }

    public static <T> T showInputDialog(final Stage owner, final String message,
                                        final String masthead, final String title,
                                        final T initialValue, final List<T> choices) {
        DialogTemplate<T> template = new DialogTemplate<T>(owner, title, masthead, null);
        template.setInputContent(message, initialValue, choices);
        return showUserInputDialog(template);
    }

    /***************************************************************************
     *                                                                         *
     * Custom Content Dialog                                                   *
     *                                                                         *
     **************************************************************************/

    //Provided Pane is inserted in the content panel. Provided callback is added to buttons' onAction handler.
    public static <T> DialogResponse showCustomDialog(final Stage owner, final Pane customContentPanel, final String masthead, final String title, DialogOptions options, Callback<java.lang.Void, java.lang.Void> callback) {
        DialogTemplate<T> template = new DialogTemplate<T>(owner, customContentPanel, title, masthead, options); //DialogType.CUSTOM.defaultOptions);
        template.setCustomContent(customContentPanel);
        template.setCustomCallback(callback);
        return showCustomDialog(template);
	}

    private static DialogResponse showSimpleContentDialog(final Stage owner,
                                        final String title, final String masthead,
                                        final String message, DialogType dialogType,
                                        final DialogOptions options) {
        DialogTemplate template = new DialogTemplate(owner, title, masthead, options);
        template.setSimpleContent(message, dialogType);
        return showDialog(template);
    }

    private static DialogResponse showDialog(DialogTemplate template) {
        try {
            template.getDialog().centerOnScreen();
            template.show();
            return template.getResponse();
        } catch (Throwable e) {
            return CLOSED;
        }
    }

    private static <T> T showUserInputDialog(DialogTemplate<T> template) {
    	// !CHANGE START! return null if user did not click ok
		template.getDialog().centerOnScreen();
		template.show();
		if (template.getResponse() == OK) {
			return template.getInputResponse();
		} else {
			return null;
		}
		// !CHANGE END!
    }

	private static DialogResponse showCustomDialog(DialogTemplate template) {
		try {
			//template.options = DialogType.CUSTOM.defaultOptions;
			template.getDialog().centerOnScreen();
			template.show();
	        return template.getResponse();
		} catch (Throwable e) {
			return CLOSED;
		}
//		if (template.getResponse() == OK) {
//			return template.getInputResponse();
//		} else {
//			return null;
//		}
	}
}