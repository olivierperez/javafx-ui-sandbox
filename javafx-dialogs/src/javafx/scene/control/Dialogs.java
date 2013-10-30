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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import static javafx.scene.control.DialogResponse.CLOSED;
import static javafx.scene.control.DialogResponse.OK;

/**
 * A class containing a number of pre-built JavaFX modal dialogs.
 * <p>
 * Note: This is a copy of the official OpenJFX UI Sandbox Control revision
 * rt-9e5ef340d95f. Changes are marked and described in the readme file.
 *
 * @author OpenJFX Authors
 * @author Marco Jakob (http://edu.makery.ch)
 * @author Andrea Cisternino <a.cisternino@gmail.com>
 */
public class Dialogs
{
    //---- Constructor ------------------------------------------------------------------

    private Dialogs() {
        // no-op as we don't want people creating instances of this class
    }

    //-----------------------------------------------------------------------------------
    //
    // Confirmation Dialogs
    //
    //-----------------------------------------------------------------------------------

    /**
     * Brings up a dialog with the options <i>Yes</i>, <i>No</i> and <i>Cancel</i>;
     * with the title "<i>Select an Option</i>".
     *
     * @param owner Component to parent the dialog to.
     * @param message the main message displayed by the dialog.
     * @return the response provided by the user.
     */
    public static DialogResponse showConfirmDialog(final Stage owner, final String message) {

        return showConfirmDialog( owner, message, DialogType.CONFIRMATION.getDefaultMasthead() );
    }

    /**
     * Brings up a dialog with the options <i>Yes</i>, <i>No</i> and <i>Cancel</i>;
     * with the title "<i>Select an Option</i>".
     *
     * @param owner Component to parent the dialog to.
     * @param message the main message displayed by the dialog.
     * @param masthead the masthead below the title bar.
     * @return the response provided by the user.
     */
    public static DialogResponse showConfirmDialog(final Stage owner, final String message,
            final String masthead) {

        return showConfirmDialog( owner, message, masthead, DialogType.CONFIRMATION.getDefaultTitle() );
    }

    /**
     * Brings up a dialog with the options <i>Yes</i>, <i>No</i> and <i>Cancel</i>;
     * with the provided title.
     *
     * @param owner Component to parent the dialog to.
     * @param message the main message displayed by the dialog.
     * @param masthead the masthead below the title bar.
     * @param title dialog title.
     * @return the response provided by the user.
     */
    public static DialogResponse showConfirmDialog(final Stage owner, final String message,
            final String masthead, final String title) {

        return showConfirmDialog( owner, message, masthead, title,
                DialogType.CONFIRMATION.getDefaultOptions() );
    }

    /**
     * Brings up a dialog with customizable options and the provided title.
     *
     * @param owner Component to parent the dialog to.
     * @param message the main message displayed by the dialog.
     * @param masthead the masthead below the title bar.
     * @param title dialog title.
     * @param options a {@link DialogOptions} instance to customize the buttons of the dialog.
     * @return the response provided by the user.
     */
    public static DialogResponse showConfirmDialog(final Stage owner, final String message,
            final String masthead, final String title, final DialogOptions options) {

        return showSimpleContentDialog( owner, title, masthead, message,
                DialogType.CONFIRMATION, options );
    }

    //-----------------------------------------------------------------------------------
    //
    // Information Dialogs
    //
    //-----------------------------------------------------------------------------------

    /**
     * Brings up a simple information dialog with the <i>Ok</i> option and
     * the title "<i>Message</i>".
     *
     * @param owner Component to parent the dialog to.
     * @param message the main message displayed by the dialog.
     */
    public static void showInformationDialog(final Stage owner, final String message) {

        showInformationDialog( owner, message, DialogType.INFORMATION.getDefaultMasthead() );
    }

    /**
     * Brings up a simple information dialog with the <i>Ok</i> option and
     * the title "<i>Message</i>".
     *
     * @param owner Component to parent the dialog to.
     * @param message the main message displayed by the dialog.
     * @param masthead the masthead below the title bar.
     */
    public static void showInformationDialog(final Stage owner, final String message,
            final String masthead) {

        showInformationDialog( owner, message, masthead, DialogType.INFORMATION.getDefaultTitle() );
    }

