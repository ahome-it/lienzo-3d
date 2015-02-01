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

package com.ait.lienzo3d.client.shape;

import com.ait.lienzo3d.client.types.Point3D;

public interface IViewable3D<T extends IViewable3D<T>>
{
    public Point3D getViewScale();

    public T setViewScale(Point3D scale);

    public Point3D getViewPosition();

    public T setViewPosition(Point3D position);

    public double getViewScaleValue();

    public T setViewScaleValue(double value);
}
