/*
 * Copyright 2010-2013 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.osgi.bundles.analytics.json;

import java.util.List;

import javax.annotation.Nullable;

import org.jooq.Field;
import org.jooq.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public class SchemaJson {

    private final List<FieldJson> fields;

    public SchemaJson(@Nullable final Table table) {
        this(table == null ? ImmutableList.<FieldJson>of()
                           : ImmutableList.<FieldJson>copyOf(Iterables.<Field<?>, FieldJson>transform(ImmutableList.<Field<?>>copyOf(table.recordType().fields()),
                                                                                                      new Function<Field<?>, FieldJson>() {
                                                                                                          @Override
                                                                                                          public FieldJson apply(final Field<?> input) {
                                                                                                              return new FieldJson(input);
                                                                                                          }
                                                                                                      })));
    }

    public SchemaJson(@JsonProperty("fields") final List<FieldJson> fields) {
        this.fields = fields;
    }

    public List<FieldJson> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SchemaJson{");
        sb.append("fields=").append(fields);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SchemaJson that = (SchemaJson) o;

        if (fields != null ? !fields.equals(that.fields) : that.fields != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return fields != null ? fields.hashCode() : 0;
    }
}
