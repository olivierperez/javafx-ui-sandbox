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

import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Andrea
 */
class DialogResources
{
    // Localization strings.
    private static final ResourceBundle dialogsResourceBundle = ResourceBundle.getBundle( "com.sun.javafx.scene.control.skin.resources.dialog-resources" );

    /**
     * Method to get an internationalized string from the deployment resource.
     */
    static String getMessage(String key) {
        try {
            return dialogsResourceBundle.getString( key );
        } catch ( MissingResourceException ex ) {
            // Do not trace this exception, because the key could be
            // an already translated string.
            System.out.println( "Failed to get string for key '" + key + "'" );
            return key;
        }
    }

    /**
     * Returns a string from the resources
     */
    static String getString(String key) {
        try {
            return dialogsResourceBundle.getString( key );
        } catch ( MissingResourceException mre ) {
            // Do not trace this exception, because the key could be
            // an already translated string.
            System.out.println( "Failed to get string for key '" + key + "'" );
            return key;
        }
    }

    /**
     * Returns a string from a resource, substituting argument 1
     */
    static String getString(String key, Object... args) {
        return MessageFormat.format( getString( key ), args );
    }

    /**
     * Returns an <code>ImageView</code> given an image file name or resource name
     */
    public static ImageView getIcon(final String key) {
        try {
            return AccessController.doPrivileged( new PrivilegedExceptionAction<ImageView>() {
                @Override
                public ImageView run() {
                    String resourceName = getString( key );
                    URL url = DialogResources.class.getResource( resourceName );
                    if ( url == null ) {
                        System.out.println( "Can't create ImageView for key '" + key + "', which has resource name '" + resourceName + "' and URL 'null'" );
                        return null;
                    }
                    return getIcon( url );
                }
            } );
        } catch ( Exception ex ) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ImageView getIcon(URL url) {
        return new ImageView( new Image( url.toString() ) );
    }

}
