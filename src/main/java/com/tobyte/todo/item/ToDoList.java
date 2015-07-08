package com.tobyte.todo.item;

import com.tobyte.todo.ToDo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by toby on 15.05.2015.
 */
public class ToDoList extends Item
{
    /**
     * Constructor
     */
    public ToDoList()
    {
        super();
        setUnlocalizedName("ToDoList");
        setCreativeTab(CreativeTabs.tabMisc);
        setTextureName("book_writable");
    }

    /**
     * Actions that should be triggered on right mouse click
     *
     * @param stack The item stack
     * @param world The world instance
     * @param player The player instance
     *
     * @return ItemStack
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.openGui(ToDo.INSTANCE, 0, world, 0, 0, 0);
        return super.onItemRightClick(stack, world, player);
    }
}
