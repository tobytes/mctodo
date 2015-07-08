package com.tobyte.todo.help;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

/**
 * Created by toby on 17.05.2015.
 */
public class NBTHelper {
    public static void initNBTTagCompound(ItemStack stack)
    {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    /**
     *
     * @param key Identifier
     * @param stack Stack object
     *
     * @return null | NBTTagList
     */
    public static NBTTagList getTagListByKey(String key, ItemStack stack)
    {
        initNBTTagCompound(stack);
        if (stack.getTagCompound().hasKey(key, Constants.NBT.TAG_LIST)) {
            return stack.getTagCompound().getTagList(key, Constants.NBT.TAG_STRING);
        }
        return null;
    }


    /**
     * Adds a string to the tag list of the item or creates a new list
     *
     * @param key Identifier
     * @param stack Stack object
     * @param string String to be saved
     */
    public static void addStringToTagListByKey(String key, ItemStack stack, String string)
    {
        initNBTTagCompound(stack);
        NBTTagString tag = new NBTTagString(string);
        if (!stack.getTagCompound().hasKey(key, Constants.NBT.TAG_LIST)) {
            NBTTagList tagList = new NBTTagList();
            tagList.appendTag(tag);
            stack.getTagCompound().setTag(key, tagList);
        } else {
            stack.getTagCompound().getTagList(key, Constants.NBT.TAG_STRING).appendTag(tag);
        }

    }
}
