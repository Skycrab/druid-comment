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

package org.apache.druid.query;

import com.google.inject.Inject;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 */
public class MapQueryToolChestWarehouse implements QueryToolChestWarehouse
{
  private final Map<Class<? extends Query>, QueryToolChest> toolchests;

  @Inject
  public MapQueryToolChestWarehouse(Map<Class<? extends Query>, QueryToolChest> toolchests)
  {
    // Accesses to IdentityHashMap should be faster than to HashMap or ImmutableMap.
    // Class doesn't override Object.equals().
    /**
     * IdentityHashMap只有key==key才相同，可以认为对象地址相同
     */
    this.toolchests = new IdentityHashMap<>(toolchests);
  }

  /**
   * 根据查询类型获取查询处理器，如TOPN -> {@link org.apache.druid.query.topn.TopNQueryQueryToolChest}
   *
   * 具体注入的QueryToolChest Map见 {@link org.apache.druid.guice.QueryToolChestModule}
   *
   * @param query
   * @param <T>
   * @param <QueryType>
   * @return
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T, QueryType extends Query<T>> QueryToolChest<T, QueryType> getToolChest(QueryType query)
  {
    return toolchests.get(query.getClass());
  }
}
