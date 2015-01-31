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

import com.ait.lienzo.client.core.shape.json.IFactory;
import com.ait.lienzo.client.core.shape.json.validators.ValidationContext;
import com.ait.lienzo.client.core.shape.json.validators.ValidationException;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo3d.client.Attribute3D;
import com.google.gwt.json.client.JSONObject;

public class Camera extends BaseObject3D<Camera>
{
    private final Point3D m_location = new Point3D(0, 0, 0);

    private final Point3D m_rotation = new Point3D(0.5 * Math.PI, 0, 0);

    public Camera()
    {
        super(Type3D.CAMERA);

        refresh();
    }

    protected Camera(final JSONObject node, final ValidationContext ctx) throws ValidationException
    {
        super(Type3D.CAMERA, node, ctx);

        refresh();
    }

    public Camera setCameraArmLength(double length)
    {
        getAttributes().setCameraArmLength(length);

        return refresh();
    }

    public double getCameraArmLength()
    {
        return getAttributes().getCameraArmLength();
    }

    public Camera setCameraArmLocation(Point3D location)
    {
        getAttributes().setCameraArmLocation(location);

        return refresh();
    }

    public Point3D getCameraArmLocation()
    {
        return getAttributes().getCameraArmLocation();
    }

    public Camera setCameraArmRotation(CameraArmRotation rotation)
    {
        getAttributes().setCameraArmRotation(rotation);

        return refresh();
    }

    public CameraArmRotation getCameraArmRotation()
    {
        return getAttributes().getCameraArmRotation();
    }

    public final Camera refresh()
    {
        final double length = getCameraArmLength();

        final Point3D location = getCameraArmLocation();

        final CameraArmRotation rotation = getCameraArmRotation();

        final double h = rotation.getH();

        final double v = rotation.getV();

        m_location.setX(location.getX() - length * Math.sin(h) * Math.cos(v));

        m_location.setY(location.getY() - length * Math.cos(h) * Math.cos(v));

        m_location.setZ(location.getZ() + length * Math.sin(v));

        m_rotation.setX(Math.PI / 2 - v);

        m_rotation.setY(0);

        m_rotation.setZ(0 - h);

        return this;
    }

    public final Point3D getCameraLocation()
    {
        return m_location;
    }

    public final Point3D getCameraRotation()
    {
        return m_rotation;
    }

    public final Point3D projectToTranslation(final Point3D point, final IViewable3D<?> viewable)
    {
        final Point3D scale = viewable.getViewScale();

        final double ax = point.getX() * scale.getX();

        final double ay = point.getY() * scale.getY();

        final double az = point.getZ() * scale.getZ();

        final double lx = m_location.getX();

        final double ly = m_location.getY();

        final double lz = m_location.getZ();

        final double rx = m_rotation.getX();

        final double ry = m_rotation.getY();

        final double rz = m_rotation.getZ();

        final double sx = Math.sin(rx);

        final double cx = Math.cos(rx);

        final double sy = Math.sin(ry);

        final double cy = Math.cos(ry);

        final double sz = Math.sin(rz);

        final double cz = Math.cos(rz);

        final double dx = cy * (sz * (ay - ly) + cz * (ax - lx)) - sy * (az - lz);

        final double dy = sx * (cy * (az - lz) + sy * (sz * (ay - ly) + cz * (ax - lx))) + cx * (cz * (ay - ly) - sz * (ax - lx));

        final double dz = cx * (cy * (az - lz) + sy * (sz * (ay - ly) + cz * (ax - lx))) - sx * (cz * (ay - ly) - sz * (ax - lx));

        return new Point3D(dx, dy, dz);
    }

    public final Point2D projectToScreen(final Point3D point, final IViewable3D<?> viewable, final double x, final double y, final double w, final double h, final boolean perspective)
    {
        final Point3D focal = viewable.getViewPosition();

        final double fx = focal.getX();

        final double fy = focal.getY();

        final double fz = focal.getZ();

        final double px = point.getX();

        final double py = point.getY();

        final double pz = point.getZ();

        double bx;

        double by;

        if (perspective)
        {
            bx = (px - fx) * (fz / pz);

            by = (py - fy) * (fz / pz);
        }
        else
        {
            final double fc = -(fz / getCameraArmLength());

            bx = px * fc;

            by = py * fc;
        }
        return new Point2D(x + bx * w, y - by * h);
    }

    public final Point2D projectTo2D(final Point3D point, final IViewable3D<?> viewable, final double x, final double y, final double w, final double h, final boolean perspective)
    {
        return projectToScreen(projectToTranslation(point, viewable), viewable, x, y, w, y, perspective);
    }

    @Override
    public IFactory<Camera> getFactory()
    {
        return new CameraFactory();
    }

    public static class CameraFactory extends BaseObject3DFactory<Camera>
    {
        public CameraFactory()
        {
            super(Type3D.CAMERA);

            addAttribute(Attribute3D.CAMERA_ARM_LENGTH);

            addAttribute(Attribute3D.CAMERA_ARM_LOCATION);

            addAttribute(Attribute3D.CAMERA_ARM_ROTATION);
        }

        @Override
        public Camera create(final JSONObject node, final ValidationContext ctx) throws ValidationException
        {
            return new Camera(node, ctx);
        }
    }
}
