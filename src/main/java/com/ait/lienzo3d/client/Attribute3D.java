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

import com.ait.lienzo.client.core.Attribute;
import com.ait.lienzo.client.core.AttributeType;

public class Attribute3D extends Attribute
{
    public static final Attribute Z                   = new Attribute3D("z", "", "", AttributeType3D.NUMBER_TYPE);

    public static final Attribute VIEW_SCALE          = new Attribute3D("viewScale", "", "", AttributeType3D.POINT3D_TYPE);

    public static final Attribute VIEW_SCALE_VALUE    = new Attribute3D("viewScaleValue", "", "", AttributeType3D.NUMBER_TYPE);

    public static final Attribute VIEW_POSITION       = new Attribute3D("viewPosition", "", "", AttributeType3D.POINT3D_TYPE);

    public static final Attribute CAMERA_ARM_LENGTH   = new Attribute3D("cameraArmLength", "", "", AttributeType3D.NUMBER_TYPE);

    public static final Attribute CAMERA_ARM_LOCATION = new Attribute3D("cameraArmLocation", "", "", AttributeType3D.POINT3D_TYPE);

    public static final Attribute CAMERA_ARM_ROTATION = new Attribute3D("cameraArmRotation", "", "", AttributeType3D.CAMERA_ARM_ROTATION_TYPE);

    protected Attribute3D(String property, String label, String description, AttributeType type)
    {
        super(property, label, description, type);
    }
}
