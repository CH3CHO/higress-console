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

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonPatch {

    private List<JsonPatchOperation> operations;

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(operations);
    }

    public void addOperations(List<JsonPatchOperation> operations) {
        if (this.operations == null) {
            this.operations = new ArrayList<>();
        }
        this.operations.addAll(operations);
    }
}