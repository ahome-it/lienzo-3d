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

import com.ait.lienzo.client.core.util.GeometryException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public final class Point3D
{
    private final Point3DJSO m_jso;

    public Point3D(Point3DJSO jso)
    {
        m_jso = jso;
    }

    public Point3D()
    {
        this(Point3DJSO.make(0, 0, 0));
    }

    public Point3D(double x, double y, double z)
    {
        this(Point3DJSO.make(x, y, z));
    }

    /**
     * Returns the x coordinate
     * @return double
     */
    public final double getX()
    {
        return m_jso.getX();
    }

    /**
     * Sets the x coordinate
     * @param x double
     * @return this Point3D
     */
    public final Point3D setX(double x)
    {
        m_jso.setX(x);

        return this;
    }

    /**
     * Returns the y coordinate
     * @return double
     */
    public final double getY()
    {
        return m_jso.getY();
    }

    /**
     * Sets the y coordinate
     * @param y double
     * @return this Point3D
     */
    public final Point3D setY(double y)
    {
        m_jso.setY(y);

        return this;
    }

    /**
     * Returns the z coordinate
     * @return double
     */
    public final double getZ()
    {
        return m_jso.getZ();
    }

    /**
     * Sets the z coordinate
     * @param z double
     * @return this Point3D
     */
    public final Point3D setZ(double z)
    {
        m_jso.setZ(z);

        return this;
    }

    public final Point3D set(Point3D p)
    {
        m_jso.setX(p.getX());

        m_jso.setY(p.getY());

        m_jso.setZ(p.getZ());

        return this;
    }

    public final double getLength()
    {
        return Point3DJSO.length(m_jso);
    };
    
    public final double distance(Point3D other)
    {
        return Point3DJSO.distance(m_jso, other.getJSO());
    };

    public final Point3D add(Point3D p)
    {
        return new Point3D(Point3DJSO.add(m_jso, p.getJSO()));
    }

    public final Point3D sub(Point3D p)
    {
        return new Point3D(Point3DJSO.sub(m_jso, p.getJSO()));
    }

    public final Point3D div(double d) throws GeometryException
    {
        if (d == 0.0)
        {
            throw new GeometryException("can't divide by 0");
        }
        return mul(1.0 / d);
    }

    public final Point3D mul(double d)
    {
        return scale(d);
    }

    public final Point3D scale(double d)
    {
        return new Point3D(Point3DJSO.scale(m_jso, d));
    }

    public final Point3D unit() throws GeometryException
    {
        double len = getLength();

        if (len == 0)
        {
            throw new GeometryException("can't normalize (0,0,0)");
        }
        return div(len);
    }

    public final double thetaTo(Point3D p)
    {
        return Point3DJSO.thetaTo(m_jso, p.getJSO());
    }

    public final Point3D copy()
    {
        return new Point3D(m_jso.copy());
    }

    public final double dot(Point3D p)
    {
        return Point3DJSO.dot(m_jso, p.getJSO());
    }

    public final Point3D cross(Point3D p)
    {
        return new Point3D(Point3DJSO.cross(m_jso, p.getJSO()));
    }
    
    public final Point3D avg(Point3D p)
    {
        return new Point3D(Point3DJSO.avg(m_jso, p.getJSO()));
    }

    @Override
    public final String toString()
    {
        return new JSONObject(getJSO()).toString();
    }

    public final Point3DJSO getJSO()
    {
        return m_jso;
    }

    public static final class Point3DJSO extends JavaScriptObject
    {
        protected Point3DJSO()
        {
        }

        public static final native Point3DJSO make(double xval, double yval, double zval)
        /*-{
			return {
				x : xval,
				y : yval,
				z : zval
			};
        }-*/;

        private final native Point3DJSO copy()
        /*-{
			return {
				x : this.x,
				y : this.y,
				z : this.z
			};
        }-*/;

        private final native double getX()
        /*-{
			return this.x;
        }-*/;

        private final native void setX(double x)
        /*-{
			this.x = x;
        }-*/;

        private final native double getY()
        /*-{
			return this.y;
        }-*/;

        private final native void setY(double y)
        /*-{
			this.y = y;
        }-*/;

        private final native double getZ()
        /*-{
			return this.z;
        }-*/;

        private final native void setZ(double z)
        /*-{
			this.z = z;
        }-*/;

        private static final native double length(Point3DJSO a)
        /*-{
			var dx = a.x;

			var dy = a.y;

			var dz = a.z;

			return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
        }-*/;

        private static final native double distance(Point3DJSO a, Point3DJSO b)
        /*-{
			var dx = a.x - b.x;

			var dy = a.y - b.y;

			var dz = a.z - b.z;

			return Math.sqrt((dx * dx) + (dy * dy) * (dz * dz));
        }-*/;

        private static final native double dot(Point3DJSO a, Point3DJSO b)
        /*-{
			return a.x * b.x + a.y * b.y + a.z * b.z;
        }-*/;

        private static final native double thetaTo(Point3DJSO a, Point3DJSO b)
        /*-{
			var dt = a.x * b.x + a.y * b.y + a.z * b.z;

			var dx = a.y * b.z - a.z * b.y;

			var dy = a.z * b.x - a.x * b.z;

			var dz = a.x * b.y - a.y * b.x;

			return Math.atan2(Math.sqrt((dx * dx) + (dy * dy) + (dz * dz)), dt);
        }-*/;

        private static final native Point3DJSO add(Point3DJSO a, Point3DJSO b)
        /*-{
			return {
				x : a.x + b.x,
				y : a.y + b.y,
				z : a.z + b.z
			};
        }-*/;

        private static final native Point3DJSO sub(Point3DJSO a, Point3DJSO b)
        /*-{
			return {
				x : a.x - b.x,
				y : a.y - b.y,
				z : a.z - b.z
			};
        }-*/;

        private static final native Point3DJSO scale(Point3DJSO a, double d)
        /*-{
			return {
				x : a.x * d,
				y : a.y * d,
				z : a.z * d
			};
        }-*/;

        private static final native Point3DJSO avg(Point3DJSO a, Point3DJSO b)
        /*-{
			return {
				x : ((a.x + b.x) / 2),
				y : ((a.y + b.y) / 2),
				z : ((a.z + b.z) / 2)
			};
        }-*/;

        private static final native Point3DJSO cross(Point3DJSO a, Point3DJSO b)
        /*-{
			return {
				x : (a.y * b.z - a.z * b.y),
				y : (a.z * b.x - a.x * b.z),
				z : (a.x * b.y - a.y * b.x)
			};
        }-*/;
    }
}
