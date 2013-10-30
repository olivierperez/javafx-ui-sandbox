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

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 */
class ExceptionDialog extends FXDialog
{
    public ExceptionDialog(Stage parent, Throwable throwable) {
        super( DialogResources.getMessage( "exception.dialog.title" ) );

        initModality( Modality.APPLICATION_MODAL );

        // --- initComponents
        VBox contentPanel = new VBox();
        contentPanel.getStyleClass().add( "more-info-dialog" );
        contentPanel.setPrefSize( 800, 600 );
        if ( throwable != null ) {
            BorderPane labelPanel = new BorderPane();
            Label label = new Label( DialogResources.getString( "exception.dialog.label" ) );
            labelPanel.setLeft( label );
            contentPanel.getChildren().add( labelPanel );
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter( sw );
            throwable.printStackTrace( pw );
            TextArea text = new TextArea( sw.toString() );
            text.setEditable( false );
            text.setWrapText( true );
            text.setPrefWidth( 60 * 8 );
            text.setPrefHeight( 20 * 12 );
            VBox.setVgrow( text, Priority.ALWAYS );
            contentPanel.getChildren().add( text );
        }

        // --- getBtnPanel
        // This panel contains right-aligned "Close" button.  It should
        // dismiss the dialog and dispose of it.
        HBox btnPanel = new HBox();
        btnPanel.getStyleClass().add( "button-panel" );
        Button dismissBtn = new Button( DialogResources.getMessage( "common.close.btn" ) );
        dismissBtn.setPrefWidth( 80 );
        dismissBtn.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e) {
                hide();
            }
        } );
        dismissBtn.setDefaultButton( true );
        btnPanel.getChildren().add( dismissBtn );
        contentPanel.getChildren().add( btnPanel );

        // --- getBtnPanel
        setContentPane( contentPanel );
        // --- initComponents
    }

}
