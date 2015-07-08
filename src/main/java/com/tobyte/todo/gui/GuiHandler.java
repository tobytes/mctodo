package com.tobyte.todo.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Handles display of custom gui elements
 */
public class GuiHandler implements IGuiHandler
{
    /**
     * Handles custom gui display on dedicated server
     * @param ID Gui id
     * @param player Player instance
     * @param world World instance
     * @param x Position x
     * @param y Position y
     * @param z Position z
     *
     * @return null
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    /**
     * Handles custom gui display on combined client
     *
     * @param ID Gui id
     * @param player Player instance
     * @param world World instance
     * @param x Position x
     * @param y Position y
     * @param z Position z
     *
     * @return null
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID) {
            case 0:
                return new Gui();
            default:
                return null;
        }
    }
}
