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

import javafx.scene.image.ImageView;

public enum DialogType
{
    INFORMATION( DialogOptions.OK, "info48.image" ) {
        @Override public String getDefaultMasthead() { return "Message"; }
    },

    CONFIRMATION( DialogOptions.YES_NO_CANCEL, "confirm48.image" ) {
        @Override public String getDefaultMasthead() { return "Select an Option"; }
    },

    WARNING( DialogOptions.OK, "warning48.image" ) {
        @Override public String getDefaultMasthead() { return "Warning"; }
    },

    ERROR( DialogOptions.OK, "error48.image" ) {
        @Override public String getDefaultMasthead() { return "Error"; }
    },

    INPUT( DialogOptions.OK_CANCEL, "confirm48.image" ) {
        @Override public String getDefaultMasthead() { return "Select an Option"; }
    },

    CUSTOM( DialogOptions.OK, "info48.image" ) {
        @Override public String getDefaultMasthead() { return "Message"; }
    };

    private final DialogOptions defaultOptions;
    private final String imageResource;

    DialogType(DialogOptions defaultOptions, String imageResource) {
        this.defaultOptions = defaultOptions;
        this.imageResource = imageResource;
    }

    public ImageView getImage() {
        return DialogResources.getIcon( imageResource );
    }

    public String getDefaultTitle() {
        return getDefaultMasthead();
    }

    public abstract String getDefaultMasthead();

    public DialogOptions getDefaultOptions() {
        return defaultOptions;
    }

}
