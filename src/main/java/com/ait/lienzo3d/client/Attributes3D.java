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

package com.ait.lienzo3d.client;

import com.ait.lienzo.client.core.shape.Attributes;
import com.ait.lienzo.client.core.shape.json.IJSONSerializable;
import com.ait.lienzo3d.client.types.CameraArmRotation;
import com.ait.lienzo3d.client.types.CameraArmRotation.CameraArmRotationJSO;
import com.ait.lienzo3d.client.types.Point3D;
import com.ait.lienzo3d.client.types.Point3D.Point3DJSO;
import com.google.gwt.core.client.JavaScriptObject;

public class Attributes3D extends Attributes
{
    public Attributes3D(final IJSONSerializable<?> ser)
    {
        super(ser);
    }

    public Attributes3D(final JavaScriptObject jso, final IJSONSerializable<?> ser)
    {
        super(jso, ser);
    }

    public final void setZ(final double z)
    {
        put(Attribute3D.Z.getProperty(), z);
    }

    public final double getZ()
    {
        return getDouble(Attribute3D.Z.getProperty());
    }

    public final void setCameraArmLength(double length)
    {
        if (length < 0.71)
        {
            length = 0.71;
        }
        else if (length > 5.0)
        {
            length = 5.0;
        }
        put(Attribute3D.CAMERA_ARM_LENGTH.getProperty(), length);
    }

    public final double getCameraArmLength()
    {
        double length = getDouble(Attribute3D.CAMERA_ARM_LENGTH.getProperty());

        if (length < 0.71)
        {
            length = 0.71;
        }
        else if (length > 5.0)
        {
            length = 5.0;
        }
        return length;
    }

    public final void setCameraArmLocation(final Point3D location)
    {
        if (null != location)
        {
            put(Attribute3D.CAMERA_ARM_LOCATION.getProperty(), location.getJSO());
        }
        else
        {
            delete(Attribute3D.CAMERA_ARM_LOCATION.getProperty());
        }
    }

    public final Point3D getCameraArmLocation()
    {
        final JavaScriptObject location = getObject(Attribute3D.CAMERA_ARM_LOCATION.getProperty());

        if (null != location)
        {
            final Point3DJSO pjso = location.cast();

            return new Point3D(pjso);
        }
        return new Point3D();
    }

    public final void setCameraArmRotation(final CameraArmRotation rotation)
    {
        if (null != rotation)
        {
            put(Attribute3D.CAMERA_ARM_ROTATION.getProperty(), rotation.getJSO());
        }
        else
        {
            delete(Attribute3D.CAMERA_ARM_ROTATION.getProperty());
        }
    }

    public final CameraArmRotation getCameraArmRotation()
    {
        final JavaScriptObject rotation = getObject(Attribute3D.CAMERA_ARM_ROTATION.getProperty());

        if (null != rotation)
        {
            final CameraArmRotationJSO pjso = rotation.cast();

            return new CameraArmRotation(pjso);
        }
        return new CameraArmRotation();
    }

    public final void setViewScale(final Point3D scale)
    {
        if (null != scale)
        {
            put(Attribute3D.VIEW_SCALE.getProperty(), scale.getJSO());
        }
        else
        {
            delete(Attribute3D.VIEW_SCALE.getProperty());
        }
    }

    public final Point3D getViewScale()
    {
        final JavaScriptObject scale = getObject(Attribute3D.VIEW_SCALE.getProperty());

        if (null != scale)
        {
            final Point3DJSO pjso = scale.cast();

            return new Point3D(pjso);
        }
        return new Point3D(1, 1, 1);
    }

    public final void setViewPosition(final Point3D position)
    {
        if (null != position)
        {
            put(Attribute3D.VIEW_POSITION.getProperty(), position.getJSO());
        }
        else
        {
            delete(Attribute3D.VIEW_POSITION.getProperty());
        }
    }

    public final Point3D getViewPosition()
    {
        final JavaScriptObject position = getObject(Attribute3D.VIEW_POSITION.getProperty());

        if (null != position)
        {
            final Point3DJSO pjso = position.cast();

            return new Point3D(pjso);
        }
        return new Point3D(0, 0, 0 - 1);
    }

    public final void setViewScaleValue(final double value)
    {
        put(Attribute3D.VIEW_SCALE_VALUE.getProperty(), value);
    }

    public final double getViewScaleValue()
    {
        return getDouble(Attribute3D.VIEW_SCALE_VALUE.getProperty());
    }
}