    /**
     * Brings up a simple information dialog with the <i>Ok</i> option and
     * the provided title.
     *
     * @param owner Component to parent the dialog to.
     * @param message the main message displayed by the dialog.
     * @param masthead the masthead below the title bar.
     * @param title dialog title.
     */
    public static void showInformationDialog(final Stage owner, final String message,
            final String masthead, final String title) {

        showSimpleContentDialog( owner, title, masthead, message, DialogType.INFORMATION,
                DialogType.INFORMATION.getDefaultOptions() );
    }

    //-----------------------------------------------------------------------------------
    //
    // Warning Dialogs
    //
    //-----------------------------------------------------------------------------------

    /**
     * Displays a Warning dialog.
     *
     * @param owner Component to parent the dialog to.
     * @param message question to display in the middle part.
     * @return the response provided by the user.
     */
    public static DialogResponse showWarningDialog(final Stage owner, final String message) {

        return showWarningDialog( owner, message, DialogType.WARNING.getDefaultMasthead() );
    }

    /**
     * Displays a Warning dialog.
     *
     * @param owner Component to parent the dialog to.
     * @param message question to display in the middle part.
     * @param masthead masthead in the top part of the dialog.
     * @return the response provided by the user.
     */
    public static DialogResponse showWarningDialog(final Stage owner, final String message,
            final String masthead) {

        return showWarningDialog( owner, message, masthead, DialogType.WARNING.getDefaultTitle() );
    }

    /**
     * Displays a Warning dialog.
     *
     * @param owner Component to parent the dialog to.
     * @param message question to display in the middle part.
     * @param masthead masthead in the top part of the dialog.
     * @param title dialog title.
     * @return the response provided by the user.
     */
    public static DialogResponse showWarningDialog(final Stage owner, final String message,
            final String masthead, final String title) {

        return showWarningDialog( owner, message, masthead, title, DialogType.WARNING.getDefaultOptions() );
    }

    /**
     * Displays a Warning dialog.
     *
     * @param owner Component to parent the dialog to.
     * @param message question to display in the middle part.
     * @param masthead masthead in the top part of the dialog.
     * @param title dialog title string from resource bundle.
     * @param options a {@link DialogOptions} instance to customize the buttons of the dialog.
     * @return the response provided by the user.
     */
    public static DialogResponse showWarningDialog(final Stage owner, final String message,
            final String masthead, final String title, DialogOptions options) {

        return showSimpleContentDialog( owner, title, masthead, message, DialogType.WARNING, options );
    }

    //-----------------------------------------------------------------------------------
    //
    // Exception / Error Dialogs
    //
    //-----------------------------------------------------------------------------------

    /**
     * Displays an Error dialog.
     *
     * @param owner Component to parent the dialog to.
     * @param message question to display in the middle part.
     * @return the response provided by the user.
     */
    public static DialogResponse showErrorDialog(final Stage owner, final String message) {

        return showErrorDialog( owner, message, DialogType.ERROR.getDefaultMasthead() );
    }

    /**
     * Displays an Error dialog.
     *
     * @param owner Component to parent the dialog to.
     * @param message question to display in the middle part.
     * @param masthead masthead in the top part of the dialog.
     * @return the response provided by the user.
     */
    public static DialogResponse showErrorDialog(final Stage owner, final String message,
            final String masthead) {

        return showErrorDialog( owner, message, masthead, masthead );
    }

    /**
     * Displays an Error dialog.
     *
     * @param owner Component to parent the dialog to.
     * @param message question to display in the middle part.
     * @param masthead masthead in the top part of the dialog.
     * @param title dialog title string from resource bundle.
     * @return the response provided by the user.
     */
    public static DialogResponse showErrorDialog(final Stage owner, final String message,
            final String masthead, final String title) {

        return showErrorDialog( owner, message, masthead, title, DialogType.ERROR.getDefaultOptions() );
    }

    /**
     * Displays an Error dialog.
     *
     * @param owner Component to parent the dialog to.
     * @param message question to display in the middle part.
     * @param masthead masthead in the top part of the dialog.
     * @param title dialog title string from resource bundle.
     * @param options a {@link DialogOptions} instance to customize the buttons of the dialog.
     * @return the response provided by the user.
     */
    public static DialogResponse showErrorDialog(final Stage owner, final String message,
            final String masthead, final String title, DialogOptions options) {

        return showSimpleContentDialog( owner, title, masthead, message, DialogType.ERROR,
                options );
    }

