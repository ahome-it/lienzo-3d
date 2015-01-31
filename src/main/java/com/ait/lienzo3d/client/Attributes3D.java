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
import com.ait.lienzo3d.client.types.CameraArmRotation;
import com.ait.lienzo3d.client.types.CameraArmRotation.CameraArmRotationJSO;
import com.ait.lienzo3d.client.types.Point3D;
import com.ait.lienzo3d.client.types.Point3D.Point3DJSO;
import com.google.gwt.core.client.JavaScriptObject;

public class Attributes3D extends Attributes
{
    public Attributes3D()
    {
    }

    public Attributes3D(JavaScriptObject ajso)
    {
        super(ajso);
    }

    public final void setZ(double z)
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

    public final void setCameraArmLocation(Point3D location)
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
        JavaScriptObject location = getObject(Attribute3D.CAMERA_ARM_LOCATION.getProperty());

        if (null != location)
        {
            Point3DJSO pjso = location.cast();

            return new Point3D(pjso);
        }
        return new Point3D();
    }

    public final void setCameraArmRotation(CameraArmRotation rotation)
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
        JavaScriptObject rotation = getObject(Attribute3D.CAMERA_ARM_ROTATION.getProperty());

        if (null != rotation)
        {
            CameraArmRotationJSO pjso = rotation.cast();

            return new CameraArmRotation(pjso);
        }
        return new CameraArmRotation();
    }
}
