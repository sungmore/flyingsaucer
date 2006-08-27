/*
 * {{{ header & license
 * Copyright (c) 2005 Wisconsin Court System
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * }}}
 */
package org.xhtmlrenderer.render;

import java.awt.Point;

import org.xhtmlrenderer.layout.FloatManager;
import org.xhtmlrenderer.layout.Layer;
import org.xhtmlrenderer.layout.LayoutContext;

public class FloatedBlockBox extends BlockBox {
    private Layer drawingLayer;
    private FloatManager manager;
    private int marginFromPrevious;
    
    public FloatedBlockBox() {
    }

    public String toString() {
        return super.toString() + " (floated)";
    }

    public Layer getDrawingLayer() {
        return drawingLayer;
    }

    public void setDrawingLayer(Layer drawingLayer) {
        this.drawingLayer = drawingLayer;
    }
    
    public void reset(LayoutContext c) {
        super.reset(c);
        manager.removeFloat(this);
        drawingLayer.removeFloat(this);
    }
    
    public void calcCanvasLocation() {
        Point offset = manager.getOffset(this);
        setAbsX(manager.getMaster().getAbsX() + this.x - offset.x);
        setAbsY(manager.getMaster().getAbsY() + this.y - offset.y);
        super.calcCanvasLocation();
    }
    
    public void calcInitialCanvasLocation(LayoutContext c) {
        Point offset = c.getBlockFormattingContext().getOffset();
        FloatManager manager = c.getBlockFormattingContext().getFloatManager();
        setAbsX(manager.getMaster().getAbsX() + this.x - offset.x);
        setAbsY(manager.getMaster().getAbsY() + this.y - offset.y);
    }

    public FloatManager getManager() {
        return manager;
    }

    public void setManager(FloatManager manager) {
        this.manager = manager;
    }
    
    public void setAbsY(int y) {
        super.setAbsY(y);
    }

    public int getMarginFromPrevious() {
        return marginFromPrevious;
    }

    public void setMarginFromPrevious(int marginFromPrevious) {
        this.marginFromPrevious = marginFromPrevious;
    }
}
