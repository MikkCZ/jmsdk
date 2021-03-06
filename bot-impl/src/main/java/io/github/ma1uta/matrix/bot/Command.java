/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix.bot;

import io.github.ma1uta.matrix.event.RoomEvent;

/**
 * Command interface.
 *
 * @param <C> actual class of the configuration.
 * @param <D> bot's dao.
 * @param <S> bot's service.
 * @param <E> extra data.
 */
public interface Command<C extends BotConfig, D extends BotDao<C>, S extends PersistentService<D>, E> {

    /**
     * Command name.
     *
     * @return name.
     */
    String name();

    /**
     * Invoking command.
     *
     * @param context   bot's context.
     * @param roomId    room id.
     * @param event     event with command.
     * @param arguments (without command).
     * @return {@code true} if invoked, else {@code false}.
     */
    boolean invoke(Context<C, D, S, E> context, String roomId, RoomEvent event, String arguments);

    /**
     * Help information.
     *
     * @return help information.
     */
    String help();

    /**
     * Usage information.
     *
     * @return usage information.
     */
    String usage();
}
