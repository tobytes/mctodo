package com.tobyte.todo;
import com.tobyte.todo.help.Reference;
import com.tobyte.todo.proxy.TodoCommonProxy;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

/**
 * The main mod class that is the entry point for forge.
 * Forge searches for annotations and the respective methods
 */
@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class ToDo
{
    @Mod.Instance(Reference.MODID)
    public static ToDo INSTANCE;

    /**
     * The proxy switch tells forge what code should be executed on server (dedicated server),
     * client(client + single player server) or both
     * clientSide = single player client + build in server
     * serverSide = dedicated server
     */
    @SidedProxy(clientSide="com.tobyte.todo.proxy.TodoCombinedClientProxy", serverSide="com.tobyte.todo.proxy.TodoServerProxy")
    public static TodoCommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
