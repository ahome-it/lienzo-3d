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

import java.util.Collection;

import com.ait.lienzo.client.core.Attribute;
import com.ait.lienzo.client.core.shape.json.AbstractFactory;
import com.ait.lienzo.client.core.shape.json.IJSONSerializable;
import com.ait.lienzo.client.core.shape.json.JSONDeserializer;
import com.ait.lienzo.client.core.shape.json.validators.ValidationContext;
import com.ait.lienzo.client.core.shape.json.validators.ValidationException;
import com.ait.lienzo.client.core.types.MetaData;
import com.ait.lienzo.client.core.types.NFastStringMapMixedJSO;
import com.ait.lienzo.client.core.util.UUID;
import com.ait.lienzo.shared.core.types.NodeType;
import com.ait.lienzo3d.client.Attributes3D;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public abstract class BaseObject3D<T extends BaseObject3D<T>> implements IJSONSerializable<T>
{
    private final Type3D       m_type;

    private final Attributes3D m_attr;

    private final MetaData     m_meta;

    private String             m_uuid = null;

    protected BaseObject3D(Type3D type)
    {
        m_type = type;

        m_meta = new MetaData();

        m_attr = new Attributes3D();
    }

    protected BaseObject3D(final Type3D type, final JSONObject node, final ValidationContext ctx) throws ValidationException
    {
        m_type = type;

        if (null == node)
        {
            m_attr = new Attributes3D();

            m_meta = new MetaData();

            return;
        }
        JSONValue aval = node.get("attributes");

        if (null == aval)
        {
            m_attr = new Attributes3D();
        }
        else
        {
            JSONObject aobj = aval.isObject();

            if (null == aobj)
            {
                m_attr = new Attributes3D();
            }
            else
            {
                JavaScriptObject ajso = aobj.getJavaScriptObject();

                if (null == ajso)
                {
                    m_attr = new Attributes3D();
                }
                else
                {
                    m_attr = new Attributes3D(ajso);
                }
            }
        }
        JSONValue mval = node.get("meta");

        if (null == mval)
        {
            m_meta = new MetaData();
        }
        else
        {
            JSONObject mobj = mval.isObject();

            if (null == mobj)
            {
                m_meta = new MetaData();
            }
            else
            {
                JavaScriptObject mjso = mobj.getJavaScriptObject();

                if (null == mjso)
                {
                    m_meta = new MetaData();
                }
                else
                {
                    NFastStringMapMixedJSO jso = mjso.cast();

                    m_meta = new MetaData(jso);
                }
            }
        }
    }

    public T setName(final String name)
    {
        m_attr.setName(name);

        return cast();
    }

    public String getName()
    {
        return m_attr.getName();
    }

    public T setID(final String id)
    {
        m_attr.setID(id);

        return cast();
    }

    public String getID()
    {
        return m_attr.getID();
    }

    @SuppressWarnings("unchecked")
    protected final <M> M cast()
    {
        return (M) this;
    }

    public T copy()
    {
        final BaseObject3D<?> base = copyUnchecked();

        if (null == base)
        {
            return null;
        }
        if (getType3D() != base.getType3D())
        {
            return null;
        }
        return base.cast();
    }

    protected BaseObject3D<?> copyUnchecked()
    {
        return (BaseObject3D<?>) JSONDeserializer.getInstance().fromString(toJSONString(), false); // don't validate
    }

    protected String uuid()
    {
        if (null == m_uuid)
        {
            m_uuid = UUID.uuid();
        }
        return m_uuid;
    }

    public Type3D getType3D()
    {
        return m_type;
    }

    @Override
    public JSONObject toJSONObject()
    {
        final JSONObject object = new JSONObject();

        object.put("type", new JSONString(getType3D().getValue()));

        if (false == getMetaData().isEmpty())
        {
            object.put("meta", new JSONObject(getMetaData().getJSO()));
        }
        object.put("attributes", new JSONObject(getAttributes().getJSO()));

        return object;
    }

    @Override
    public String toJSONString()
    {
        JSONObject object = toJSONObject();

        if (null != object)
        {
            return object.toString();
        }
        return null;
    }

    public final MetaData getMetaData()
    {
        return m_meta;
    }

    @Override
    public String toString()
    {
        return toJSONString();
    }

    @Override
    public final int hashCode()
    {
        return uuid().hashCode();
    }

    @Override
    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (null == obj)
        {
            return false;
        }
        if (false == (obj instanceof BaseObject3D))
        {
            return false;
        }
        BaseObject3D<?> that = ((BaseObject3D<?>) obj);

        return uuid().equals(that.uuid());
    }

    public final Attributes3D getAttributes()
    {
        return m_attr;
    }

    public Collection<Attribute> getAttributeSheet()
    {
        return getFactory().getAttributeSheet();
    }

    public Collection<Attribute> getRequiredAttributes()
    {
        return getFactory().getRequiredAttributes();
    }

    protected static abstract class Base3DFactory<T extends IJSONSerializable<T>> extends AbstractFactory<T>
    {
        protected Base3DFactory(final Type3D type)
        {
            this(type.getValue());
        }

        protected Base3DFactory(final String typeName)
        {
            super(typeName);

            addAttribute(Attribute.ID);

            addAttribute(Attribute.NAME);
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
