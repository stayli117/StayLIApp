/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.reflect;


import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a generic type {@code T}. Java doesn't yet provide a way to
 * represent generic types, so this class does. Forces clients to create a
 * subclass of this class which enables retrieval the type information even at
 * runtime.
 * <p>
 * <p>For example, to create a type literal for {@code List<String>}, you can
 * create an empty anonymous inner class:
 * <p>
 * <p>
 * {@code TypeToken<List<String>> list = new TypeToken<List<String>>() {};}
 * <p>
 * <p>This syntax cannot be used to create type literals that have wildcard
 * parameters, such as {@code Class<?>} or {@code List<? extends CharSequence>}.
 *
 * @author Bob Lee
 * @author Sven Mawson
 * @author Jesse Wilson
 */
public class TypeToken<T, R> {


    public R getType(T t) {

        Class<?> aClass = t.getClass();

        System.out.println("clazz ==>> " + aClass);


//        Type[] typeArguments = parameterizedType.getActualTypeArguments();
//
//        for (Type typeArgument : typeArguments) {
//            System.out.println("clazz ==>> " + typeArgument);
//        }

        return null;

    }
}
