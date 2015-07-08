package com.tobyte.todo.help;

import com.tobyte.todo.ToDo;
import com.tobyte.todo.gui.GuiHandler;
import com.tobyte.todo.message.Message;
import com.tobyte.todo.message.MessageHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Helper for registering different Handlers for this mod
 */
public class RegisterHelper
{
    /**
     * Simple Wrapper for network communication
     * between server and client
     */
    public static final SimpleNetworkWrapper simpleNetworkWrapper
            = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    /**
     * Registers new items to the game
     * @param item New item to register
     */
    public static void registerItemAndRecipe(Item item)
    {
        GameRegistry.registerItem(item, Reference.MODID + "_" + item.getUnlocalizedName().substring(5));
        GameRegistry.addShapelessRecipe(
                new ItemStack(item),
                Items.paper,
                new ItemStack(Items.dye, 1, 0)
        );
    }

    /**
     * Registers a new message handler and message for communication between server and client.
     * The handler will be registered on the receiving side as stated by the side parameter.
     *
     * @param handlerClass The class that handles the incoming message
     * @param messageClass The message
     * @param discriminator A discriminator byte
     * @param side The side for the handler
     */
    public static void registerToDoMessage(Class<MessageHandler> handlerClass, Class<Message> messageClass,
                                       Integer discriminator, Side side)
    {
        simpleNetworkWrapper.registerMessage(handlerClass, messageClass, discriminator, side);
    }

    /**
     * Registers a new gui handler for this mod to the game
     *
     * @param instance The mod for which the gui should be registered
     * @param guiHandler A handler to create gui objects
     */
    public static void registerGui(ToDo instance, GuiHandler guiHandler)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);
    }
}