    /**
     * Displays an Error dialog.
     *
     * @param owner Component to parent the dialog to.
     * @param message question to display in the middle part.
     * @param masthead masthead in the top part of the dialog.
     * @param title dialog title string from resource bundle.
     * @param throwable a Throwable associated with the dialog.
     * @return the response provided by the user.
     */
    public static DialogResponse showErrorDialog(final Stage owner, final String message,
            final String masthead, final String title, final Throwable throwable) {

        DialogTemplate template = new DialogTemplate( owner, title, masthead, null );
        template.setErrorContent( message, throwable );

        return showDialog( template );
    }

    //-----------------------------------------------------------------------------------
    //
    // User Input Dialogs
    //
    //-----------------------------------------------------------------------------------

    public static String showInputDialog(final Stage owner, final String message) {

        return showInputDialog( owner, message, "Masthead" );
    }

    public static String showInputDialog(final Stage owner, final String message, final String masthead) {

        return showInputDialog( owner, message, masthead, "Title" );
    }

    public static String showInputDialog(final Stage owner, final String message, final String masthead,
            final String title) {

        return showInputDialog( owner, message, masthead, title, null );
    }

    public static String showInputDialog(final Stage owner, final String message, final String masthead,
            final String title, final String initialValue) {

        return showInputDialog( owner, message, masthead, title, initialValue, Collections.<String>emptyList() );
    }

    public static <T> T showInputDialog(final Stage owner, final String message, final String masthead,
            final String title, final T initialValue, final T... choices) {

        return showInputDialog( owner, message, masthead, title, initialValue, Arrays.asList( choices ) );
    }

    public static <T> T showInputDialog(final Stage owner, final String message, final String masthead,
            final String title, final T initialValue, final List<T> choices) {

        DialogTemplate<T> template = new DialogTemplate<T>( owner, title, masthead, null );
        template.setInputContent( message, initialValue, choices );

        return showUserInputDialog( template );
    }

    //-----------------------------------------------------------------------------------
    //
    // Custom Content Dialog
    //
    //-----------------------------------------------------------------------------------

    // Provided Pane is inserted in the content panel. Provided callback is added to buttons' onAction handler.
    public static <T> DialogResponse showCustomDialog(final Stage owner, final Pane customContentPanel,
            final String masthead, final String title, DialogOptions options, Callback<Void, Void> callback) {

        DialogTemplate<T> template = new DialogTemplate<T>( owner, customContentPanel, title, masthead, options );
        template.setCustomContent( customContentPanel );
        template.setCustomCallback( callback );

        return showCustomDialog( template );
    }

    //-----------------------------------------------------------------------------------
    //
    // Private API
    //
    // These methods are responsible for the actual creation of the Dialog.
    //-----------------------------------------------------------------------------------

    private static DialogResponse showSimpleContentDialog(final Stage owner, final String title,
            final String masthead, final String message, DialogType dialogType, final DialogOptions options) {

        DialogTemplate template = new DialogTemplate( owner, title, masthead, options );
        template.setSimpleContent( message, dialogType );

        return showDialog( template );
    }

    private static DialogResponse showDialog(DialogTemplate template) {
        try {
            template.getDialog().centerOnScreen();
            template.show();
            return template.getResponse();
        } catch ( Exception ex ) {
            return CLOSED;
        }
    }

    private static <T> T showUserInputDialog(DialogTemplate<T> template) {

        template.getDialog().centerOnScreen();
        template.show();

        if ( template.getResponse() == OK ) {
            return template.getInputResponse();
        } else {
            return null;
        }
    }

    private static DialogResponse showCustomDialog(DialogTemplate template) {

        try {
//            template.options = DialogType.CUSTOM.defaultOptions;
            template.getDialog().centerOnScreen();
            template.show();
            return template.getResponse();
        } catch ( Exception ex ) {
            return CLOSED;
        }
//        if (template.getResponse() == OK) {
//                return template.getInputResponse();
//        } else {
//                return null;
//        }
    }
}
