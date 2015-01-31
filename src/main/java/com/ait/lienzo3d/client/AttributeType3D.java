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

import com.ait.lienzo.client.core.AttributeType;
import com.ait.lienzo.client.core.shape.json.validators.ArrayValidator;
import com.ait.lienzo.client.core.shape.json.validators.IAttributeTypeValidator;
import com.ait.lienzo3d.client.shape.json.validators.CameraArmRotationValidator;
import com.ait.lienzo3d.client.shape.json.validators.Point3DValidator;

public class AttributeType3D extends AttributeType
{
    public static AttributeType POINT3D_TYPE             = new AttributeType3D(Point3DValidator.INSTANCE);

    public static AttributeType POINT3D_ARRAY_TYPE       = new AttributeType3D(new ArrayValidator(Point3DValidator.INSTANCE));

    public static AttributeType CAMERA_ARM_ROTATION_TYPE = new AttributeType3D(CameraArmRotationValidator.INSTANCE);

    protected AttributeType3D(IAttributeTypeValidator validator)
    {
        super(validator);
    }
}
