/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jaggeryjs.scriptengine.engine;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.jaggeryjs.scriptengine.exceptions.ScriptException;

import java.lang.reflect.Method;

public class JavaScriptMethod {

    private String name = null;
    private Class clazz = null;
    private String methodName = null;
    private Method method = null;
    private int attribute = ScriptableObject.PERMANENT;

    public JavaScriptMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Method getMethod() throws ScriptException {
        if (method != null) {
            return method;
        }

        try {
            method = clazz.getDeclaredMethod(
                    name, Context.class, Scriptable.class, Object[].class, Function.class);
            return method;
        } catch (NoSuchMethodException e) {
            throw new ScriptException(e);
        }
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }
}
