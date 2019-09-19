/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.druid.initialization;

import com.fasterxml.jackson.databind.Module;
import org.apache.druid.guice.annotations.ExtensionPoint;

import java.util.List;

/**
 * DruidModule是Druid实现扩展的关键
 * 使用ServiceLoader实现扩展，META-INF/services/org.apache.druid.initialization.DruidModule
 */
@ExtensionPoint
public interface DruidModule extends com.google.inject.Module
{
  /**
   * 用来扩展Druid的Jackson ObjectMapper，通过jackson反序列化增加到Druid扩展
   * 参考LocalDataStorageDruidModule，注册了配置文件子类LocalLoadSpec
   *
   * 可参考redis cache扩展RedisDruidModule
   */
  List<? extends Module> getJacksonModules();
}
