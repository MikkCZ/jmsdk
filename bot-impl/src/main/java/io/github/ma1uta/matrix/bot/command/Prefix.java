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

package io.github.ma1uta.matrix.bot.command;

import io.github.ma1uta.matrix.bot.BotConfig;
import io.github.ma1uta.matrix.bot.BotDao;
import io.github.ma1uta.matrix.bot.Context;
import io.github.ma1uta.matrix.bot.PersistentService;
import io.github.ma1uta.matrix.event.RoomEvent;

/**
 * Set new prefix or show current.
 *
 * @param <C> bot configuration.
 * @param <D> bot dao.
 * @param <S> bot service.
 * @param <E> extra data.
 */
public class Prefix<C extends BotConfig, D extends BotDao<C>, S extends PersistentService<D>, E> extends OwnerCommand<C, D, S, E> {

    @Override
    public String name() {
        return "prefix";
    }

    @Override
    public boolean ownerInvoke(Context<C, D, S, E> context, String roomId, RoomEvent event, String arguments) {
        C config = context.getConfig();
        if (arguments == null || arguments.trim().isEmpty()) {
            String prefix = config.getPrefix();
            context.getMatrixClient().event().sendNotice(roomId, prefix == null ? "!" : prefix);
        } else {
            config.setPrefix(arguments);
        }
        return true;
    }

    @Override
    public String help() {
        return "set new prefix or show current (invoked only by owner).";
    }

    @Override
    public String usage() {
        return "prefix [<prefix>]";
    }
}
