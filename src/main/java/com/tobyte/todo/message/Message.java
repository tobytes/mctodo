package com.tobyte.todo.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

/**
 * A message containing saved list entries
 */
public class Message implements IMessage
{
    private String entry;

    /**
     * Default constructor needed by forge
     */
    public Message()
    {}

    /**
     * Constructor containing list entries
     *
     * @param entry List entries to be send
     */
    public Message(String entry)
    {
       this.entry = entry;
    }

    /**
     * Returns current entries
     * @return NBTTagCompound entries
     */
    public String getMessage()
    {
        return this.entry;
    }

    /**
     * Converts from buffer to NBTTagCompound
     * @param buf Byte buffer
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.entry = ByteBufUtils.readUTF8String(buf);
    }

    /**
     * Converts NBTTagCompound to byte buffer
     * @param buf Byte buffer
     */
    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, this.entry);
    }
}
