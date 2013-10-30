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

	// !CHANGE START! use a separate css file
	private static final URL DIALOGS_CSS_URL = FXDialog.class.getResource("dialogs.css");
	// !CHANGE END!



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


    /**
     * Heavyweight dialog implementation
     */
    private static class FXDialog extends Stage {
        private BorderPane root;
        private RootPane decoratedRoot;
        private HBox windowBtns;
        private Button minButton;
        private Button maxButton;
        private Rectangle resizeCorner;
        private double mouseDragOffsetX = 0;
        private double mouseDragOffsetY = 0;
        protected Label titleLabel;

        private static final int HEADER_HEIGHT = 28;

        FXDialog(String title) {
            this(title, null, false);
        }

        FXDialog(String title, Window owner, boolean modal) {
            this(title, owner, modal, StageStyle.TRANSPARENT);
        }

        FXDialog(String title, Window owner, boolean modal, StageStyle stageStyle) {
            super(stageStyle);

            setTitle(title);

            if (owner != null) {
                initOwner(owner);
            }

            if (modal) {
                initModality(Modality.WINDOW_MODAL);
            }

            resizableProperty().addListener(new InvalidationListener() {
                @Override public void invalidated(Observable valueModel) {
                    resizeCorner.setVisible(isResizable());
                    maxButton.setVisible(isResizable());

                    if (isResizable()) {
                        windowBtns.getChildren().add(1, maxButton);
                    } else {
                        windowBtns.getChildren().remove(maxButton);
                    }
                }
            });

            root = new BorderPane();

            Scene scene;
            if (stageStyle == StageStyle.DECORATED) {
                scene = new Scene(root);
                // !CHANGE START!
                scene.getStylesheets().addAll(DIALOGS_CSS_URL.toExternalForm());
                // !CHANGE END!
                setScene(scene);
                return;
            }

            // *** The rest is for adding window decorations ***

            decoratedRoot = new RootPane() {
                @Override protected void layoutChildren() {
                    super.layoutChildren();
                    if (resizeCorner != null) {
                        resizeCorner.relocate(getWidth() - 20, getHeight() - 20);
                    }
                }
            };
            decoratedRoot.getChildren().add(root);
            scene = new Scene(decoratedRoot);
            // !CHANGE START!
            String css = (String) AccessController.doPrivileged(new PrivilegedAction() {
                @Override public Object run() {
                    return DIALOGS_CSS_URL.toExternalForm();
                }
            });
            scene.getStylesheets().addAll(css);
            // !CHANGE END!
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);

            decoratedRoot.getStyleClass().addAll("dialog", "decorated-root");

            focusedProperty().addListener(new InvalidationListener() {
                @Override public void invalidated(Observable valueModel) {
                    decoratedRoot.pseudoClassStateChanged("active");
                }
            });

            ToolBar toolBar = new ToolBar();
            toolBar.getStyleClass().add("window-header");
            toolBar.setPrefHeight(HEADER_HEIGHT);
            toolBar.setMinHeight(HEADER_HEIGHT);
            toolBar.setMaxHeight(HEADER_HEIGHT);

            // add window dragging
            toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    mouseDragOffsetX = event.getSceneX();
                    mouseDragOffsetY = event.getSceneY();
                }
            });
            toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    setX(event.getScreenX() - mouseDragOffsetX);
                    setY(event.getScreenY() - mouseDragOffsetY);
                }
            });

            titleLabel = new Label();
            titleLabel.getStyleClass().add("window-title");
            titleLabel.setText(getTitle());

            titleProperty().addListener(new InvalidationListener() {
                @Override public void invalidated(Observable valueModel) {
                    titleLabel.setText(getTitle());
                }
            });

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            // add close min max
            Button closeButton = createWindowButton("close");
            closeButton.setOnAction(new EventHandler() {
                @Override public void handle(Event event) {
                    FXDialog.this.hide();
                }
            });
            minButton = createWindowButton("minimize");
            minButton.setOnAction(new EventHandler() {
                @Override public void handle(Event event) {
                    setIconified(!isIconified());
                }
            });

            maxButton = createWindowButton("maximize");
            maxButton.setOnAction(new EventHandler() {
                private double restoreX;
                private double restoreY;
                private double restoreW;
                private double restoreH;

                @Override public void handle(Event event) {
                    Screen screen = Screen.getPrimary(); // todo something more sensible
                    double minX = screen.getVisualBounds().getMinX();
                    double minY = screen.getVisualBounds().getMinY();
                    double maxW = screen.getVisualBounds().getWidth();
                    double maxH = screen.getVisualBounds().getHeight();

                    if (restoreW == 0 || getX() != minX || getY() != minY || getWidth() != maxW || getHeight() != maxH) {
                        restoreX = getX();
                        restoreY = getY();
                        restoreW = getWidth();
                        restoreH = getHeight();
                        setX(minX);
                        setY(minY);
                        setWidth(maxW);
                        setHeight(maxH);
                    } else {
                        setX(restoreX);
                        setY(restoreY);
                        setWidth(restoreW);
                        setHeight(restoreH);
                    }
                }
            });

            windowBtns = new HBox(3);
            windowBtns.getStyleClass().add("window-buttons");
            windowBtns.getChildren().addAll(minButton, maxButton, closeButton);

            toolBar.getItems().addAll(titleLabel, spacer, windowBtns);
            root.setTop(toolBar);

            resizeCorner = new Rectangle(10, 10);
            resizeCorner.getStyleClass().add("window-resize-corner");

            // add window resizing
            EventHandler<MouseEvent> resizeHandler = new EventHandler<MouseEvent>() {
                private double width;
                private double height;
                private Point2D dragAnchor;

                @Override public void handle(MouseEvent event) {
                    EventType type = event.getEventType();

                    if (type == MouseEvent.MOUSE_PRESSED) {
                        width = getWidth();
                        height = getHeight();
                        dragAnchor = new Point2D(event.getSceneX(), event.getSceneY());
                    } else if (type == MouseEvent.MOUSE_DRAGGED) {
                        setWidth(Math.max(decoratedRoot.minWidth(-1),   width  + (event.getSceneX() - dragAnchor.getX())));
                        setHeight(Math.max(decoratedRoot.minHeight(-1), height + (event.getSceneY() - dragAnchor.getY())));
                    }
                }
            };
            resizeCorner.setOnMousePressed(resizeHandler);
            resizeCorner.setOnMouseDragged(resizeHandler);

            resizeCorner.setManaged(false);
            decoratedRoot.getChildren().add(resizeCorner);
        }

        void setContentPane(Pane pane) {
            if (pane.getId() == null) {
                pane.getStyleClass().add("content-pane");
            }
            root.setCenter(pane);
        }

