/*
 * Copyright (c) 2022-2023 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.alibaba.higress.console.service.kubernetes.model;

import java.io.IOException;
import java.util.Locale;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

@JsonAdapter(OperationType.JsonAdapter.class)
public enum OperationType {

    /**
     * Adds a value to an object or inserts it into an array. In the case of an array, the value is inserted before the
     * given index. The - character can be used instead of an index to insert at the end of an array.
     */
    ADD,
    /**
     * Removes a value from an object or array.
     */
    REMOVE,
    /**
     * Replaces a value. Equivalent to a “remove” followed by an “add”.
     */
    REPLACE,
    /**
     * Moves a value from one location to the other. Both <code>from</code> and <code>path</code> are JSON Pointers.
     */
    MOVE,
    /**
     * Copies a value from one location to another within the JSON document. Both <code>from</code> and
     * <code>path</code> are JSON Pointers.
     */
    COPY,
    /**
     * Tests that the specified value is set in the document. If the test fails, then the patch as a whole should not
     * apply.
     */
    TEST;

    static class JsonAdapter extends TypeAdapter<OperationType> {

        @Override
        public void write(JsonWriter jsonWriter, OperationType operationType) throws IOException {
            if (operationType == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(operationType.toString().toLowerCase(Locale.ROOT));
            }
        }

        @Override
        public OperationType read(JsonReader jsonReader) throws IOException {
            JsonToken token = jsonReader.peek();
            switch (token) {
                case NULL:
                    jsonReader.nextNull();
                    return null;
                case STRING:
                    String value = jsonReader.nextString();
                    return Enum.valueOf(OperationType.class, value.toUpperCase(Locale.ROOT));
                default:
                    throw new JsonSyntaxException(
                            "Unable to parse OperationType field from a " + token + " token. STRING or NULL is expected.");
            }
        }
    }
}
