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

import com.ait.lienzo.client.core.shape.json.validators.ValidationContext;
import com.ait.lienzo.client.core.shape.json.validators.ValidationException;
import com.ait.lienzo.shared.core.types.NodeType;
import com.ait.lienzo3d.client.Attribute3D;
import com.ait.lienzo3d.client.types.Point3D;
import com.ait.lienzo3d.client.types.Type3D;
import com.google.gwt.json.client.JSONObject;

public abstract class BaseViewable3D<T extends BaseViewable3D<T>> extends BaseObject3D<T> implements IViewable3D<T>
{
    protected BaseViewable3D(final Type3D type)
    {
        super(type);
    }

    protected BaseViewable3D(final Type3D type, final JSONObject node, final ValidationContext ctx) throws ValidationException
    {
        super(type, node, ctx);
    }

    @Override
    public T refresh()
    {
        return super.refresh();
    }

    @Override
    public Point3D getViewScale()
    {
        return getAttributes().getViewScale();
    }

    @Override
    public T setViewScale(final Point3D scale)
    {
        getAttributes().setViewScale(scale);

        return refresh();
    }

    @Override
    public Point3D getViewPosition()
    {
        return getAttributes().getViewPosition();
    }

    @Override
    public T setViewPosition(final Point3D position)
    {
        getAttributes().setViewPosition(position);

        return refresh();
    }

    @Override
    public double getViewScaleValue()
    {
        return getAttributes().getViewScaleValue();
    }

    @Override
    public T setViewScaleValue(final double value)
    {
        getAttributes().setViewScaleValue(value);

        return refresh();
    }

    protected static abstract class BaseViewable3DFactory<T extends BaseViewable3D<T>> extends BaseObject3DFactory<T>
    {
        protected BaseViewable3DFactory(final Type3D type)
        {
            this(type.getValue());
        }

        protected BaseViewable3DFactory(final String typeName)
        {
            super(typeName);

            addAttribute(Attribute3D.VIEW_SCALE);

            addAttribute(Attribute3D.VIEW_SCALE_VALUE);

            addAttribute(Attribute3D.VIEW_POSITION);
        }

        /**
         * Only factories that wish to extend other factories should use this.
         * 
         * @param type {@link NodeType}
         */
        protected void setNodeType(final Type3D type)
        {
            setTypeName(type.getValue());
        }
    }
}