//        public void setIconifiable(boolean iconifiable) {
//            minButton.setVisible(iconifiable);
//        }

        private Button createWindowButton(String name) {
            StackPane graphic = new StackPane();
            graphic.getStyleClass().setAll("graphic");

            Button button = new Button();
            button.getStyleClass().setAll("window-button");
            button.getStyleClass().add("window-"+name+"-button");
            button.setGraphic(graphic);
            button.setMinSize(17, 17);
            button.setPrefSize(17, 17);
            return button;
        }



        private static class RootPane extends StackPane {
            /*******************************************************************
             *                                                                 *
             * Stylesheet Handling                                             *
             *                                                                 *
             *******************************************************************/

        	// !CHANGE START!
            private static final long PSEUDO_CLASS_ACTIVE_MASK =
                    StyleManager.getInstance().getPseudoclassMask("active");
            // !CHANGE END!

            @Override public long impl_getPseudoClassState() {
                long mask = super.impl_getPseudoClassState();
                if (getScene().getWindow().isFocused()) {
                    mask |= PSEUDO_CLASS_ACTIVE_MASK;
                }
                return mask;
            }

            private void pseudoClassStateChanged(String pseudoClass) {
                impl_pseudoClassStateChanged(pseudoClass);
            }
        }
    }

    private static class ExceptionDialog extends FXDialog {
        public ExceptionDialog(Stage parent, Throwable throwable) {
            super(getMessage("exception.dialog.title"));

            initModality(Modality.APPLICATION_MODAL);

            // --- initComponents
            VBox contentPanel = new VBox();
            contentPanel.getStyleClass().add("more-info-dialog");

            contentPanel.setPrefSize(800, 600);

            if (throwable != null) {
                BorderPane labelPanel = new BorderPane();

                Label label = new Label(getString("exception.dialog.label"));
                labelPanel.setLeft(label);

                contentPanel.getChildren().add(labelPanel);

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                throwable.printStackTrace(pw);
                TextArea text = new TextArea(sw.toString());
                text.setEditable(false);
                text.setWrapText(true);
                text.setPrefWidth(60 * 8);
                text.setPrefHeight(20 * 12);

                VBox.setVgrow(text, Priority.ALWAYS);
                contentPanel.getChildren().add(text);
            }

            // --- getBtnPanel
            // This panel contains right-aligned "Close" button.  It should
            // dismiss the dialog and dispose of it.
            HBox btnPanel = new HBox();
            btnPanel.getStyleClass().add("button-panel");

            Button dismissBtn = new Button(getMessage("common.close.btn"));
            dismissBtn.setPrefWidth(80);
            dismissBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    hide();
                }
            });

            dismissBtn.setDefaultButton(true);
            btnPanel.getChildren().add(dismissBtn);
            contentPanel.getChildren().add(btnPanel);
            // --- getBtnPanel

            setContentPane(contentPanel);
            // --- initComponents
        }
    }
}