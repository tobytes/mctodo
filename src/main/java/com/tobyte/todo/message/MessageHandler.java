package com.tobyte.todo.message;

import com.tobyte.todo.help.NBTHelper;
import com.tobyte.todo.help.Reference;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Handles message on server side
 */
public class MessageHandler implements IMessageHandler<Message, IMessage>
{
    /**
     * Inserts list entries to mod item in player hand
     *
     * @param message A custom message of the mod
     * @param ctx Message context
     * @return null
     */
    @Override
    public IMessage onMessage(Message message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        NBTHelper.addStringToTagListByKey(Reference.STORAGE_KEY, player.getHeldItem(), message.getMessage());
        return null;
    }
}
