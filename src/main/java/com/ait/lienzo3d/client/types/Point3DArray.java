/*
   Copyright (c) 2014 Ahome' Innovation Technologies. All rights reserved.

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import com.ait.lienzo3d.client.types.Point3D.Point3DJSO;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONArray;

/**
 * Point3DArray represents an array (or List) with {@link Point3D} objects.
 */
public final class Point3DArray implements Iterable<Point3D>
{
    private final Point3DArrayJSO m_jso;

    Point3DArray(Point3DArrayJSO jso)
    {
        m_jso = jso;
    }

    public Point3DArray(JsArray<JavaScriptObject> jso)
    {
        m_jso = jso.cast();
    }

    public Point3DArray()
    {
        this(Point3DArrayJSO.make());
    }

    public Point3DArray(double x, double y, double z)
    {
        this(Point3DArrayJSO.make());

        push(x, y, z);
    }

    public Point3DArray(Point3D point)
    {
        this(Point3DArrayJSO.make());

        push(point);
    }

    public Point3DArray(Point3D point, Point3D... points)
    {
        this(Point3DArrayJSO.make());

        push(point, points);
    }

    public Point3DArray(double[] x, double[] y, double[] z)
    {
        this(Point3DArrayJSO.make());

        if (x.length != y.length)
        {
            throw new IllegalArgumentException("x and y array should have the same length");
        }
        if (x.length != z.length)
        {
            throw new IllegalArgumentException("x and z array should have the same length");
        }
        for (int i = 0; i < x.length; i++)
        {
            push(x[i], y[i], z[i]);
        }
    }

    public Point3DArray(double[][] points)
    {
        this(Point3DArrayJSO.make());

        for (int i = 0; i < points.length; i++)
        {
            double[] xyz = points[i];

            if (xyz.length != 3)
            {
                throw new IllegalArgumentException("points[" + i + "] does not have length of 3");
            }
            push(xyz[0], xyz[1], xyz[2]);
        }
    }

    public final Point3DArray push(Point3D point)
    {
        m_jso.push(point.getJSO());

        return this;
    }

    public final Point3DArray push(double x, double y, double z)
    {
        m_jso.push(Point3DJSO.make(x, y, z));

        return this;
    }

    public final Point3DArray push(Point3D point, Point3D... points)
    {
        m_jso.push(point.getJSO());

        if (points != null)
        {
            for (int i = 0; i < points.length; i++)
            {
                m_jso.push(points[i].getJSO());
            }
        }
        return this;
    }

    public final int size()
    {
        return m_jso.length();
    }

    public final Point3D get(int i)
    {
        return new Point3D(m_jso.get(i));
    }

    public final Point3DArray set(int i, Point3D p)
    {
        m_jso.set(i, p.getJSO());

        return this;
    }

    public Point3DArray shift()
    {
        m_jso.shift();

        return this;
    }

    public Point3DArray pop()
    {
        m_jso.pop();

        return this;
    }

    public final Collection<Point3D> getPoints()
    {
        int leng = size();

        ArrayList<Point3D> list = new ArrayList<Point3D>(leng);

        for (int i = 0; i < leng; i++)
        {
            list.add(get(i));
        }
        return Collections.unmodifiableCollection(list);
    }

    @Override
    public String toString()
    {
        return new JSONArray(getJSO()).toString();
    }

    @Override
    public Iterator<Point3D> iterator()
    {
        return getPoints().iterator();
    }

    public final Point3DArrayJSO getJSO()
    {
        return m_jso;
    }

    public static final class Point3DArrayJSO extends JsArray<Point3DJSO>
    {
        protected Point3DArrayJSO()
        {
        }

        public final native void pop()
        /*-{
			this.pop();
        }-*/;

        public static final Point3DArrayJSO make()
        {
            return JsArray.createArray().cast();
        }
    }
}
