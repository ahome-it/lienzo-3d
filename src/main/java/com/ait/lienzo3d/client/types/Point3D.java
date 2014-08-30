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
        return m_jso.getLength();
    };

    public final double distance(Point3D other)
    {
        return m_jso.distance(other.getJSO());
    };

    public static final double distance(Point3D a, Point3D b)
    {
        return Point3DJSO.distance(a.getJSO(), b.getJSO());
    };

    public final Point3D add(Point3D p)
    {
        return new Point3D(m_jso.add(p.getJSO()));
    }

    public final Point3D sub(Point3D p)
    {
        return new Point3D(m_jso.sub(p.getJSO()));
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
        return new Point3D(m_jso.scale(d));
    }

    public final Point3D scale(double d)
    {
        return new Point3D(m_jso.scale(d));
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
        return m_jso.thetaTo(p.getJSO());
    }

    public final Point3D copy()
    {
        return new Point3D(m_jso.copy());
    }

    public final double dot(Point3D p)
    {
        return m_jso.dot(p.getJSO());
    }

    public final Point3D cross(Point3D p)
    {
        return new Point3D(m_jso.cross(p.getJSO()));
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

    public static class Point3DJSO extends JavaScriptObject
    {
        protected Point3DJSO()
        {
        }

        public static native Point3DJSO make(double xval, double yval, double zval)
        /*-{
			return {
				x : xval,
				y : yval,
				z : zval
			};
        }-*/;

        public final native static double distance(Point3DJSO a, Point3DJSO b)
        /*-{
			var dx = b.x - a.x;

			var dy = b.y - a.y;

			var dz = b.z - a.z;

			return Math.sqrt((dx * dx) + (dy * dy) * (dz * dz));
        }-*/;

        public final native static double length(Point3DJSO a)
        /*-{
			var dx = a.x;

			var dy = a.y;

			var dz = a.z;

			return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
        }-*/;

        public native Point3DJSO copy()
        /*-{
			return {
				x : this.x,
				y : this.y,
				z : this.z
			};
        }-*/;

        public final native double dot(Point3DJSO p)
        /*-{
			return this.x * p.x + this.y * p.y + this.z * p.z;
        }-*/;

        public final double thetaTo(Point3DJSO p)
        {
            return thetaTo_(p, dot(p));
        }

        private final native double thetaTo_(Point3DJSO p, double dot)
        /*-{
			var dx = this.y * p.z - this.z * p.y;

			var dy = this.z * p.x - this.x * p.z;

			var dz = this.x * p.y - this.y * p.x;

			return Math.atan2(Math.sqrt(dx * dx + dy * dy + dz * dz), dot);
        }-*/;

        private final native Point3DJSO cross(Point3DJSO p)
        /*-{
			return {
				x : this.y * p.z - this.z * p.y,
				y : this.z * p.x - this.x * p.z,
				z : this.x * p.y - this.y * p.x
			};
        }-*/;

        public final native double getX()
        /*-{
			return this.x;
        }-*/;

        public final native void setX(double x)
        /*-{
			this.x = x;
        }-*/;

        public final native double getY()
        /*-{
			return this.y;
        }-*/;

        public final native void setY(double y)
        /*-{
			this.y = y;
        }-*/;

        public final native double getZ()
        /*-{
			return this.z;
        }-*/;

        public final native void setZ(double z)
        /*-{
			this.z = z;
        }-*/;

        public final double distance(Point3DJSO other)
        {
            return distance(this, other);
        };

        public final double getLength()
        {
            return length(this);
        };

        public final native Point3DJSO add(Point3DJSO jso)
        /*-{
			return {
				x : this.x + jso.x,
				y : this.y + jso.y,
				z : this.z + jso.z
			};
        }-*/;

        public final native Point3DJSO sub(Point3DJSO jso)
        /*-{
			return {
				x : this.x - jso.x,
				y : this.y - jso.y,
				z : this.z - jso.z
			};
        }-*/;

        public final native Point3DJSO scale(double d)
        /*-{
			return {
				x : this.x * d,
				y : this.y * d,
				z : this.z * d
			};
        }-*/;
    }
}
