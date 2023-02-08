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

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonPatchOperation {

    @SerializedName("op")
    @JSONField(name = "op")
    private OperationType type;

    private String from;

    private String path;

    private Object value;

    public static JsonPatchOperation add(String path, Object value) {
        return new JsonPatchOperation(OperationType.ADD, null, path, value);
    }

    public static JsonPatchOperation remove(String path) {
        return new JsonPatchOperation(OperationType.REMOVE, null, path, null);
    }

    public static JsonPatchOperation replace(String path, Object value) {
        return new JsonPatchOperation(OperationType.REPLACE, null, path, value);
    }

    public static JsonPatchOperation copy(String from, String path) {
        return new JsonPatchOperation(OperationType.COPY, from, path, null);
    }

    public static JsonPatchOperation move(String from, String path) {
        return new JsonPatchOperation(OperationType.MOVE, from, path, null);
    }

    public static JsonPatchOperation test(String path, Object value) {
        return new JsonPatchOperation(OperationType.TEST, null, path, value);
    }
}
