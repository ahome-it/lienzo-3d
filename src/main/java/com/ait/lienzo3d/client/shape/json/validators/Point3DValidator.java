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

package com.ait.lienzo3d.client.shape.json.validators;

import com.ait.lienzo.client.core.shape.json.validators.NumberValidator;
import com.ait.lienzo.client.core.shape.json.validators.ObjectValidator;

public class Point3DValidator extends ObjectValidator
{
    public static final Point3DValidator INSTANCE = new Point3DValidator();

    public Point3DValidator()
    {
        super("Point3D");

        addAttribute("x", NumberValidator.INSTANCE, true);

        addAttribute("y", NumberValidator.INSTANCE, true);

        addAttribute("z", NumberValidator.INSTANCE, true);
    }
}
