/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.catalina.loader;

import org.apache.catalina.LifecycleException;


/**
 * 打破了双亲委托机制，它首先自己尝试去加载某个类，如果找不到再代理给父类加载器，其目的是优先加载 Web 应用自己定义的类。
 * 具体实现就是重写 ClassLoader 的两个方法：findClass 和 loadClass
 *
 *  Tomcat 为每个 Web 应用创建一个 WebAppClassLoarder 类加载器，并在启动 Web 应用的线程里设置线程上下文加载器
 */
public class WebappClassLoader extends WebappClassLoaderBase {

    public WebappClassLoader() {
        super();
    }


    public WebappClassLoader(ClassLoader parent) {
        super(parent);
    }


    /**
     * Returns a copy of this class loader without any class file
     * transformers. This is a tool often used by Java Persistence API
     * providers to inspect entity classes in the absence of any
     * instrumentation, something that can't be guaranteed within the
     * context of a {@link java.lang.instrument.ClassFileTransformer}'s
     * {@link java.lang.instrument.ClassFileTransformer#transform(ClassLoader,
     * String, Class, java.security.ProtectionDomain, byte[]) transform} method.
     * <p>
     * The returned class loader's resource cache will have been cleared
     * so that classes already instrumented will not be retained or
     * returned.
     *
     * @return the transformer-free copy of this class loader.
     */
    @Override
    public WebappClassLoader copyWithoutTransformers() {

        WebappClassLoader result = new WebappClassLoader(getParent());

        super.copyStateWithoutTransformers(result);

        try {
            result.start();
        } catch (LifecycleException e) {
            throw new IllegalStateException(e);
        }

        return result;
    }


    /**
     * This class loader is not parallel capable so lock on the class loader
     * rather than a per-class lock.
     */
    @Override
    protected Object getClassLoadingLock(String className) {
        return this;
    }
}
