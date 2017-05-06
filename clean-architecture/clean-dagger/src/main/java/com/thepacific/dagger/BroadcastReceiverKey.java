/*
 * Copyright (C) 2016 The Dagger Authors.
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

package com.thepacific.dagger;

import static java.lang.annotation.ElementType.METHOD;

import android.content.BroadcastReceiver;

import dagger.MapKey;
import dagger.internal.Beta;

import java.lang.annotation.Target;

/**
 * {@link MapKey} annotation to key bindings by a type of a {@link BroadcastReceiver}.
 */
@Beta
@MapKey
@Target(METHOD)
public @interface BroadcastReceiverKey {
    Class<? extends BroadcastReceiver> value();
}
