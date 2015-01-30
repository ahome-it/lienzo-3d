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

public class Type3D
{
    public static final Type3D CAMERA = new Type3D("Lienzo3D.Camera");

    private final String       m_value;

    protected Type3D(String value)
    {
        m_value = value;
    }

    @Override
    public final String toString()
    {
        return m_value;
    }

    public final String getValue()
    {
        return m_value;
    }

    @Override
    public boolean equals(Object other)
    {
        if ((other == null) || (false == (other instanceof Type3D)))
        {
            return false;
        }
        if (this == other)
        {
            return true;
        }
        Type3D that = ((Type3D) other);

        return that.getValue().equals(getValue());
    }

    @Override
    public int hashCode()
    {
        return getValue().hashCode();
    }
}
