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
import com.ait.lienzo3d.client.Attribute3D;
import com.google.gwt.json.client.JSONObject;

public class Camera extends BaseObject3D<Camera>
{
    public Camera()
    {
        super(Type3D.CAMERA);
    }

    protected Camera(final JSONObject node, final ValidationContext ctx) throws ValidationException
    {
        super(Type3D.CAMERA, node, ctx);
    }

    @Override
    public IFactory<Camera> getFactory()
    {
        return new CameraFactory();
    }

    public static class CameraFactory extends Base3DFactory<Camera>
    {
        public CameraFactory()
        {
            super(Type3D.CAMERA);

            addAttribute(Attribute3D.CAMERA_ARM_LENGTH, true);

            addAttribute(Attribute3D.CAMERA_ARM_LOCATION, true);

            addAttribute(Attribute3D.CAMERA_ARM_ROTATION, true);
        }

        @Override
        public Camera create(final JSONObject node, final ValidationContext ctx) throws ValidationException
        {
            return new Camera(node, ctx);
        }
    }
}
