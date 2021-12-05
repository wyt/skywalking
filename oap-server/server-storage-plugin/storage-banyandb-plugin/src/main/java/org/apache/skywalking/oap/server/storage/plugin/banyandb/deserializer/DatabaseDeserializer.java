/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.oap.server.storage.plugin.banyandb.deserializer;

import com.google.common.collect.ImmutableList;
import org.apache.skywalking.banyandb.v1.client.RowEntity;
import org.apache.skywalking.banyandb.v1.client.TagAndValue;
import org.apache.skywalking.oap.server.core.analysis.manual.service.ServiceTraffic;
import org.apache.skywalking.oap.server.core.query.type.Database;

import java.util.List;

public class DatabaseDeserializer extends AbstractBanyanDBDeserializer<Database> {
    public DatabaseDeserializer() {
        super(ServiceTraffic.INDEX_NAME,
                ImmutableList.of(ServiceTraffic.NAME, ServiceTraffic.NODE_TYPE));
    }

    @Override
    public Database map(RowEntity row) {
        Database database = new Database();
        final List<TagAndValue<?>> searchable = row.getTagFamilies().get(0);
        database.setId(row.getId());
        database.setName((String) searchable.get(0).getValue());
        return database;
    }
}
