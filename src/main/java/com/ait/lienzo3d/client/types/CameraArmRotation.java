/*
   Copyright (c) 2014,2015 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.lienzo3d.client.types;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public final class CameraArmRotation
{
    private final CameraArmRotationJSO m_jso;

    public CameraArmRotation(CameraArmRotationJSO jso)
    {
        m_jso = jso;
    }

    public CameraArmRotation()
    {
        this(CameraArmRotationJSO.make(0, 0));
    }

    public CameraArmRotation(double h, double v)
    {
        this(CameraArmRotationJSO.make(h, v));
    }

    /**
     * Returns the x coordinate
     * @return double
     */
    public final double getH()
    {
        return m_jso.getH();
    }

    /**
     * Sets the x coordinate
     * @param x double
     * @return this Point3D
     */
    public final CameraArmRotation setH(double h)
    {
        m_jso.setH(h);

        return this;
    }

    /**
     * Returns the y coordinate
     * @return double
     */
    public final double getV()
    {
        return m_jso.getV();
    }

    /**
     * Sets the y coordinate
     * @param y double
     * @return this Point3D
     */
    public final CameraArmRotation setV(double v)
    {
        m_jso.setV(v);

        return this;
    }

    public final CameraArmRotation set(CameraArmRotation p)
    {
        m_jso.setH(p.getH());

        m_jso.setV(p.getV());

        return this;
    }

    @Override
    public final String toString()
    {
        return new JSONObject(getJSO()).toString();
    }

    public final CameraArmRotationJSO getJSO()
    {
        return m_jso;
    }

    public static final class CameraArmRotationJSO extends JavaScriptObject
    {
        protected CameraArmRotationJSO()
        {
        }

        public static final native CameraArmRotationJSO make(double horz, double vert)
        /*-{
			return {
				h : horz,
				v : vert
			};
        }-*/;

        private final native CameraArmRotationJSO copy()
        /*-{
			return {
				h : this.h,
				v : this.v
			};
        }-*/;

        private final native double getH()
        /*-{
			return this.h;
        }-*/;

        private final native void setH(double h)
        /*-{
			this.h = h;
        }-*/;

        private final native double getV()
        /*-{
			return this.v;
        }-*/;

        private final native void setV(double v)
        /*-{
			this.v = v;
        }-*/;
    }
}
