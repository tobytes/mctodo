package com.tobyte.todo.proxy;

import com.tobyte.todo.ToDo;
import com.tobyte.todo.gui.GuiHandler;
import com.tobyte.todo.item.ToDoList;
import com.tobyte.todo.help.RegisterHelper;
import com.tobyte.todo.message.Message;
import com.tobyte.todo.message.MessageHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

/**
 * Proxy for both client side and server side
 * Client side = single player client + build in server
 * Server side = dedicated server
 */
public class TodoCommonProxy
{

    public void preInit(FMLPreInitializationEvent e)
    {
        RegisterHelper.registerItemAndRecipe(new ToDoList());
        RegisterHelper.registerToDoMessage(MessageHandler.class, Message.class, 0, Side.SERVER);
    }

    public void init(FMLInitializationEvent e)
    {
        RegisterHelper.registerGui(ToDo.INSTANCE, new GuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e)
    {
    }
}
